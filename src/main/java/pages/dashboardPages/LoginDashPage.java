package pages.dashboardPages;

import framework.ProprtyLoader;
import framework.Operations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class LoginDashPage extends Operations {

    public LoginDashPage(WebDriver driver){
        super(driver);
        WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(ProprtyLoader.loadProperty("timeout")));
        wait.until(ExpectedConditions.visibilityOf(logButt));
    }

    @FindBy (id="loginButton")
    private WebElement logButt;

    @FindBy (id="login")
    private WebElement loginField;

    @FindBy (id="password")
    private WebElement passField;

    public void loginDashPage(){
        login(loginField,passField,logButt);
    }

    public void login(WebElement logField1,WebElement passField1, WebElement logBtn){
        String logName, password="";
        if (ProprtyLoader.loadProperty("portal").equals("1")){
            logName=ProprtyLoader.loadProperty("prodLoginName");
            password=ProprtyLoader.loadProperty("prodPass");
        }
        else {
            logName=ProprtyLoader.loadProperty("testLoginName");
            password=ProprtyLoader.loadProperty("testPass");
        }
        logField1.sendKeys(logName);
        passField1.sendKeys(password);
        logBtn.click();
    }
}
