package testcases.DashPageTests;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.dashboardPages.LoginDashPage;
import setup.BaseTest;


public class DashLoginTest extends BaseTest {

    private LoginDashPage loginDashPage;
    private WebDriver driver;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
       if (ProprtyLoader.loadProperty("portal").equals("1")) {
           driver.navigate().to(ProprtyLoader.loadProperty("prodUrl"));
        } else driver.navigate().to(ProprtyLoader.loadProperty("testUrl"));
        loginDashPage =new LoginDashPage(driver);
    }

    @Test
    private void loginDashPageTest(){
        loginDashPage.loginDashPage();
    }
}
