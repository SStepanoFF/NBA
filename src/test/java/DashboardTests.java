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

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class DashboardTests extends LoginTest{
    private WebDriver driver;
    private DashboardTab dashboardTab;

    @BeforeTest
    public void setUp() {
        driver = Driver.getInstance();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")), TimeUnit.SECONDS);
        dashboardTab=new DashboardTab(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    private void openGameTest(){
        dashboardTab.openGame();
    }

}
