package testcases;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.BaseTest;


public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private WebDriver driver;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = getDriver(context);
       if (ProprtyLoader.loadProperty("portal").equals("1")) {
            driver.navigate().to(ProprtyLoader.loadProperty("prodUrl"));
        } else driver.navigate().to(ProprtyLoader.loadProperty("testUrl"));
        loginPage=new LoginPage(driver);
    }

    @Test
    private void loginTest(){
        loginPage.loginNBA();
    }

}
