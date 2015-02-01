package pages.dashboardPages;

import framework.DataBase;
import framework.ProprtyLoader;
import framework.Operations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DashboardTab extends Operations {
    private DataBase dataBase;
    private final String gameID=ProprtyLoader.loadProperty("gameID");

    public DashboardTab(WebDriver driver){
        super(driver);
        dataBase=new DataBase();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("dashTimeout")), TimeUnit.SECONDS);
        WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(ProprtyLoader.loadProperty("dashTimeout")));
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
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("dashTimeout")),TimeUnit.SECONDS);
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
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("dashTimeout")),TimeUnit.SECONDS);
        }
    }

    public void powerFailTestColorIdentification(String color){
        String locatorr="//td[descendant::button[contains(@id,'"+gameID+"')]]";
        WebElement powerFailTestColor=driver.findElement(By.xpath(locatorr));
        taskColorAssertion(powerFailTestColor.getCssValue("background-color"), color);
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
                    ProprtyLoader.writeToFile("Task status=" + realStatus+"\n");
                }catch (AssertionError e){
                    e.getStackTrace();
                    ProprtyLoader.writeToFile("ERROR! Incorrect task status: "+realStatus+"  must be: "+mustStatus+"\n");
                    throw new RuntimeException("Assert error status powerFailTestStatusIdentification");
                }finally {
                    driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("dashTimeout")),TimeUnit.SECONDS);
                }
    }

    private void taskColorAssertion(String realColor, String mustColor){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            Assert.assertTrue(realColor.contains(mustColor));     //Verify task status
            ProprtyLoader.writeToFile("Task color=" + realColor+"\n");
        }catch (AssertionError e){
            e.getStackTrace();
            ProprtyLoader.writeToFile("ERROR! Incorrect task color: "+realColor+"  must be: "+mustColor+"\n");
            throw new RuntimeException("Assert error status powerFailTestColorIdentification");
        }finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("dashTimeout")),TimeUnit.SECONDS);
        }
    }

}
