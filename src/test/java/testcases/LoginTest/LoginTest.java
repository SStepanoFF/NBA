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

    //http://automated-testing.info/t/webdriver-features-robota-s-neskolkimi-oknami-vkladkami-odnovremenno-pri-pomoshhi-selenium-web-driver/2289

    @BeforeClass
    public void setup(ITestContext context) {
        dashDriver = getDriver(context);
       if (ProprtyLoader.loadProperty("portal").equals("1")) {
           dashDriver.navigate().to(ProprtyLoader.loadProperty("prodUrl"));
        } else dashDriver.navigate().to(ProprtyLoader.loadProperty("testUrl"));
        loginDashPage =new LoginDashPage(dashDriver);
    }

    @Test
    private void loginDashPageTest(){
        loginDashPage.loginNBA();
    }

    @Test
    private void loginSurveyPageTest(){
        loginSurveyPage.loginNBA();
    }

}
