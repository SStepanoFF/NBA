package pages.offline_Form;

import framework.Operations;
import framework.ProprtyLoader;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by sergii.stepanov on 29.01.2015.
 */
public class MainOfflinePage extends Operations {

    public String submitTaskDate;

    public MainOfflinePage(WebDriver driver){
        super(driver);
        WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(ProprtyLoader.loadProperty("timeout")));
        wait.until(ExpectedConditions.visibilityOf(taskTab));
    }

    @FindBy(id="task")
    private WebElement taskTab;

    @FindBy (css="span[title='Show Menu']")
    private WebElement menuBtn;
    @FindBy (id="syncBtn")
    private WebElement syncBtn;

    @FindBy (id="loaderDialog")
    private WebElement loadingDialog;

    public void syncOperation(){
        waitDownLoad();
        menuBtn.click();
        syncBtn.click();
        waitDownLoad();
    }

    public void waitDownLoad(){
        if (isElementPresent(loadingDialog)){
            try {
                WebDriverWait wait = new WebDriverWait(driver, 120);
                wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(loadingDialog)));
            }catch (StaleElementReferenceException e){}
        }
    }
}
