package testcases.DashPageTests;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.dashboardPages.DashboardTab;
import setup.BaseTest;

/**
 * Created by sergii.stepanov on 30.01.2015.
 */
public class DashTaskVerificationTest extends BaseTest {

    private WebDriver driver;
    private DashboardTab dashboardTab;

    private String blue="rgba(0, 109, 204, 1)";
    private String green="rgba(0, 128, 0, 1)";
    private String red="rgba(255, 0, 0, 1)";
    private String yellow="rgba(255, 125, 0, 1)";

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
        dashboardTab=new DashboardTab(driver);
    }

    @Test
    private void incorrectOneTaskStatusVerificationTest(){
        ProprtyLoader.writeToFile("\nIncorrectOneTaskStatusVerificationTest:");
        dashboardTab.powerFailTestStatusVerification("Incorrect");
    }

    @Test
    private void incorrectOneTaskColorVerificationTest(){
        ProprtyLoader.writeToFile("\nIncorrectOneTaskColorVerificationTest:");
        dashboardTab.powerFailTestColorVerification(yellow); // blue green red orange
    }

    @Test
    private void correctAllStatusVerificationTest(){
        ProprtyLoader.writeToFile("\nCorrectAllStatusVerificationTest:");
        dashboardTab.allTasksStatusVerification("Done");
    }

    @Test
    private void correctAllColorVerificationTest(){
        ProprtyLoader.writeToFile("\nCorrectAllColorVerificationTest:");
        dashboardTab.allTaskColorVerification(green);
    }

    @AfterClass
    public void teardown(ITestContext context){
        driver = getDriver(context);
        switchTab(driver);  //switchWindow(driver, 0);
    }
}
