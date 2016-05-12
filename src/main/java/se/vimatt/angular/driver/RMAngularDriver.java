package se.vimatt.angular.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import se.vimatt.angular.element.AngularElement;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * The specific class that provides the method that handles the waiting for Angular
 * Implements WebDriver and therefore overrides the methods, which some have added functionality for our purpose
 * Created by victor mattsson on 2016-03-21.
 */
public class RMAngularDriver implements WebDriver {

    private JavascriptExecutor jSExecutor;
    private WebDriver driver;
    private boolean sync;

    //The constructor takes the given WebDriver and sets an implicit wait which tells
    //the driver to wait in this case for 30 secs until throwing an exception if it can't find the element.
    //Then we cast the driver to a Selenium JavascriptExecutor to be used in the waitForAngular method
    public RMAngularDriver(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        this.jSExecutor = (JavascriptExecutor) driver;
        this.sync = true;
    }

    //This second constructor makes it possible for the user to set the wait time manually
    public RMAngularDriver(WebDriver driver, int timeOut) {
        driver.manage().timeouts().setScriptTimeout(timeOut, TimeUnit.SECONDS);
        this.jSExecutor = (JavascriptExecutor) driver;
    }

    //The waitForAngular method uses the Selenium JavascriptExecutor which enables us to inject
    //javascript into our browser and in this case wait until angular has finished loading and
    //is available for testing. The idea of this script is taken from Protractor
    public void waitforAngular() {
        jSExecutor.executeAsyncScript(
                //With the executeAsyncScript a callback is added to the list of arguments passed to my script and we
                //find it here by fetching the last argument in the arguments list
                //This callback must be called within the time limit for it to return with success
                "var callback = arguments[arguments.length - 1];" +
                //Save the body element in el variable
                "var el = document.querySelector('body');" +
                "    try {" +
                "        if (window.getAngularTestability) {" +
                "            window.getAngularTestability(el).whenStable(callback);" +
                "            return;" +
                "        }" +
                "        if (angular.getTestability) {" +
                "            angular.getTestability(el).whenStable(callback);" +
                "        } " +
                "    } catch (err) {" +
                "        callback(err.message);" +
                "    }");
    }

    @Override
    public void close() {
        driver.close();
    }

    //The overridden method findElement with added functionality
    @Override
    public WebElement findElement(By by) {
        //We do a check to see if we want to wait for angular
        if (sync) {
            waitforAngular();
        }
        //Returns a new AngularElement which is a WebElement but with additional functionality, see specific class for more
        return new AngularElement(this, driver.findElement(by));
    }

    //The overridden method findElements with added functionality
    @Override
    public List<WebElement> findElements(By by) {
        if (sync) {
            waitforAngular();
        }
        //Because findElements returns a list of WebElements, here we need to iterate through them and rewrite
        //each of them to out own AngularElements, and later return the new list
        List<WebElement> findElements = driver.findElements(by);
        for (int i = 0; i < findElements.size(); i++) {
            WebElement webElement = findElements.get(i);
            findElements.set(i, new AngularElement(this, webElement));
        }
        return findElements;
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    //By default angular will sync, but with this method it is possible to change the state of the boolean
    public void angularSync(boolean shouldSync) {
        this.sync = shouldSync;
    }

    public boolean angularSync() {
        return this.sync;
    }

    //If the bool sync is set to true, which means that we want to wait for angular, the method that
    //does the waiting will be executed
    public void waitforAngularIfSync() {
        if (sync) {
            waitforAngular();
        }
    }
}
