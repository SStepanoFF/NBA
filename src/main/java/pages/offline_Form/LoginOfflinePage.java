package pages.offline_Form;

import framework.Loader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.dashboardPages.LoginDashPage;

public class LoginOfflinePage extends LoginDashPage {

    public LoginOfflinePage(WebDriver driver){
        super(driver);
        if (Loader.loadProperty("portal").equals("1")){
            super.logName= Loader.loadProperty("prodLoginNameOffline");
            super.password= Loader.loadProperty("prodPass");
        }
        else {
            logName= Loader.loadProperty("testLoginName");
            password= Loader.loadProperty("testPass");
        }
        WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(Loader.loadProperty("timeout")));
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
