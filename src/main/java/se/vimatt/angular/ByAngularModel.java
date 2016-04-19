package se.vimatt.angular;

import org.openqa.selenium.SearchContext;

/**
 * For finding the AngularJS specific element model
 * Extends BaseBy which provides the getObject method
 * <p>
 * Created by victor mattsson on 2016-04-05.
 */
public class ByAngularModel extends BaseBy {

    private String model;

    public ByAngularModel(String model) {
        this.model = model;
    }

    //Our getObject method uses the given SearchContext to find the ng-model elements in the browser
    //with Selenium's cssSelector and the provided string which is the name of the model element we
    //want to find, and returns it
    @Override
    protected Object getObject(SearchContext context) {
        return context.findElements(cssSelector("[ng-model='" + model + "']"));
    }
}
