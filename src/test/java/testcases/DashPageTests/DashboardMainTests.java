package testcases.DashPageTests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.dashboardPages.DashboardTab;
import setup.BaseTest;


public class DashboardMainTests extends BaseTest {

    private DashboardTab dashboardTab;
    private WebDriver driver;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
        dashboardTab=new DashboardTab(driver);
    }

    @Test
    private void selectDate(){
        dashboardTab.selectDate();
    }

    @Test
    private void compareGameNumber(){
        dashboardTab.numbGamesVerification();
    }

    @Test
    private void compareTaskNumber(){
        dashboardTab.numbTasksVerification();
    }

    @AfterClass
    public void teardown(ITestContext context){
        driver = getDriver(context);
        switchTab(driver);  //switchWindow(driver, 0);
    }

}
