package pages;

import framework.DataBase;
import framework.ProprtyLoader;
import framework.Operations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DashboardTab extends Operations {
    private DataBase dataBase;
    private final String gameID=ProprtyLoader.loadProperty("gameID");
    private final String game="11";

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

    //region Task WebElements
    @FindBy (css="td[data-bind='html:title']")
    private List<WebElement> taskList;

    @FindBy (css = "td button")
    private List<WebElement> statusButtList;

    private final String locatorr="//td[descendant::button[contains(@id,'"+game+"')]]";
    @FindBy(xpath = locatorr)
    private List<WebElement> colorStatus;

    @FindBy (xpath="//td[text()=('Power Failure Test')]/following-sibling::td/button")        ////td[text()=("Power Failure Test")]/following-sibling::*[2] подкраш поле
    private List<WebElement> powerFailTestStatus;        ////td[text()=("Power Failure Test")]/following-sibling::td/button конкретный баттон
    //endregion

    public void selectDate(){
        datepicker.click();
        for (int i=0; i<date.size(); i++)
            if (date.get(i).getText().equals(ProprtyLoader.loadProperty("date").substring(8)) && //verify day
                    Integer.parseInt(date.get(i).getAttribute("data-month"))==0){                //verify month
                date.get(i).click();
            }
    }

    public void numbTasksVerification(){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int dbCountTask=dataBase.taskCount(gameID);
        try {
            Assert.assertTrue(taskList.size() == dbCountTask); //Compare count of task with DB
            ProprtyLoader.writeToFile("Number of tasks="+taskList.size()+"\n");
        }catch (AssertionError e){
            e.getStackTrace();
            ProprtyLoader.writeToFile("ERROR! Incorrect tasks number: UI="+taskList.size()+"  DB="+dbCountTask+"\n");
            throw new RuntimeException("Assert error numbTasksVerification");
        }finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")),TimeUnit.SECONDS);
        }
    }

    public void numbGamesVerification(){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int dbCountGame=dataBase.gamesCount(ProprtyLoader.loadProperty("date"));
        try {
            Assert.assertTrue(gamesList.size() == dbCountGame); //Compare count of games with DB
            ProprtyLoader.writeToFile("Number of games="+gamesList.size()+"\n");
        }catch (AssertionError e){
            e.getStackTrace();
            ProprtyLoader.writeToFile("ERROR! Incorrect games number: UI="+gamesList.size()+"  DB="+dbCountGame+"\n");
            throw new RuntimeException("Assert error numbGamesVerification");
        }finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")),TimeUnit.SECONDS);
        }
    }

    public void colorIdentification(){
        //((JavascriptExecuter)driver).executeScript("return arguments[0].style.background-color", element);
        colorStatus.get(1).getCssValue("background-color");  //"rgba(0, 109, 204, 1)"- blue  "rgba(0, 128, 0, 1)" -green    "rgba(255, 0, 0, 1)" -red
        //colorStatus.getText();
    }

    public void powerFailTestStatusIdentification(String status){
        taskStatusAssertion(getTaskStatus(powerFailTestStatus), status);
    }

    public void allTasksStatusVerification(String status){  //Проверка статуса всех тасок если все они одинаковы
        //getTaskStatus(statusButtList,status);
    }

    private String getTaskStatus(List<WebElement> taskName){
        String taskStatus="Error status";
        for(WebElement element: taskName) {
            if (element.getAttribute("id").contains(gameID)) {  //select button by gameID
                taskStatus = element.getText();
            }
        }
        return  taskStatus;
    }

    private void taskStatusAssertion(String realStatus, String mustStatus){
                driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                try {
                    Assert.assertTrue(realStatus.contains(mustStatus));     //Verify task status
                    ProprtyLoader.writeToFile("Game status=" + realStatus+"\n");
                }catch (AssertionError e){
                    e.getStackTrace();
                    ProprtyLoader.writeToFile("ERROR! Incorrect games status: "+realStatus+"  must be: "+mustStatus+"\n");
                    throw new RuntimeException("Assert error status powerFailTestStatusIdentification");
                }finally {
                    driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")),TimeUnit.SECONDS);
                }
    }

}
