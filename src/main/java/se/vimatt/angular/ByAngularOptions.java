package se.vimatt.angular;

import org.openqa.selenium.SearchContext;

/**
 * Created by victormattsson on 2016-04-05.
 */
public class ByAngularOptions extends BaseBy {

    private String options;

    public ByAngularOptions(String options) {
        this.options = options;
    }

    @Override
    protected Object getObject(SearchContext context) {
        return context.findElements(cssSelector("[ng-options='" + options + "'] > option"));
    }
}
