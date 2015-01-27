package testcases.LoginTest;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPages.LoginSurveyPage;
import setup.BaseTest;

public class LoginSurvPageTest extends BaseTest {

    private LoginSurveyPage loginSurveyPage;
    private WebDriver driver;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
        createNewWindow(driver,ProprtyLoader.loadProperty("offlineUrl"));
        //createNewTab(driver, ProprtyLoader.loadProperty("offlineUrl"));
//        survPageHandler=driver.getWindowHandles().toArray()[0].toString();
//        switchWindow(driver,0);
//        driver.navigate().to(ProprtyLoader.loadProperty("offlineUrl"));
        loginSurveyPage=new LoginSurveyPage(driver);
    }

    @Test
    private void loginSurveyPageTest(){
        loginSurveyPage.logSurvPage();
    }

    @AfterClass
    public void teardown(ITestContext context){
        driver=getDriver(context);
        switchWindow(driver,0);
    }

}
