package se.vimatt.angular;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * The specific class that provides the method that handles the waiting for Angular
 *
 * Created by victormattsson on 2016-03-21.
 */
public class RMAngularDriver {

    private JavascriptExecutor driver;

    //The constructor takes the given WebDriver and sets an implicit wait which tells
    //the driver to wait in this case for 30 secs until throwing an exception if it can't find the element.
    //Then we cast the driver to a Selenium JavascriptExecutor
    public RMAngularDriver(WebDriver driver) {
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        this.driver = (JavascriptExecutor) driver;
    }

    //This second constructor makes it possible for the user to set the wait time manually
    public RMAngularDriver(WebDriver driver, int timeOut) {
        driver.manage().timeouts().setScriptTimeout(timeOut, TimeUnit.SECONDS);
        this.driver = (JavascriptExecutor) driver;
    }

    //The waitForAngular method uses the Selenium JavascriptExecutor which enables us to inject
    //javascript into our browser and in this case wait until angular has finished loading and
    //is available for testing
    public void waitforAngular() {
        driver.executeAsyncScript(
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
}
