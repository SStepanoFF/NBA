package testcases.DashPageTests;

import framework.Loader;
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
       if (Loader.loadProperty("portal").equals("1")) {
           driver.navigate().to(Loader.loadProperty("prodUrl"));
        } else driver.navigate().to(Loader.loadProperty("testUrl"));
        loginDashPage =new LoginDashPage(driver);
    }

    @Test
    private void loginDashPageTest(){
        loginDashPage.loginDashPage();
    }
}
