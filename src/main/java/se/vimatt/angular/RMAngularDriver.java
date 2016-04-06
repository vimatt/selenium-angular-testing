package se.vimatt.angular;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by victormattsson on 2016-03-21.
 */
public class RMAngularDriver {


    private JavascriptExecutor driver;

    public RMAngularDriver(WebDriver driver) {
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        this.driver = (JavascriptExecutor) driver;
    }

    public RMAngularDriver(WebDriver driver, int timeOut) {
        driver.manage().timeouts().setScriptTimeout(timeOut, TimeUnit.SECONDS);
        this.driver = (JavascriptExecutor) driver;
    }

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
