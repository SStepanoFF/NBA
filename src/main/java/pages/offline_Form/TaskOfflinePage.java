package pages.offline_Form;

import framework.DataBase;
import framework.Operations;
import framework.ProprtyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class TaskOfflinePage extends MainOfflinePage {

    private final String gameID=ProprtyLoader.loadProperty("gameID");
    private DataBase dataBase;

    public TaskOfflinePage(WebDriver driver){
        super(driver);
        dataBase=new DataBase();
        waitDownLoad();
        //WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(ProprtyLoader.loadProperty("survTimeout")));
        //wait.until(ExpectedConditions.elementToBeClickable(taskTab));
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("survTimeout")), TimeUnit.SECONDS);
        taskTab.click();
    }

    @FindBy (id="task")
    private WebElement taskTab;

    //region Task Name WebElements
    @FindBy (linkText = "Power Failure Test QA 2015")
    private WebElement powerFailTestTask;
    @FindBy (xpath="//h3[contains(text(),'2015')]")
    private List<WebElement> tasksList;
    @FindBy (xpath="//span[contains(text(),'Open')]")  //"span:contains('Open')"   span.ks-desc-title:contains('Open')
    private List<WebElement> survList;
    //endregion

    @FindBy (xpath="//*[@id='taskEditForm']//a[1]")
    private WebElement editFormBtn;

    //region Survey WebElements
    @FindBy (css="form>input")
    private List<WebElement> taskId;
    @FindBy (xpath="//label//span[contains(text(),'Yes')]")
    private List<WebElement> yesBtnList;
    @FindBy (xpath="//label//span[contains(text(),'No')]")
    private List<WebElement> noBtnList;
    @FindBy (id="backToResponsesButton")
    private WebElement closeSurveyBtn;
    @FindBy (tagName = "textarea")
    private List<WebElement> textField;
    @FindBy (id="goSubmitPage")
    private WebElement submitBtn;
    //endregion

    public void powerFailIncorrect(){
 //       try {
            powerFailTestTask.click();
            findSurvey("Power Failure Test");
            for (WebElement yesBtn : yesBtnList) {  //select all YES answers
                yesBtn.click();
            }
            noBtnList.get(noBtnList.size() - 1).click();  //select last NO-Button
            textField.get(textField.size() - 1).sendKeys("text");  //write comment on NO answer
            ProprtyLoader.writeToFile("Power Fail incorrect task created\n");
//        }catch (Exception e){
//            ProprtyLoader.writeToFile("ERROR! Power Fail incorrect task was not create");
//            throw new RuntimeException("Power Fail incorrect task was not create");
//        }
    }

    public void createAllCorrectTasks(){
      //  try {
            for (int i = 1; i < tasksList.size(); i++) {            //take all other task except first
                tasksList.get(i).click();
                findSurvey(tasksList.get(i).getText().substring(5));  //delete year (2015) from task text name
                for (WebElement yesBtn : yesBtnList) {          //select all YES answers
                    yesBtn.click();
                }
                ProprtyLoader.writeToFile("All other correct tasks created\n");
            }
//        }catch (Exception e){
//            ProprtyLoader.writeToFile("ERROR! All other correct tasks were not create");
//            throw new RuntimeException("All other correct tasks were not create");
//        }
    }

    private void findSurvey(String taskName){
//        try {
            for (int i = survList.size() - 1; i >= 0; i--) {
                survList.get(i).click();
                editFormBtn.click();
                if (taskId.get(7).getAttribute("value").contains(dataBase.getTaskExtId(gameID, taskName))) { //сравнение taskId  в сюрвее и в BD
                    break;
                } else {
                    closeSurveyBtn.click();
                }
            }
//        }catch (Exception e){
//            ProprtyLoader.writeToFile("ERROR! Can't find survey for task:"+taskName);
//        }
    }

    public void submit(){
        submitBtn.click();
    }
}
