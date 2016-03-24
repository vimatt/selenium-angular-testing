package se.vimatt.angular;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * Created by victormattsson on 2016-03-21.
 */
public class AUnitTest {

    ChromeDriver driver;
    RMAngularDriver rmAngularDriver;

    @BeforeSuite
    public void before() {
        driver = new ChromeDriver();
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        rmAngularDriver = new RMAngularDriver(driver);
    }

    @AfterSuite
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void pageTitle() {

        driver.get("http://localhost:9090");
        rmAngularDriver.waitforAngularJS();

        assertEquals("Demo Site", driver.getTitle());
    }

    @Test
    public void favoriteMoviesData() {

        driver.get("http://localhost:9090/#/favorites");
        rmAngularDriver.waitforAngularJS();

        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        String firstMovieTitle = element.getText();

        assertEquals(firstMovieTitle, "Fargo");
    }
}
