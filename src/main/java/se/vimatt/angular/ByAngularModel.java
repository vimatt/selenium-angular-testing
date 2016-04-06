package se.vimatt.angular;

import org.openqa.selenium.SearchContext;

/**
 * Created by victormattsson on 2016-04-05.
 */
public class ByAngularModel extends BaseBy {

    private String model;

    public ByAngularModel(String model) {
        this.model = model;
    }

    @Override
    protected Object getObject(SearchContext context) {
        return context.findElements(cssSelector("[ng-model='" + model + "']"));
    }
}
