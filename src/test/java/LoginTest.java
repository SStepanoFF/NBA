import framework.DataBase;
import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardTab;
import pages.LoginPage;
import webDriver.Driver;
import java.util.concurrent.TimeUnit;


public class LoginTest {
    public WebDriver driver;
    private LoginPage loginPage;

    @BeforeTest
    public void setUp() {
        driver = Driver.getInstance();
        if (ProprtyLoader.loadProperty("portal").equals("1")){
            driver.get(ProprtyLoader.loadProperty("prodUrl"));
        }
        else driver.get(ProprtyLoader.loadProperty("testUrl"));
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")), TimeUnit.SECONDS);
        loginPage=new LoginPage(driver);
        ProprtyLoader.clearResultFile();
    }

    @Test
    private void loginTest(){
        loginPage.loginNBA();
    }

}
