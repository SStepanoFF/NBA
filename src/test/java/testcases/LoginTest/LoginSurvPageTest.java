package testcases.LoginTest;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.offline_Form.LoginSurveyPage;
import pages.offline_Form.MainOfflinePage;
import setup.BaseTest;

public class LoginSurvPageTest extends BaseTest {

    private LoginSurveyPage loginSurveyPage;
    private WebDriver driver;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
        createNewTab(driver, ProprtyLoader.loadProperty("offlineUrl"));  //createNewWindow(driver,ProprtyLoader.loadProperty("offlineUrl"));
        loginSurveyPage=new LoginSurveyPage(driver);
    }

    @Test
    private void loginSurveyPageTest(){
        loginSurveyPage.logSurvPage();
    }

    @Test
    private void syncTest(){
        MainOfflinePage mainOfflinePage=new MainOfflinePage(driver);
        mainOfflinePage.syncOperation();
    }

//    @AfterClass
//    public void teardown(ITestContext context){
//        driver = getDriver(context);
//        switchTab(driver);  //switchWindow(driver, 0);
//    }

}
