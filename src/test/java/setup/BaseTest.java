package setup;

import com.gargoylesoftware.htmlunit.WebWindow;
import framework.ProprtyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import webDriver.Driver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver driver;
    protected String survPageHandler;
    protected String dashPageHandler;

    @BeforeTest(alwaysRun = true)
    public void setUpTest(ITestContext context) {
        driver = Driver.getInstance();
        context.setAttribute(getCurrentTestCaseName(context), driver);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")), TimeUnit.SECONDS);
        ProprtyLoader.clearResultFile();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(ITestContext context) {
        driver = getDriver(context);
        if (driver != null) {
            driver.quit();
        }
   }

    protected WebDriver getDriver (ITestContext context) {
        return (WebDriver) context.getAttribute(getCurrentTestCaseName(context));
    }

    protected String getCurrentTestCaseName(ITestContext context) {
        return context.getCurrentXmlTest().getName();
    }

    protected void createNewWindow(WebDriver driver, String url) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])");
            switchWindow(driver,1);
            driver.navigate().to(url);
        } catch (Exception e) {
            ProprtyLoader.writeToFile("ERROR! Couldn't load second page");
        }
    }

    protected void createNewTab(WebDriver driver, String url) {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        switchWindow(driver,0);
        driver.navigate().to(url);
    }

    protected void switchWindow(WebDriver driver,int numberOfWindow) {
        String handle = driver.getWindowHandles().toArray()[numberOfWindow].toString();
        try{
            driver.switchTo().window(handle);
            driver.switchTo().activeElement();
        }catch (Exception e){
            ProprtyLoader.writeToFile("ERROR! Couldn't switch tab");
        }
    }

//    protected void switchFromSecondTabToFirst() {
//        try {
//            driver.switchTo().window(dashPageHandler);
//            driver.switchTo().activeElement();
//        } catch (Exception e) {
//            System.err.println("Couldn't get back to first page");
//        }
//    }
}
