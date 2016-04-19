package se.vimatt.angular.element;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import se.vimatt.angular.driver.RMAngularDriver;

/**
 * Our own variation of the Selenium WebElement. It takes a RMAngularDriver which acts as a modified WebDriver
 * and also a WebElement. The purpose of this class is to make sure we can do waitForAngular before we do actions
 * on certain elements.
 */
public class AngularElement implements WebElement {

    private RMAngularDriver driver;
    private WebElement element;

    public AngularElement(RMAngularDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return element.getScreenshotAs(outputType);
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public void click() {
        //Here is an additional waitForAngular ran if the sync boolean in RMAngularDriver class is set to true.
        //Same goes for all the other methods in here with this code.
        driver.waitforAngularIfSync();
        element.click();
    }

    @Override
    public WebElement findElement(By by) {
        driver.waitforAngularIfSync();
        return element.findElement(by);
    }

    @Override
    public List<WebElement> findElements(By by) {
        driver.waitforAngularIfSync();
        return element.findElements(by);
    }

    @Override
    public String getAttribute(String attribute) {
        driver.waitforAngularIfSync();
        return element.getAttribute(attribute);
    }

    @Override
    public String getCssValue(String css) {
        return element.getCssValue(css);
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public void sendKeys(CharSequence... keys) {
        driver.waitforAngularIfSync();
        element.sendKeys(keys);
    }

    @Override
    public void submit() {
        element.submit();
    }

}
