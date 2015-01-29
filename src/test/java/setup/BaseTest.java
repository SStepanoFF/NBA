package setup;

import com.gargoylesoftware.htmlunit.WebWindow;
import framework.ProprtyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import webDriver.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUpTest(ITestContext context) {
        driver = Driver.getInstance();
        context.setAttribute(getCurrentTestCaseName(context), driver);
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

    protected void createNewTab(WebDriver driver, String url) {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        driver.navigate().to(url);
    }

    protected void switchTab(WebDriver driver) {
        try{
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");
            ArrayList tabs = new ArrayList(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0).toString());  //driver.switchTo().defaultContent();
        }catch (Exception e){
            ProprtyLoader.writeToFile("ERROR! Couldn't switch tab");
        }
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

    protected void switchWindow(WebDriver driver, int number) {
        try{
            driver.switchTo().window(driver.getWindowHandles().toArray()[number].toString());
        }catch (Exception e){
            ProprtyLoader.writeToFile("ERROR! Couldn't switch tab");
        }
    }
}
