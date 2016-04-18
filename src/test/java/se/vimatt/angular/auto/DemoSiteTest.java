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
import se.vimatt.angular.RMAngularDriver;
import se.vimatt.util.SparkServer;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by victormattsson on 2016-03-21.
 */
public class DemoSiteTest {

    private static RMAngularDriver rmAngularDriver;
    private final String LOCALHOST = "http://localhost:9090";

    @BeforeClass
    public static void before() {
    	rmAngularDriver = new RMAngularDriver(new ChromeDriver());
    	SparkServer.start();
    }

    @AfterClass
    public static void afterClass() {
    	rmAngularDriver.quit();
        SparkServer.close();
    }
    @Before
    public void setupDriver(){
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
        rmAngularDriver.get(LOCALHOST + "/#/favorites/movies");
        
        WebElement element = rmAngularDriver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        String firstMovieTitle = element.getText();

        assertEquals(firstMovieTitle, "Fargo");
    }
    
    /**
     * This test displays that the waitForAngular works, since the http response on this URL
     * is set to sleep for 500 milliseconds. Without the wait method the test would fail. And this test will
     */
    @Test(expected=NoSuchElementException.class)
    public void waitForAngularDemoBeToSlow() {
        rmAngularDriver.get(LOCALHOST + "/#/favorites/movies");
        rmAngularDriver.angularSync(false);
        rmAngularDriver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
    }

    @Test
    public void byAngularModel() throws InterruptedException {

        rmAngularDriver.get(LOCALHOST);
        

        WebElement modelElement = rmAngularDriver.findElement(ByAngular.model("search"));
        modelElement.sendKeys("Fargo");
        WebElement inputElement = rmAngularDriver.findElement(By.id("search-field"));
        assertEquals("Fargo", inputElement.getAttribute("value"));
    }

    @Test
    public void byAngularBinding() {
        rmAngularDriver.get(LOCALHOST);
        

        WebElement bindingElements = rmAngularDriver.findElement(ByAngular.binding("search"));
        assertEquals("You are searching for a MOVIE:", bindingElements.getText());

        WebElement searchInput = rmAngularDriver.findElement(ByAngular.model("search"));
        searchInput.sendKeys("Men in Black");
        assertEquals("You are searching for a MOVIE: Men in Black", bindingElements.getText());
    }

    @Test
    public void byAngularOptions() {
        rmAngularDriver.get(LOCALHOST);
        

        List<WebElement> options = rmAngularDriver.findElements(ByAngular.options("types.name for types in types" +
                ".availableTypes track by types.id"));
        assertEquals("movies", options.get(0).getText());
        assertEquals("series", options.get(1).getText());
    }

    @Test
    public void byAngularRepeater() {
        rmAngularDriver.get(LOCALHOST + "/#/favorites/movies");
       
        List<WebElement> repeater = rmAngularDriver.findElements(ByAngular.repeater("movie in movies"));

        rmAngularDriver.findElements(ByAngular.repeater("movie in movies").row(1));
    }

    @Test
    public void byAngularRepeaterRow() {
        rmAngularDriver.get(LOCALHOST + "/#/favorites/movies");

        WebElement el = rmAngularDriver.findElement(ByAngular.repeater("movie in movies").row(1));
    }

    @Test
    public void byAngularRepeaterCell() {
        rmAngularDriver.get(LOCALHOST + "/#/favorites/movies");

        WebElement el = rmAngularDriver.findElement(ByAngular.repeater("movie in movies").row(1).column(2));
    }
}
