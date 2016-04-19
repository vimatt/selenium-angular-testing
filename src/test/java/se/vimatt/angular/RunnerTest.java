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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import se.vimatt.angular.driver.RMAngularDriver;
import se.vimatt.util.SparkServer;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by victor mattsson on 2016-03-29.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, format = {"json:target/cucumber.json"}, features = "src/test/resources")
public class RunnerTest {

    private static RMAngularDriver driver;
    private static final String LOCALHOST = "http://localhost:9090";

    private RunnerTest() {
    }

    @BeforeClass
    public static void before() {
        driver = new RMAngularDriver(new ChromeDriver());
        driver.manage().window().maximize();
        SparkServer.start();
    }

    @AfterClass
    public static void after() {
        driver.quit();
        SparkServer.close();
    }

    public static class StepDefinitions {

        private HashMap<String, By> aliasLocations = new HashMap<>();

        /* Navigating */

        @Given("^that we navigate to \"([^\"]*)\"$")
        public void that_we_navigate_to(String url) {
            driver.get(url);
        }

        @Given("^that we navigate to localhost$")
        public void that_we_navigate_to_localhost() {
            driver.get(LOCALHOST);
        }

        /* Actions */

        @Given("^input \"([^\"]*)\" to the element \"([^\"]*)\"$")
        public void input_to_the_element(String data, String element) {
            WebElement el = driver.findElement(aliasLocations.get(element));
            el.click();
            el.sendKeys(data);
        }

        @And("^we wait for (\\d+) milliseconds$")
        public void we_wait_for_milliseconds(int millisecs) throws InterruptedException {
            Thread.sleep(millisecs);
        }

        @Then("^click the element \"([^\"]*)\"$")
        public void click_the_element(String element) {
            driver.findElement(aliasLocations.get(element)).click();
        }

        @And("^click the (\\d+)(?:st|nd|rd|th) element \"([^\"]*)\"$")
        public void click_the_nd_element(int index, String element) {
            List<WebElement> elements = driver.findElements(aliasLocations.get(element));
            elements.get(index - 1).click();
        }

        @And("^choose the (\\d+)(?:st|nd|rd|th) angular option \"([^\"]*)\"$")
        public void choose_the_angular_option(int index, String element) {
            List<WebElement> elements = driver.findElements(aliasLocations.get(element));
            elements.get(index - 1).click();
        }

        /* Aliases */

        @Given("^that we know the element with the \"(|id|xpath|class|css|link-text|model|binding|options|repeater)\" named \"(.*)\" as \"([^\"]*)\"$")
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
                case "link-text":
                    aliasLocations.put(by, By.linkText(element));
                    break;
                case "model":
                    aliasLocations.put(by, ByAngular.model(element));
                    break;
                case "binding":
                    aliasLocations.put(by, ByAngular.binding(element));
                    break;
                case "options":
                    aliasLocations.put(by, ByAngular.options(element));
                    break;
                case "repeater":
                    aliasLocations.put(by, ByAngular.repeater(element));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown type: " + type);
            }
        }

        @Given("^that we know the repeater element \"(.*)\" as \"([^\"]*)\" with row (\\d+)$")
        public void that_we_know_the_repeater_element(String element, String by, int row) {
            aliasLocations.put(by, ByAngular.repeater(element).row(row));
        }

        @Given("^that we know the repeater element \"([^\"]*)\" as \"([^\"]*)\" with row (\\d+) and column (\\d+)$")
        public void that_we_know_the_repeater_element_as_with_row_number_and_column(String element, String by, int row, int column) {
            aliasLocations.put(by, ByAngular.repeater(element).row(row).column(column));
        }

        /* Assertions */

        @Then("^the title of the page should be \"([^\"]*)\"$")
        public void the_title_of_the_page_should_be(String title) {
            assertEquals(title, driver.getTitle());
        }

        @Then("^the URL should be \"([^\"]*)\"$")
        public void the_url_should_be(String URL) {
            assertEquals(URL, driver.getCurrentUrl());
        }

        @Then("^the first cell in the table should be \"([^\"]*)\"$")
        public void the_first_cell_in_the_table_should_be(String title) {
            WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
            String firstMovieTitle = element.getText();
            assertEquals(title, firstMovieTitle);
        }

        @Then("^the attribute \"([^\"]*)\" of \"([^\"]*)\" should be \"([^\"]*)\"$")
        public void the_attribute_of_should_be(String attribute, String element, String expected) {
            WebElement el = driver.findElement(aliasLocations.get(element));
            assertEquals(expected, el.getAttribute(attribute));
        }

        @Then("^the text of the element \"([^\"]*)\" should be \"([^\"]*)\"$")
        public void the_text_of_the_element_should_be(String element, String expected) {
            assertEquals(expected, driver.findElement(aliasLocations.get(element)).getText());
        }

        @Then("^the text of the (\\d+)(?:st|nd|rd|th) element of \"([^\"]*)\" should be \"([^\"]*)\"$")
        public void the_text_of_the_st_element_should_be(int index, String element, String expected) {
            List<WebElement> elements = driver.findElements(aliasLocations.get(element));
            assertEquals(expected, elements.get(index - 1).getText());
        }

        @Then("^we should have (\\d+) elements in \"([^\"]*)\"$")
        public void we_should_have_elements_in(int number, String element) {
            List<WebElement> elements = driver.findElements(aliasLocations.get(element));
            assertEquals(number, elements.size());
        }

        @Then("^there should be (\\d+) elements in repeater element \"([^\"]*)\"$")
        public void there_should_be__elements_in_repeater(int count, String element) {
            List<WebElement> elements = driver.findElements(aliasLocations.get(element));
            assertEquals(count, elements.size());
        }
    }
}
