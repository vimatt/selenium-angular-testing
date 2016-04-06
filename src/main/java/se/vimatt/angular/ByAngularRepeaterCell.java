package se.vimatt.angular;

import org.openqa.selenium.SearchContext;

/**
 * Created by victormattsson on 2016-04-05.
 */
public class ByAngularRepeaterCell extends BaseBy{

    private int column;
    private int row;
    private String repeater;

    public ByAngularRepeaterCell(int column, int row, String repeater) {
        this.column = column;
        this.row = row;
        this.repeater = repeater;
    }

    @Override
    protected Object getObject(SearchContext context) {
        return context.findElements(cssSelector("[ng-repeat='"+ repeater +"']:nth-child("+ row + ") " +
                ":nth-child("+ column + ")"));
    }
}
