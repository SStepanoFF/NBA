package pages.dashboardPages;

import framework.Operations;
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
    @FindBy (linkText = "Prev")
    private WebElement prevMonthBtn;
    //endregion

    @FindBy (css="td[data-bind='html:startTime']")
    private List<WebElement> gamesList;

    //region Task WebElements
//    @FindBy (css="td[data-bind='html:title']")
//    private List<WebElement> taskList;
    @FindBy (css = "td button")
    private List<WebElement> statusButtList;
    @FindBy (xpath="//td[text()=('Power Failure Test')]/following-sibling::td/button")        ////td[text()=("Power Failure Test")]/following-sibling::*[2] подкраш поле
    private List<WebElement> powerFailTestStatus;        ////td[text()=("Power Failure Test")]/following-sibling::td/button конкретный баттон
    //endregion

    public void selectDate(){
        datepicker.click();
        String dateProp=ProprtyLoader.loadProperty("date");
        String day=dateProp.substring(8);                                           //get day from property file
        String month=dateProp.substring(5, 7);     //get month from property file
        if (Integer.parseInt(month.substring(0, 1))==0) {
            month=Integer.toString(Integer.parseInt(month.substring(1))- 1);  //correct month type according to UI
        }
        boolean findDate=false;
        while (!findDate){
            for (int i = 0; i < date.size(); i++) {
                if (date.get(i).getText().equals(day) && (date.get(i).getAttribute("data-month")).equals(month)) {         //verify day and month
                    date.get(i).click();
                    findDate=true;
                    ProprtyLoader.writeToFile("Date was selected");
                }
            }
            if(!findDate) prevMonthBtn.click();
        }
    }

    public void numbTasksVerification(){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int dbCountTask=dataBase.taskCount(gameID);     //get number of tasks from DB
        String locator="button[id*='"+gameID+"']";
        List<WebElement> taskList = driver.findElements(By.cssSelector(locator));  //get task list from UI
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

    public void powerFailTestColorVerification(String color){
        String locator="//td[descendant::button[contains(@id,'"+gameID+"')]]";  //locator for task color
        WebElement powerFailTestColor=driver.findElement(By.xpath(locator));
        taskColorAssertion(powerFailTestColor.getCssValue("background-color"), color);
    }

    public void allTaskColorVerification(String color){
        String locator="//td[descendant::button[contains(@id,'"+gameID+"')]]";  //locator for task color
        List<WebElement> taskColor=driver.findElements(By.xpath(locator));
        for (int i=0;i<taskColor.size();i++) {
            taskColorAssertion(taskColor.get(i).getCssValue("background-color"), color);
        }
    }

    public void powerFailTestStatusVerification(String status){
        String locator="button[id*='"+gameID+"']";
        List<WebElement> taskList = driver.findElements(By.cssSelector(locator));  //get task list from UI
        taskStatusAssertion(taskList.get(0).getText(),status);
    }

    public void allTasksStatusVerification(String status){  //Проверка статуса всех тасок если все они одинаковы
        String locator="button[id*='"+gameID+"']";
        List<WebElement> taskList = driver.findElements(By.cssSelector(locator));  //get task list from UI
        for (int i=1;i<taskList.size();i++){
            taskStatusAssertion(taskList.get(i).getText(), status);
        }
    }

    private void taskStatusAssertion(String realStatus, String mustStatus){
                driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                try {
                    Assert.assertTrue(realStatus.contains(mustStatus));     //Verify task status
                }catch (AssertionError e){
                    e.getStackTrace();
                    ProprtyLoader.writeToFile("ERROR! Incorrect task status: "+realStatus+"  must be: "+mustStatus);
                    throw new RuntimeException("Assert error Incorrect task status");
                }finally {
                    driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")),TimeUnit.SECONDS);
                }
    }

    private void taskColorAssertion(String realColor, String mustColor){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            Assert.assertTrue(realColor.contains(mustColor));     //Verify task status
        }catch (AssertionError e){
            e.getStackTrace();
            ProprtyLoader.writeToFile("ERROR! Incorrect task color: "+realColor+"  must be: "+mustColor);
            throw new RuntimeException("Assert error status powerFailTestColorIdentification");
        }finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")),TimeUnit.SECONDS);
        }
    }

}
