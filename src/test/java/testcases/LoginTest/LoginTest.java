package testcases.LoginTest;

import framework.ProprtyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPages.LoginDashPage;
import pages.LoginPages.LoginSurveyPage;
import setup.BaseTest;


public class LoginTest extends BaseTest {

    private LoginDashPage loginDashPage;
    private WebDriver driver;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
       if (ProprtyLoader.loadProperty("portal").equals("1")) {
           driver.navigate().to(ProprtyLoader.loadProperty("prodUrl"));
        } else driver.navigate().to(ProprtyLoader.loadProperty("testUrl"));
        super.dashPageHandler=driver.getWindowHandle();
        loginDashPage =new LoginDashPage(driver);
    }

    @Test
    private void loginDashPageTest(){
        loginDashPage.loginDashPage();
    }
}
