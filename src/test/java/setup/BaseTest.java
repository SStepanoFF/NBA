package setup;

import framework.Loader;
import framework.utils.XMLDataProvider;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import webDriver.Driver;

import java.io.File;
import java.io.IOException;

import org.joda.time.format.DateTimeFormatter;
import framework.utils.Report;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class BaseTest {

    private WebDriver driver;
    public static int step;
    protected XMLDataProvider dataProvider;

    @BeforeTest(alwaysRun = true)
    public void setUpTest(ITestContext context) {
        driver = Driver.getInstance();
        context.setAttribute(getCurrentTestCaseName(context), driver);
        Loader.clearResultFile();
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
            Loader.logWritter("ERROR! Couldn't switch tab");
        }
    }

    protected void createNewWindow(WebDriver driver, String url) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])");
            switchWindow(driver,1);
            driver.navigate().to(url);
        } catch (Exception e) {
            Loader.logWritter("ERROR! Couldn't load second page");
        }
    }

    protected void switchWindow(WebDriver driver, int number) {
        try{
            driver.switchTo().window(driver.getWindowHandles().toArray()[number].toString());
        }catch (Exception e){
            Loader.logWritter("ERROR! Couldn't switch tab");
        }
    }

//    @AfterMethod
//    public void takeScreenShot(ITestResult result) throws IOException {
//        DateTime dt = new DateTime();
//        DateTimeFormatter dateFormat = DateTimeFormat.forPattern(Loader.loadProperty("dateFormat"));
//        String resultDateTime = dateFormat.print(dt);
//              if (result.isSuccess()) {     //условие делать скриншот если тест FAIL
//                Report.log(++step, "Test: " + result.getTestContext().getCurrentXmlTest().getName()
//                        + "_" + result.getName(), Report.State.PASS );
//                return;
//            }
//
//            File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            File outputDir = new File(result.getTestContext().getOutputDirectory());
//            File saved=new File(outputDir.getParent() + "\\screenshots\\", result.getTestContext().getCurrentXmlTest().getName() + "_" + result.getName()+".png");
//            FileUtils.copyFile(f, saved);
//
//    }
}
