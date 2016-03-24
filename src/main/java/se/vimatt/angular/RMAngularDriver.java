package se.vimatt.angular;

import org.openqa.selenium.JavascriptExecutor;

/**
 * Created by victormattsson on 2016-03-21.
 */
public class RMAngularDriver {


    private JavascriptExecutor driver;

    public RMAngularDriver(JavascriptExecutor driver) {
        this.driver = driver;
    }

    public void waitforAngularJS() {

        final String query =
//                "var functions = {};" +
//                "functions.waitForAngular = " +
//                        "function(rootSelector, callback) {" +
                "    var el = document.querySelector(rootSelector);" +
                "    try {" +
                "        if (window.getAngularTestability) {" +
                "            window.getAngularTestability(el).whenStable(callback);" +
                "            return;" +
                "        }" +
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
                "    }"
//                + "};"
                ;

        driver.executeAsyncScript("var callback = arguments[arguments.length - 1];" +
                "var rootSelector = 'body';" + query);

    }
}
