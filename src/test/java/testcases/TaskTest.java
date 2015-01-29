package testcases;

import framework.DataBase;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashPages.DashboardTab;
import pages.offline_Form.TaskOfflinePage;
import setup.BaseTest;


public class TaskTest extends BaseTest {

    private WebDriver driver;
    private TaskOfflinePage taskOfflinePage;
    private DashboardTab dashboardTab;
    private DataBase dataBase;


    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
        taskOfflinePage=new TaskOfflinePage(driver);
        //dashboardTab=new DashboardTab(driver);
        dataBase=new DataBase();
    }

    @Test
    public void submitSurveyTest(){
        taskOfflinePage.openTest();
    }
}
