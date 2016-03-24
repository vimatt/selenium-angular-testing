package se.vimatt.angular;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import se.vimatt.util.SparkServer;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by victormattsson on 2016-03-21.
 */
public class AUnitTest {

    static ChromeDriver driver;
    static RMAngularDriver rmAngularDriver;

    @BeforeClass
    public static void before() {
    	driver = new ChromeDriver();
    	driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
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

        driver.get("http://localhost:9090");
        rmAngularDriver.waitforAngularJS();

        assertEquals(driver.getTitle(), "Demo Site");
    }

    @Test
    public void favoriteMoviesData() throws InterruptedException {

        driver.get("http://localhost:9090/#/favorites");
        rmAngularDriver.waitforAngularJS();

        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        String firstMovieTitle = element.getText();

        assertEquals(firstMovieTitle, "Fargo");
    }
}
