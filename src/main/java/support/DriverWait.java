package support;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverWait {

    private WebDriver driver;
    private int timeOut = 10;

    public DriverWait(WebDriver driver) {
        this.driver = driver;
    }

    public Wait<WebDriver> waitElement(){
        waitForJavaScriptCondition().waitForJQueryProcessing();
        return new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(InvalidElementStateException.class);
    }

    private DriverWait waitForJavaScriptCondition() {
        boolean jscondition = false;
        String javaScript = "return (xmlhttp.readyState >= 2 && xmlhttp.status == 200)";
        try{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            new WebDriverWait(driver, timeOut) {
            }.until(new ExpectedCondition<Boolean>() {

                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject).executeScript(javaScript);
                }
            });
            jscondition =  (Boolean) ((JavascriptExecutor) driver).executeScript(javaScript);
            driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS); //reset implicitlyWait
        } catch (JavascriptException e) { }
        return this;
    }

    private DriverWait waitForJQueryProcessing(){
        boolean jQcondition = false;
        try{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
            new WebDriverWait(driver, timeOut) {
            }.until(new ExpectedCondition<Boolean>() {

                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject).executeScript("return jQuery.active == 0");
                }
            });
            jQcondition = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS); //reset implicitlyWait
        } catch (Exception e) { }
        return this;
    }


    public WebElement waitClickableElement(WebElement element){
        return waitElement().until(ExpectedConditions.elementToBeClickable(element));
    }

    public List<WebElement> elementList(List<WebElement> elements){
        try {
            return waitElement().until(ExpectedConditions.visibilityOfAllElements(elements));
        }catch (WebDriverException e) {
            Utils.sleep(3);
            return elements;
        }
    }
}
