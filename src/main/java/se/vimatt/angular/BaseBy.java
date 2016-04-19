package se.vimatt.angular;

import org.openqa.selenium.*;

import java.util.List;

/**
 * The abstract class which overrides the findElement methods inherited from Selenium's By
 * <p>
 * Created by victor mattsson on 2016-04-05.
 */
abstract class BaseBy extends By {

    /**
     * Checks a given object and throws a Selenium NoSuchElementException if it's null or if it's an empty list
     *
     * @param obj the given object to be checked
     * @return the object if it passes the null check
     */
    protected final Object errorIfNull(Object obj) {
        if (obj == null || obj instanceof List && ((List) obj).isEmpty()) {
            throw new NoSuchElementException(this + " didn't have any matching elements at this place in the DOM");
        }
        return obj;
    }

    /**
     * Overridden findElement from Selenium By
     *
     * @param context the SearchContext given to find DOM elements
     * @return the first element in the list found by getObject()
     */
    @Override
    public WebElement findElement(SearchContext context) {
        return ((List<WebElement>) errorIfNull(getObject(context))).get(0);
    }

    /**
     * Overridden findElements from Selenium By
     *
     * @param context the SearchContext given to find DOM elements
     * @return the list of elements found by getObject
     */
    @Override
    public List<WebElement> findElements(SearchContext context) {
        return (List<WebElement>) errorIfNull(getObject(context));
    }

    /**
     * Abstract method used in child classes
     *
     * @param context the SearchContext given to find DOM elements
     * @return the element object
     */
    protected abstract Object getObject(SearchContext context);

}
