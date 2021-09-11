package decorator.custom.webelements;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AbstractElement implements WebElement {

    private final static Integer MAX_ATTEMPTS_COUNT = 3;
    private final static ThreadLocal<Integer> attemptsCount = new ThreadLocal<>();
    protected WebDriver driver;
    protected WebElement element;

    public AbstractElement(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    private static Boolean canNextAttempt() {
        if (AbstractElement.attemptsCount.get() == null) {
            AbstractElement.attemptsCount.set(0);
        }
        if (AbstractElement.attemptsCount.get() < AbstractElement.MAX_ATTEMPTS_COUNT) {
            AbstractElement.attemptsCount.set(AbstractElement.attemptsCount.get() + 1);
            return true;
        } else {
            AbstractElement.attemptsCount.set(0);
            return false;
        }
    }

    public static void performAndWaitForUpdate(
        WebDriver driver,
        Runnable actionToPerform,
        long timeOutInSeconds
    ) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        actionToPerform.run();
        wait.until(
            webDriver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete")
        );
    }

    public static void performAndWaitForUpdate(
        WebDriver driver,
        Runnable actionToPerform,
        WebElement elementToWaitForUpdate,
        long timeOutInSeconds
    ) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        actionToPerform.run();
        try {
            wait.until(ExpectedConditions.stalenessOf(elementToWaitForUpdate));
        } catch (TimeoutException ignored) {}
    }

    public static void performAndWaitForUpdate(
        WebDriver driver,
        Runnable actionToPerform,
        By locatorToWaitForElementUpdate,
        long timeOutInSeconds
    ) {
        WebElement elementToWaitForUpdate = driver.findElement(locatorToWaitForElementUpdate);
        performAndWaitForUpdate(driver, actionToPerform, elementToWaitForUpdate, timeOutInSeconds);
    }

    public void safeAction(Runnable actionToPerform, long timeOutInSeconds) {
        try {
            actionToPerform.run();
        } catch (ElementClickInterceptedException | StaleElementReferenceException ignored) {
            if (AbstractElement.canNextAttempt()) {
                new WebDriverWait(driver, timeOutInSeconds)
                    .until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(element)));
                safeAction(actionToPerform, timeOutInSeconds);
            }
        }
    }

    public void moveToElementAndSafeAction(
        WebElement target,
        Runnable actionToPerform,
        long timeOutInSeconds
    ) {
        Actions actions = new Actions(driver);
        actions.moveToElement(target).perform();
        safeAction(actionToPerform, timeOutInSeconds);
    }

    @Override
    public void click() {
        element.click();
    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        element.sendKeys();
    }

    @Override
    public void clear() {
        element.clear();
    }


    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public String getCssValue(String s) {
        return element.getCssValue(s);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return getScreenshotAs(outputType);
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return element.getAttribute(s);
    }
}
