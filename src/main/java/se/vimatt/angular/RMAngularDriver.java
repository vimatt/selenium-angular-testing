package se.vimatt.angular;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import se.vimatt.angular.element.AngularElement;

import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * The specific class that provides the method that handles the waiting for Angular
 *
 * Created by victormattsson on 2016-03-21.
 */
public class RMAngularDriver implements WebDriver {

    private JavascriptExecutor jSExecutor;
	private WebDriver driver;
	private boolean sync;

    //The constructor takes the given WebDriver and sets an implicit wait which tells
    //the driver to wait in this case for 30 secs until throwing an exception if it can't find the element.
    //Then we cast the driver to a Selenium JavascriptExecutor
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
    //is available for testing
    public void waitforAngular() {
    	jSExecutor.executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                "var rootSelector = 'body';" +
                "var el = document.querySelector(rootSelector);" +
                "    try {" +
                "        if (window.getAngularTestability) {" +
                "            window.getAngularTestability(el).whenStable(callback);" +
                "            return;" +
                "        }" +
                        //A check to see if angular is undefined, if so it throws an error
                "        if (!window.angular) {" +
                "            throw new Error('window.angular is undefined.  This could be either ' +" +
                "                'because this is a non-angular page or because your test involves ' +" +
                "                'client-side navigation, which can interfere with Protractor\\'s ' +" +
                "                'bootstrapping.  See http://git.io/v4gXM for details');" +
                "        }" +
                "        if (angular.getTestability) {" +
                "            angular.getTestability(el).whenStable(callback);" +
                "        } else {" +
                "            if (!angular.element(el).injector()) {" +
                "                throw new Error('root element (' + rootSelector + ') has no injector.' +" +
                "                    ' this may mean it is not inside ng-app.');" +
                "            }" +
                "            angular.element(el).injector().get('$browser')." +
                "            notifyWhenNoOutstandingRequests(callback);" +
                "        }" +
                "    } catch (err) {" +
                "        callback(err.message);" +
                "    }");
    }
    
    @Override
	public void close() {
		driver.close();
	}

	@Override
	public WebElement findElement(By by) {
		if(sync){
			waitforAngular();
		}
		return new AngularElement(this, driver.findElement(by));
	}

	@Override
	public List<WebElement> findElements(By by) {
		if(sync){
			waitforAngular();
		}
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
	
	public void angularSync(boolean shouldSync){
		this.sync = shouldSync; 
	}
	
	public boolean angularSync(){
		return this.sync;
	}

	public void waitforAngularIfSync() {
		if(sync) waitforAngular();
	}
}
