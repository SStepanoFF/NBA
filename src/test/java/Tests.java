import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashPages.DashboardTab;
import pages.DashPages.LoginDashPage;
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
        if (ProprtyLoader.loadProperty("portal").equals("1")) {
            driver.navigate().to(ProprtyLoader.loadProperty("prodUrl"));
        } else driver.navigate().to(ProprtyLoader.loadProperty("testUrl"));
        loginDashPage =new LoginDashPage(driver);
    }

    @Test
    private void loginTest(){
        //dashboardTab=loginPage.loginNBA();
    }

    @Test
    private void selectDate(){
        dashboardTab.selectDate();
        dashboardTab.powerFailTestStatusIdentification("Done");
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
