package testcases.LoginTest;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPages.LoginDashPage;
import pages.LoginPages.LoginSurveyPage;
import setup.BaseTest;


public class LoginTest extends BaseTest {

    private LoginDashPage loginDashPage;
    private LoginSurveyPage loginSurveyPage;
    private WebDriver dashDriver;
    private WebDriver survDriver;

    @BeforeClass
    public void setup(ITestContext context) {
        dashDriver = getDriver(context);
        survDriver=getDriver(context);
       if (ProprtyLoader.loadProperty("portal").equals("1")) {
           dashDriver.navigate().to(ProprtyLoader.loadProperty("prodUrl"));
        } else dashDriver.navigate().to(ProprtyLoader.loadProperty("testUrl"));
        survDriver.navigate().to(ProprtyLoader.loadProperty("offlineUrl"));
        loginDashPage =new LoginDashPage(dashDriver);
        loginSurveyPage=new LoginSurveyPage(survDriver);
    }

    @Test
    private void loginDashPageTest(){
        loginDashPage.loginNBA();
    }

    @Test
    private void loginSurveyPageTest(){loginSurveyPage.loginNBA();}

}
