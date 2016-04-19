package se.vimatt.angular;

import org.openqa.selenium.SearchContext;

/**
 * For finding the AngularJS specific element repeater
 * Extends BaseBy which provides the getObject method
 * <p>
 * Created by victor mattsson on 2016-04-05.
 */
public class ByAngularRepeater extends BaseBy {

    private String repeater;

    public ByAngularRepeater(String repeater) {
        this.repeater = repeater;
    }

    //Used to return a new ByAngularRepeaterRow when you want to find a specific element within a repeater
    public ByAngularRepeaterRow row(int row) {
        return new ByAngularRepeaterRow(repeater, row);
    }

    //Our getObject method uses the given SearchContext to find the ng-repeat elements in the browser
    //with Selenium's cssSelector and the provided string which is the name of the repeater element we
    //want to find, and returns it
    @Override
    protected Object getObject(SearchContext context) {
        return context.findElements(cssSelector("[ng-repeat='" + repeater + "']"));
    }

}
