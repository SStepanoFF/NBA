package testcases.TaskOfflineTest;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.offline_Form.TaskOfflinePage;
import setup.BaseTest;


public class OfflineSurvAnswerTest extends BaseTest {

    private WebDriver driver;
    private TaskOfflinePage taskOfflinePage;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
        taskOfflinePage=new TaskOfflinePage(driver);
    }

    @Test
    public void submitWithOneIncorrectTaskTest(){
        taskOfflinePage.powerFailIncorrect();
    }

    @Test
    public void submitWithAllOtherCorrectTaskTest(){
        taskOfflinePage.createAllCorrectTasks();
    }

    @Test
    private void syncTest(){
        taskOfflinePage.syncOperation();
    }

    @AfterClass
    public void teardown(ITestContext context){
        driver = getDriver(context);
        switchTab(driver);  //switchWindow(driver, 0);
    }
}
