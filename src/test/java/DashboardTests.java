import framework.DataBase;
import framework.ProprtyLoader;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardTab;
import pages.LoginPage;
import webDriver.Driver;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class DashboardTests extends BaseTest{

    private DashboardTab dashboardTab;
    //WebDriver driver;

//    public DashboardTests(WebDriver driver){
//        super(driver);
//    }

    @BeforeClass
    public void setUp1() {
        dashboardTab=new DashboardTab(driver);
    }

    @Test
    private void openGameTest(){

    }

}
