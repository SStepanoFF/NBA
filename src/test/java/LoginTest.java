
import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import webDriver.Driver;
import java.util.concurrent.TimeUnit;


public class LoginTest extends BaseTest{

    private LoginPage loginPage;
    //private WebDriver driver;

//    public LoginTest(WebDriver driver){
//        super(driver);
//    }

    @BeforeClass
    public void setUp() {  //ITestContext contex
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
