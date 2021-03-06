package testcases.TaskOfflineTest;

import framework.Loader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.offline_Form.TaskOfflinePage;
import setup.BaseTest;

public class OfflineFixIncorrectTaskTest extends BaseTest{

    private WebDriver driver;
    private TaskOfflinePage taskOfflinePage;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
        taskOfflinePage=new TaskOfflinePage(driver);
    }

    @Test
    private void fixIncorrectTaskTest(){
        Loader.logWritter("\nFixIncorrectTaskTest:");
        taskOfflinePage.fixPowerFailTestTask();
    }

    @Test
    private void syncTest(){
        taskOfflinePage.syncOperation();
    }

    @Test
    public void switchTabTest(){
        switchTab(driver);  //switchWindow(driver, 0);
    }
}
