package testcases.DashPageTests;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.dashboardPages.DashboardTab;
import setup.BaseTest;


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
        ProprtyLoader.writeToFile("\nSelectDateTest:");
        dashboardTab.selectDate();
    }

    @Test
    private void compareGameNumberTest(){
        ProprtyLoader.writeToFile("\nCompareGameNumberTest:");
        dashboardTab.numbGamesVerification();
    }

    @Test
    private void compareTaskNumberTest(){
        ProprtyLoader.writeToFile("\nCompareTaskNumberTest:");
        dashboardTab.numbTasksVerification();
    }

    @AfterClass
    public void teardown(ITestContext context){
        driver = getDriver(context);
        switchTab(driver);  //switchWindow(driver, 0);
    }

}
