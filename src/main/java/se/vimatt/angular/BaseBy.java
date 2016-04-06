package se.vimatt.angular;

import org.openqa.selenium.*;

import java.util.List;

/**
 * Created by victormattsson on 2016-04-05.
 */
abstract class BaseBy extends By {

    protected final Object errorIfNull(Object obj) {
        if (obj == null || obj instanceof List && ((List) obj).isEmpty()) {
            throw new NoSuchElementException(this + " didn't have any matching elements at this place in the DOM");
        }
        return obj;
    }

    @Override
    public WebElement findElement(SearchContext context) {
        return ((List<WebElement>) errorIfNull(getObject(context))).get(0);
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        return (List<WebElement>) errorIfNull(getObject(context));
    }

    protected abstract Object getObject(SearchContext context);

}
