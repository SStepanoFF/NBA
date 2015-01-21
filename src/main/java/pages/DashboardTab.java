package pages;

import framework.DataBase;
import framework.ProprtyLoader;
import framework.Operations;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DashboardTab extends Operations {
    private DataBase dataBase;

    public DashboardTab(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(ProprtyLoader.loadProperty("timeout")));
        wait.until(ExpectedConditions.visibilityOf(dashTab));
        dashTab.click();
    }

    @FindBy (linkText = "Dashboard")
    private WebElement dashTab;

    @FindBy (css="td[data-bind='html:title']")
    private List<WebElement> taskList;

    //region Datepicker WebElements
    @FindBy (css=".ui-datepicker-trigger")
    private WebElement datepicker;

    @FindBy (css = "td[data-handler='selectDay']")
    private List<WebElement> date;

    @FindBy (css=".ui-datepicker-month")
    private WebElement month;
    //endregion

    private void selectDate(){
        datepicker.click();
        for (int i=0; i<date.size(); i++)
            if (ProprtyLoader.loadProperty("date").equals(date.get(i).getText())){
                date.get(i).click();
            }
    }

    public void openGame(){
        dataBase=new DataBase();
        selectDate();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            Assert.assertTrue(taskList.size() == dataBase.taskCount(Integer.parseInt(ProprtyLoader.loadProperty("gameID")))); //Compare count of task with DB
        }catch (junit.framework.AssertionFailedError e){
            ProprtyLoader.writeToFile("Incorrect tasks number\n");
        }finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")),TimeUnit.SECONDS);
        }
    }
}
