package se.vimatt.angular;

import org.openqa.selenium.SearchContext;

/**
 * Created by victormattsson on 2016-04-05.
 */
public class ByAngularRepeaterRow extends BaseBy {

    private final int row;
    private String repeater;

    public ByAngularRepeaterRow(String repeater, int row) {
        this.row = row;
        this.repeater = repeater;
    }

    public ByAngularRepeaterCell column(int column) {
        return new ByAngularRepeaterCell(column, row, repeater);
    }

    protected Object getObject(SearchContext context) {
        return context.findElements(cssSelector("[ng-repeat='"+ repeater +"']:nth-child("+ row + ")"));
    }

}
