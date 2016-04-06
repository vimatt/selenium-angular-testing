package se.vimatt.angular;

import org.openqa.selenium.SearchContext;

/**
 * Created by victormattsson on 2016-04-05.
 */
public class ByAngularRepeater extends BaseBy {

    private String repeater;

    public ByAngularRepeater(String repeater) {
        this.repeater = repeater;
    }

    public ByAngularRepeaterRow row(int row) {
        return new ByAngularRepeaterRow(repeater, row);
    }

    @Override
    protected Object getObject(SearchContext context) {
        return context.findElements(cssSelector("[ng-repeat='"+ repeater +"']"));
    }

}
