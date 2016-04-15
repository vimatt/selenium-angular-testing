package se.vimatt.angular.auto;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import se.vimatt.angular.ByAngular;
import se.vimatt.angular.RMAngularDriver;
import se.vimatt.util.SparkServer;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by victormattsson on 2016-03-21.
 */
public class DemoSiteTest {

    private static WebDriver driver;
    private static RMAngularDriver rmAngularDriver;
    private final String LOCALHOST = "http://localhost:9090";

    @BeforeClass
    public static void before() {
    	driver = new ChromeDriver();
    	rmAngularDriver = new RMAngularDriver(driver);
    	SparkServer.start();
    }

    @AfterClass
    public static void afterClass() {
    	driver.quit();
        SparkServer.close();
    }

    @Test
    public void pageTitle() {

        driver.get(LOCALHOST);
        rmAngularDriver.waitforAngular();

        assertEquals("Demo Site", driver.getTitle());
    }

    /**
     * This test displays that the waitForAngular works, since the http response on this URL
     * is set to sleep for 500 milliseconds. Without the wait method the test would fail.
     */
    @Test
    public void waitForAngularDemo() {

        driver.get(LOCALHOST + "/#/favorites/movies");
        rmAngularDriver.waitforAngular();

        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        String firstMovieTitle = element.getText();

        assertEquals(firstMovieTitle, "Fargo");
    }

    @Test
    public void byAngularModel() throws InterruptedException {

        driver.get(LOCALHOST);
        rmAngularDriver.waitforAngular();

        WebElement modelElement = driver.findElement(ByAngular.model("search"));
        modelElement.sendKeys("Fargo");
        Thread.sleep(3000);
        WebElement inputElement = driver.findElement(By.id("search-field"));
        assertEquals("Fargo", inputElement.getAttribute("value"));
    }

    @Test
    public void byAngularBinding() {
        driver.get(LOCALHOST);
        rmAngularDriver.waitforAngular();

        WebElement bindingElements = driver.findElement(ByAngular.binding("search"));
        System.out.println(bindingElements.getText());
        assertEquals("You are searching for a MOVIE:", bindingElements.getText());

        WebElement searchInput = driver.findElement(ByAngular.model("search"));
        searchInput.sendKeys("Men in Black");
        System.out.println(bindingElements.getText());
        assertEquals("You are searching for a MOVIE: Men in Black", bindingElements.getText());
    }

    @Test
    public void byAngularOptions() {
        driver.get(LOCALHOST);
        rmAngularDriver.waitforAngular();

        List<WebElement> options = driver.findElements(ByAngular.options("types.name for types in types" +
                ".availableTypes track by types.id"));
        assertEquals("movies", options.get(0).getText());
        assertEquals("series", options.get(1).getText());
    }

    @Test
    public void byAngularRepeater() {
        driver.get(LOCALHOST + "/#/favorites/movies");
        rmAngularDriver.waitforAngular();

        List<WebElement> repeater = driver.findElements(ByAngular.repeater("movie in movies"));

        driver.findElements(ByAngular.repeater("movie in movies").row(1));

        for (WebElement element : repeater) {
            System.out.println(element.getText());
        }
    }

    @Test
    public void byAngularRepeaterRow() {
        driver.get(LOCALHOST + "/#/favorites/movies");
        rmAngularDriver.waitforAngular();

        WebElement el = driver.findElement(ByAngular.repeater("movie in movies").row(1));

        System.out.println(el.getText());
    }

    @Test
    public void byAngularRepeaterCell() {
        driver.get(LOCALHOST + "/#/favorites/movies");
        rmAngularDriver.waitforAngular();

        WebElement el = driver.findElement(ByAngular.repeater("movie in movies").row(1).column(2));

        System.out.println(el.getText());
    }
}
