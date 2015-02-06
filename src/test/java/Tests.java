import framework.Loader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.dashboardPages.DashboardTab;
import pages.dashboardPages.LoginDashPage;
import setup.BaseTest;

/**
 * Created by sergii.stepanov on 22.01.2015.
 */
public class Tests extends BaseTest {
    WebDriver driver;
    private LoginDashPage loginDashPage;
    private DashboardTab dashboardTab;

    @BeforeClass
    public void setUp() {
        if (Loader.loadProperty("portal").equals("1")) {
            driver.navigate().to(Loader.loadProperty("prodUrl"));
        } else driver.navigate().to(Loader.loadProperty("testUrl"));
        loginDashPage =new LoginDashPage(driver);
    }

    @Test
    private void loginTest(){
        //dashboardTab=loginPage.loginNBA();
    }

    @Test
    private void selectDate(){
        dashboardTab.selectDate();
    }

    @Test
    private void compareGameNumber(){
        dashboardTab.numbGamesVerification();
    }

    @Test
    private void compareTaskNumber(){
        dashboardTab.numbTasksVerification();
    }
}
