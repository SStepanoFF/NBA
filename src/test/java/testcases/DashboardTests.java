package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardTab;
import setup.BaseTest;


public class DashboardTests extends BaseTest {

    private DashboardTab dashboardTab;
    private WebDriver driver;
    private String blue="rgba(0, 109, 204, 1)";
    private String green="rgba(0, 128, 0, 1)";
    private String red="rgba(255, 0, 0, 1)";

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

    @Test
    private void statusIdentificationTest(){ dashboardTab.powerFailTestStatusIdentification("Done");}

    @Test
    private void colorIdentificationTest(){
        dashboardTab.powerFailTestColorIdentification(blue); // blue green red
    }
}
