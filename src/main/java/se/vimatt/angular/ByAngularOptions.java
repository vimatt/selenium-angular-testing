package se.vimatt.angular;

import org.openqa.selenium.SearchContext;

/**
 * For finding the AngularJS specific element options
 * Extends BaseBy which provides the getObject method
 *
 * Created by victormattsson on 2016-04-05.
 */
public class ByAngularOptions extends BaseBy {

    private String options;

    public ByAngularOptions(String options) {
        this.options = options;
    }

    //Our getObject method uses the given SearchContext to find the ng-options elements in the browser
    //with Selenium's cssSelector and the provided string which is the name of the options element we
    //want to find, and returns it
    @Override
    protected Object getObject(SearchContext context) {
        return context.findElements(cssSelector("[ng-options='" + options + "'] > option"));
    }
}
