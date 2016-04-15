package se.vimatt.angular;

import org.openqa.selenium.SearchContext;

/**
 * For finding the AngularJS specific element row within the repeater
 * Extends BaseBy which provides the getObject method
 *
 * Created by victormattsson on 2016-04-05.
 */
public class ByAngularRepeaterRow extends BaseBy {

    private final int row;
    private String repeater;

    public ByAngularRepeaterRow(String repeater, int row) {
        this.row = row;
        this.repeater = repeater;
    }

    //Used to return a new ByAngularRepeaterCell when you want to find a specific element within a repeater row
    public ByAngularRepeaterCell column(int column) {
        return new ByAngularRepeaterCell(column, row, repeater);
    }

    //Our getObject method uses the given SearchContext to find a specific element within the ng-repeat elements in the browser
    //with Selenium's cssSelector and the provided string which is the name of the repeater element we
    //want to find, and returns it. The integer row is the index of which element inside the repeater wa want to find
    protected Object getObject(SearchContext context) {
        return context.findElements(cssSelector("[ng-repeat='"+ repeater +"']:nth-child("+ row + ")"));
    }

}
