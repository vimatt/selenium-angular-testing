package se.vimatt.angular;

import org.openqa.selenium.SearchContext;

/**
 * For finding the AngularJS specific element cell/column within the repeater row
 * Extends BaseBy which provides the getObject method
 * <p>
 * Created by victor mattsson on 2016-04-05.
 */
public class ByAngularRepeaterCell extends BaseBy {

    private int column;
    private int row;
    private String repeater;

    public ByAngularRepeaterCell(int column, int row, String repeater) {
        this.column = column;
        this.row = row;
        this.repeater = repeater;
    }

    //Our getObject method uses the given SearchContext to find a specific element within the specific row in
    //ng-repeat elements in the browser, with Selenium's cssSelector, and returns it.
    //The the provided string is the name of the repeater element we want to find and the integer row is the index
    //of which element inside the repeater wa want to find, and integer column is the specific element inside the row
    //we want to find
    @Override
    protected Object getObject(SearchContext context) {
        return context.findElements(cssSelector("[ng-repeat='" + repeater + "']:nth-child(" + row + ") " +
                ":nth-child(" + column + ")"));
    }
}
