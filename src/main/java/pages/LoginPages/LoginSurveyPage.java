package pages.LoginPages;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSurveyPage extends LoginDashPage {

    private String logName, password="";

    public LoginSurveyPage(WebDriver driver){
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
}
