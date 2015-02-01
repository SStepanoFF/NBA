package testcases.TaskOfflineTest;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.offline_Form.TaskOfflinePage;
import setup.BaseTest;

/**
 * Created by Sergey on 01.02.2015.
 */
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
        taskOfflinePage.fixPowerFailTestTask();
    }

    @Test
    private void syncTest(){
        taskOfflinePage.syncOperation();
    }

//    @AfterClass
//    public void teardown(ITestContext context){
//        driver = getDriver(context);
//        switchTab(driver);  //switchWindow(driver, 0);
//    }
}
