package pages.offline_Form;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.dashboardPages.LoginDashPage;

import java.util.concurrent.TimeUnit;

public class LoginOfflinePage extends LoginDashPage {

    public LoginOfflinePage(WebDriver driver){
        super(driver);
        WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(ProprtyLoader.loadProperty("timeout")));
        wait.until(ExpectedConditions.visibilityOf(logButt));
    }

    @FindBy(id="loginButton")
    private WebElement logButt;

    @FindBy (id="usernameField")
    private WebElement loginField;

    @FindBy (id="passwordField")
    private WebElement passField;

    public void logSurvPage(){
        login(loginField,passField,logButt);
    }
}
