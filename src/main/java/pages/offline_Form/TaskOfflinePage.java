package pages.offline_Form;

import framework.DataBase;
import framework.Operations;
import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class TaskOfflinePage extends MainOfflinePage {

    private final String gameID=ProprtyLoader.loadProperty("gameID");

    public TaskOfflinePage(WebDriver driver){
        super(driver);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("survTimeout")), TimeUnit.SECONDS);
        waitDownLoad();
        //WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(ProprtyLoader.loadProperty("survTimeout")));
        //wait.until(ExpectedConditions.elementToBeClickable(taskTab));
        taskTab.click();
    }

    @FindBy (id="task")
    private WebElement taskTab;

    @FindBy (linkText = "Power Failure Test QA 2015")
    private WebElement powerFailTestTask;
    @FindBy (css="h3::contains('2015')")
    private List<WebElement> tasksList;
    @FindBy (css="div[id='tasksList'] * li")  //"a[title='Edit']"
    private List<WebElement> survList;

    @FindBy (css="a[class='detailsPanel_item editFormButton']")
    private WebElement editFormBtn;

    //region Survey WebElements
    @FindBy (css="form>input")
    private List<WebElement> taskId;
    @FindBy (css="span:contains('Yes')")
    private List<WebElement> yesBtnList;
    //endregion

    public void openTest(){
        waitDownLoad();
        powerFailTestTask.click();
        findSurvey("Power Failure Test");
    }

    private void findSurvey(String taskName){
        for (int i=survList.size()-1; i>=0;i--){
            //mouseClick(survList.get(i));
            survList.get(i).click();
            //mouseClick(editFormBtn);
            mouseOver(editFormBtn);
            editFormBtn.click();
            if(taskId.get(7).getAttribute("value").contains(DataBase.getTaskExtId(gameID,taskName))){ //сравнение taskId  в сюрвее и в BD
                break;
            }
            else {
                ProprtyLoader.writeToFile("ERROR! Can't find survey for task:"+taskName);
            }
        }
    }
}
