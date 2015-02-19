package testcases.DashPageTests;

import framework.Loader;
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
    private String orange="rgba(255, 125, 0, 1)";

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
        dashboardTab=new DashboardTab(driver);
    }

    @Test
    private void correctedOneTaskStatusVerificationTest(){
        Loader.logWritter("\nCorrectedOneTaskStatusVerificationTest:");
        dashboardTab.powerFailTestStatusVerification("Done");
    }

    @Test
    private void correctedOneTaskColorVerificationTest(){
        Loader.logWritter("\nCorrectedOneTaskColorVerificationTest:");
         dashboardTab.powerFailTestColorVerification(blue); // blue green red yellow
    }

    @Test
    private void correctAllOtherStatusVerificationTest(){
        Loader.logWritter("\nCorrectAllOtherStatusVerificationTest:");
        dashboardTab.allTasksStatusVerification("Done");
    }

    @Test
    private void correctAllOtherColorVerificationTest(){
        Loader.logWritter("\nCorrectAllOtherColorVerificationTest:");
        dashboardTab.allTaskColorVerification(green);
    }

    @Test
    private void gameStatusVerificationCompleteTest(){
        Loader.logWritter("\ngameStatusVerificationTest:");
        // GameStatus gameStatus=GameStatus.Overdue;
        dashboardTab.gameStatusVerification(2);     //0-overdue, 1-Done, 2-game complete with incorrect
    }
}
