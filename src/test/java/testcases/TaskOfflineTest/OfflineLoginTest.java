package testcases.TaskOfflineTest;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.offline_Form.LoginOfflinePage;
import pages.offline_Form.MainOfflinePage;
import setup.BaseTest;

public class OfflineLoginTest extends BaseTest {

    private LoginOfflinePage loginOfflinePage;
    private WebDriver driver;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
        createNewTab(driver, ProprtyLoader.loadProperty("offlineUrl"));
        //createNewWindow(driver,ProprtyLoader.loadProperty("offlineUrl"));
        loginOfflinePage =new LoginOfflinePage(driver);
    }

    @Test
    private void loginSurveyPageTest(){
        loginOfflinePage.logSurvPage();
    }

    @Test
    private void syncTest(){
        MainOfflinePage mainOfflinePage=new MainOfflinePage(driver);
        mainOfflinePage.syncOperation();
    }

    @AfterClass
    public void teardown(ITestContext context){
        driver = getDriver(context);
        switchTab(driver);  //switchWindow(driver, 0);
    }

}
