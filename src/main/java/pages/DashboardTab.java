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
        dataBase=new DataBase();
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

    @FindBy (css="td[data-bind='html:startTime']")
    private List<WebElement> gamesList;

    public void selectDate(){
        datepicker.click();
        for (int i=0; i<date.size(); i++)
            if (ProprtyLoader.loadProperty("day").equals(date.get(i).getText())){
                date.get(i).click();
            }
    }

    public void numbTasksVerification(){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int dbRes=dataBase.taskCount(Integer.parseInt(ProprtyLoader.loadProperty("gameID")));
        try {
            Assert.assertTrue(taskList.size() == dbRes); //Compare count of task with DB
            ProprtyLoader.writeToFile("Number of tasks="+taskList.size()+"\n");
        }catch (junit.framework.AssertionFailedError e){
            e.getStackTrace();
            ProprtyLoader.writeToFile("ERROR! Incorrect tasks number: UI="+taskList.size()+"  DB="+dbRes+"\n");
        }finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")),TimeUnit.SECONDS);
        }
    }

    public void numbGamesVerification(){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int dbRes=dataBase.gamesCount();
        try {
            Assert.assertTrue(gamesList.size() == dbRes); //Compare count of games with DB
            ProprtyLoader.writeToFile("Number of games="+gamesList.size()+"\n");
        }catch (junit.framework.AssertionFailedError e){
            e.getStackTrace();
            ProprtyLoader.writeToFile("ERROR! Incorrect games number: UI="+gamesList.size()+"  DB="+dbRes+"\n");
        }finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")),TimeUnit.SECONDS);
        }
    }

}
