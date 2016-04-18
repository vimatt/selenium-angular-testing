package se.vimatt.angular;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import se.vimatt.util.SparkServer;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by victormattsson on 2016-03-29.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources")
public class RunnerTest {

    private static WebDriver driver;
    private static RMAngularDriver rmAngularDriver;
    private static final String LOCALHOST = "http://localhost:9090";

    @BeforeClass
    public static void before() {
        driver = new ChromeDriver();
        rmAngularDriver = new RMAngularDriver(driver);
        SparkServer.start();
    }

    @AfterClass
    public static void after() {
        driver.quit();
        SparkServer.close();
    }

    public static class StepDefinitions {

        private HashMap<String, By> aliasLocations = new HashMap<>();
        private HashMap<String, BaseBy> angularAliasLocations = new HashMap<>();

        @Given("^that we navigate to \"([^\"]*)\"$")
        public void that_we_navigate_to(String url) {
            driver.get(url);
        }

        @Given("^that we navigate to localhost$")
        public void that_we_navigate_to_localhost() {
            driver.get(LOCALHOST);
        }

        @Then("^the title of the page should be \"([^\"]*)\"$")
        public void the_title_of_the_page_should_be(String title) {
            assertEquals(title, driver.getTitle());
        }

        @Given("^we wait for angular$")
        public void we_wait_for_angular() {
            rmAngularDriver.waitforAngular();
        }

        @Then("^the first cell in the table should be \"([^\"]*)\"$")
        public void the_first_cell_in_the_table_should_be(String title) {
            WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
            String firstMovieTitle = element.getText();
            assertEquals(title, firstMovieTitle);
        }

        @Given("^that we know the element with the \"(|id|xpath|class|css)\" named \"(.*)\" as \"([^\"]*)\"$")
        public void that_we_know_the_element_named(String type, String element, String by) {
            switch (type) {
                case "id":
                    aliasLocations.put(by, By.id(element));
                    break;
                case "xpath":
                    aliasLocations.put(by, By.xpath(element));
                    break;
                case "class":
                    aliasLocations.put(by, By.className(element));
                    break;
                case "css":
                    aliasLocations.put(by, By.cssSelector(element));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown type: " + type);
            }
        }

        @Given("^that we know the angular element with the \"(model|binding|options|repeater)\" named \"(.*)\" as \"([^\"]*)\"$")
        public void that_we_know_the_angular_element_named(String type, String element, String by) {
            switch (type) {
                case "model":
                    angularAliasLocations.put(by, ByAngular.model(element));
                    break;
                case "binding":
                    angularAliasLocations.put(by, ByAngular.binding(element));
                    break;
                case "options":
                    angularAliasLocations.put(by, ByAngular.options(element));
                    break;
                case "repeater":
                    angularAliasLocations.put(by, ByAngular.repeater(element));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown type: " + type);
            }
        }

        @Given("^that we know the repeater element \"(.*)\" as \"([^\"]*)\" with row (\\d+)$")
        public void that_we_know_the_repeater_element(String element, String by, int row) {
            angularAliasLocations.put(by, ByAngular.repeater(element).row(row));
        }

        @Given("^that we know the repeater element \"([^\"]*)\" as \"([^\"]*)\" with row (\\d+) and column (\\d+)$")
        public void that_we_know_the_repeater_element_as_with_row_number_and_column(String element, String by, int row, int column) {
            angularAliasLocations.put(by, ByAngular.repeater(element).row(row).column(column));
        }

        @Then("^the attribute \"([^\"]*)\" of \"([^\"]*)\" should be \"([^\"]*)\"$")
        public void the_attribute_of_should_be(String attribute, String element, String expected) {
            WebElement el = driver.findElement(aliasLocations.get(element));
            assertEquals(expected, el.getAttribute(attribute));
        }

        @Given("^input \"([^\"]*)\" to the element \"([^\"]*)\"$")
        public void input_to_the_element(String data, String element) {
            WebElement el = driver.findElement(aliasLocations.get(element));
            el.sendKeys(data);
            el.sendKeys(Keys.SPACE);
        }

        @Given("^input \"([^\"]*)\" to the angular element \"([^\"]*)\"$")
        public void input_to_the_angular_element(String data, String element) {
            driver.findElement(angularAliasLocations.get(element)).sendKeys(data);
        }

        @And("^we wait for (\\d+) milliseconds$")
        public void we_wait_for_milliseconds(int millisecs) throws InterruptedException {
            Thread.sleep(millisecs);
        }

        @Then("^the text of the element \"([^\"]*)\" should be \"([^\"]*)\"$")
        public void the_text_of_the_element_should_be(String element, String expected) {
            assertEquals(expected, driver.findElement(aliasLocations.get(element)).getText());
        }

        @Then("^the text of the angular element \"([^\"]*)\" should be \"([^\"]*)\"$")
        public void the_text_of_the_angular_element_should_be(String element, String expected) {
            assertEquals(expected, driver.findElement(angularAliasLocations.get(element)).getText());
        }

        @Then("^click the element \"([^\"]*)\"$")
        public void click_the_element(String element) {
            driver.findElement(aliasLocations.get(element)).click();
        }

        @Then("^click the angular element \"([^\"]*)\"$")
        public void click_the_angular_element(String element) {
            driver.findElement(angularAliasLocations.get(element)).click();
        }

        @Then("^the text of the (\\d+)(?:st|nd|rd|th) element of \"([^\"]*)\" should be \"([^\"]*)\"$")
        public void the_text_of_the_st_element_should_be(int index, String element, String expected) {
            List<WebElement> elements = driver.findElements(angularAliasLocations.get(element));
            assertEquals(expected, elements.get(index - 1).getText());
        }

        @Then("^we should have (\\d+) elements in \"([^\"]*)\"$")
        public void we_should_have_elements_in(int number, String element) {
            List<WebElement> elements = driver.findElements(angularAliasLocations.get(element));
            assertEquals(number, elements.size());
        }


        @Then("^the URL should be \"([^\"]*)\"$")
        public void the_url_should_be(String URL) {
            assertEquals(URL, driver.getCurrentUrl());
        }

        @And("^click the (\\d+)(?:st|nd|rd|th) angular element \"([^\"]*)\"$")
        public void clickTheNdAngularElement(int index, String element) {
            List<WebElement> elements = driver.findElements(angularAliasLocations.get(element));
            elements.get(index-1).click();
        }

        @Then("^there should be (\\d+) elements in repeater element \"([^\"]*)\"$")
        public void there_should_be__elements_in_repeater(int count, String element) {
            List<WebElement> elements = driver.findElements(angularAliasLocations.get(element));
            assertEquals(count, elements.size());
        }

        @And("^choose the (\\d+)(?:st|nd|rd|th) angular option \"([^\"]*)\"$")
        public void choose_the_angular_option(int index, String element) {
            List<WebElement> elements = driver.findElements(angularAliasLocations.get(element));
            elements.get(index-1).click();
        }
    }
}
