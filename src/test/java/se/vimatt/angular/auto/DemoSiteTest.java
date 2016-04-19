package se.vimatt.angular.auto;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import se.vimatt.angular.ByAngular;
import se.vimatt.angular.driver.RMAngularDriver;
import se.vimatt.util.SparkServer;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * A regular Selenium test class which demonstrates the different Angular functions of my framework
 * <p>
 * Created by victor mattsson on 2016-03-21.
 */
public class DemoSiteTest {

    private static RMAngularDriver rmAngularDriver;
    private static final String LOCALHOST = "http://localhost:9090";
    private static final String MOVIE_FAVORITES = LOCALHOST + "/#/favorites/movies";

    //Instantiates the RMAngularDriver and SparkServer before the test class runs
    @BeforeClass
    public static void before() {
        rmAngularDriver = new RMAngularDriver(new ChromeDriver());
        rmAngularDriver.manage().window().maximize();
        SparkServer.start();
    }

    //After the test run is done we close down the driver and spark server
    @AfterClass
    public static void afterClass() {
        rmAngularDriver.quit();
        SparkServer.close();
    }

    @Before
    public void setupDriver() {
        //Resets the waitForAngular to true before each test method
        rmAngularDriver.angularSync(true);
    }

    @Test
    public void pageTitle() {
        rmAngularDriver.get(LOCALHOST);
        assertEquals("Demo Site", rmAngularDriver.getTitle());
    }

    /**
     * This test displays that the waitForAngular works, since the http response on this URL
     * is set to sleep for 500 milliseconds. Without the wait method the test would fail.
     */
    @Test
    public void waitForAngularDemo() {
        rmAngularDriver.get(MOVIE_FAVORITES);
        WebElement element = rmAngularDriver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        String firstMovieTitle = element.getText();

        assertEquals(firstMovieTitle, "Fargo");
    }

    /**
     * This test displays that the waitForAngular works, since the http response on this URL
     * is set to sleep for 500 milliseconds. Without the wait method the test would fail.
     * And this test will, with an expected Exception throw
     */
    @Test(expected = NoSuchElementException.class)
    public void waitForAngularDemoBeToSlow() {
        rmAngularDriver.get(MOVIE_FAVORITES);
        //Set that we don't want to wait for angular
        rmAngularDriver.angularSync(false);
        rmAngularDriver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
    }

    /**
     * This demonstrates the ByAngular.model function which finds the input element on the site by it's model name 'search'
     * Then it sends the keys 'Fargo' to the element and finds the same element with an ordinary id locator
     * and asserts that the attribute value in the element in fact is 'Fargo'
     */
    @Test
    public void byAngularModel() {
        rmAngularDriver.get(LOCALHOST);
        WebElement modelElement = rmAngularDriver.findElement(ByAngular.model("search"));
        modelElement.sendKeys("Fargo");
        WebElement inputElement = rmAngularDriver.findElement(By.id("search-field"));
        assertEquals("Fargo", inputElement.getAttribute("value"));
    }

    /**
     * This demonstrates the ByAngular.binding function which finds the binding elements with it's name 'search'
     * and asserts that the text of the element is correct, after that we find the input element by model 'search'
     * and send the keys 'Men in Black', and asserts that that the binding element has updated it's text to correspond the input.
     */
    @Test
    public void byAngularBinding() {
        rmAngularDriver.get(LOCALHOST);
        WebElement bindingElements = rmAngularDriver.findElement(ByAngular.binding("search"));
        assertEquals("You are searching for a MOVIE:", bindingElements.getText());
        WebElement searchInput = rmAngularDriver.findElement(ByAngular.model("search"));
        searchInput.sendKeys("Men in Black");
        assertEquals("You are searching for a MOVIE: Men in Black", bindingElements.getText());
    }

    /**
     * This test demonstrates the ByAngular.options function which finds the option elements by the given name
     * and asserts the text of the first two elements(and only elements) that they are what we expect.
     */
    @Test
    public void byAngularOptions() {
        rmAngularDriver.get(LOCALHOST);
        List<WebElement> options = rmAngularDriver.findElements(ByAngular.options("types.name for types in types.availableTypes track by types.id"));
        assertEquals("movies", options.get(0).getText());
        assertEquals("series", options.get(1).getText());
    }

    /**
     * This test demonstrates the ByAngular.repeater function which finds the repeater elements with the name 'movie in movies'
     * and asserts that we get the right amount of elements in the list. Later we get the first repeater and find the first child of it
     * and asserts that the text is what we expect and then find the fifth repeater element and the fifth child of it and asserts
     * that the text is what we expect
     */
    @Test
    public void byAngularRepeater() {
        rmAngularDriver.get(MOVIE_FAVORITES);
        List<WebElement> repeater = rmAngularDriver.findElements(ByAngular.repeater("movie in movies"));
        assertEquals(5, repeater.size());
        String movieTitleOnFirstElement = repeater.get(0).findElement(By.cssSelector(":nth-child(1)")).getText();
        assertEquals("Fargo", movieTitleOnFirstElement);
        String ratingOnLastElement = repeater.get(4).findElement(By.cssSelector(":nth-child(5)")).getText();
        assertEquals("8.6", ratingOnLastElement);
    }

    /**
     * This test demonstrates the ByAngular.repeater.row function which first finds the repeater element with name 'movie in movies' and then
     * find the first child element with the row method (like in the previous test) and then asserts that the text is what we expect.
     */
    @Test
    public void byAngularRepeaterRow() {
        rmAngularDriver.get(MOVIE_FAVORITES);
        WebElement firstElementOfRepeater = rmAngularDriver.findElement(ByAngular.repeater("movie in movies").row(1));
        assertEquals("Fargo 1996 Crime, Drama, Thriller 98 min 8.2", firstElementOfRepeater.getText());
    }

    /**
     * This test demonstrates the ByAngular.repeater.row.column function which first finds the repeater element with name 'movie in movies' and then
     * find the first child element with the row method (like in the previous test) and then finds the second element within the row
     * and asserts that the text is what we expect.
     */
    @Test
    public void byAngularRepeaterCell() {
        rmAngularDriver.get(MOVIE_FAVORITES);
        WebElement firstElementSecondRowOfRepeater = rmAngularDriver.findElement(ByAngular.repeater("movie in movies").row(1).column(2));
        assertEquals("1996", firstElementSecondRowOfRepeater.getText());
    }
}
