package testcases.DashPageTests;

import framework.Loader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.dashboardPages.DashboardTab;
import setup.BaseTest;

import java.io.IOException;


public class DashMainPageTests extends BaseTest {

    private DashboardTab dashboardTab;
    private WebDriver driver;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
        dashboardTab=new DashboardTab(driver);
    }

    @Test
    private void selectDateTest(){
        Loader.logWritter("\nSelectDateTest:");
        dashboardTab.selectDate();
    }

    @Test
    private void compareGameNumberTest(){
        Loader.logWritter("\nCompareGameNumberTest:");
        dashboardTab.numbGamesVerification();
    }

    @Test
    private void compareTaskNumberTest(){
        Loader.logWritter("\nCompareTaskNumberTest:");
        dashboardTab.numbTasksVerification();
    }

    @Test
    public void switchTabTest(){
        switchTab(driver);  //switchWindow(driver, 0);
    }

//    @AfterClass
//    public void afterClass(ITestResult result) throws IOException {
//        takeScreenShot(result);
//    }

}
