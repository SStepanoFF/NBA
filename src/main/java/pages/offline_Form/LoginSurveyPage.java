package pages.offline_Form;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.dashboardPages.LoginDashPage;

import java.util.concurrent.TimeUnit;

public class LoginSurveyPage extends LoginDashPage {

    public LoginSurveyPage(WebDriver driver){
        super(driver);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("survTimeout")), TimeUnit.SECONDS);
        WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(ProprtyLoader.loadProperty("survTimeout")));
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
