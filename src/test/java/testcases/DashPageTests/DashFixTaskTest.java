package testcases.DashPageTests;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.dashboardPages.DashboardTab;
import setup.BaseTest;

/**
 * Created by Sergey on 01.02.2015.
 */
public class DashFixTaskTest extends BaseTest {

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
    private void correctedOneTaskStatusVerificationTest(){
        ProprtyLoader.writeToFile("\nCorrectedOneTaskStatusVerificationTest:");
        dashboardTab.powerFailTestStatusVerification("Done");
    }

    @Test
    private void correctedOneTaskColorVerificationTest(){
        ProprtyLoader.writeToFile("\nCorrectedOneTaskColorVerificationTest:");
         dashboardTab.powerFailTestColorVerification(blue); // blue green red yellow
    }

    @Test
    private void correctAllOtherStatusVerificationTest(){
        ProprtyLoader.writeToFile("\nCorrectAllOtherStatusVerificationTest:");
        dashboardTab.allTasksStatusVerification("Done");
    }

    @Test
    private void correctAllOtherColorVerificationTest(){
        ProprtyLoader.writeToFile("\nCorrectAllOtherColorVerificationTest:");
        dashboardTab.allTaskColorVerification(green);
    }
}
