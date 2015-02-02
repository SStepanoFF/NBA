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
        taskTab.click();
    }

    @FindBy (id="task")
    private WebElement taskTab;
    @FindBy (id="form")
    private WebElement formTab;

    //region Task Name WebElements
    @FindBy (linkText = "Power Failure Test QA 2015")
    private WebElement powerFailTestTask;
    @FindBy (xpath="//a[@class='ksList__listItem showTasks']//h3[contains(text(),'2015')]")
    private List<WebElement> tasksList;
    @FindBy (xpath="//span[contains(text(),'Open')]")  //"span:contains('Open')"   span.ks-desc-title:contains('Open')
    private List<WebElement> survList;
    @FindBy (xpath = "//a[contains(@id,'form')]//h3[contains(text(),'Power Failure Test')]")
    private WebElement powerFailTestForm;
    @FindBy (xpath = "//ul[@id='rl']//p")//span[@class='main-view']
    private List<WebElement> survListForm;
    //endregion

    @FindBy (xpath="//*[@id='taskEditForm']//a[1]")
    private WebElement editBtnTasks;
    @FindBy (xpath = "//div[@class='detailsPanel']/a[1]")//a[@class='detailsPanel_item editResponseButton']
    private WebElement editBtnForms;

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
    @FindBy (xpath = "//input[@class='inputSingleLine']")
    private WebElement clockTextField;
    @FindBy (id="goSubmitPage")
    private WebElement submitBtn;
    //endregion

    public void createPowerFailIncorrectTask(){
        powerFailTestTask.click();
        if(findSurvey("Power Failure Test")) {
            for (WebElement yesBtn : yesBtnList) {  //select all YES answers
                yesBtn.click();
            }
            noBtnList.get(noBtnList.size() - 1).click();  //select last NO-Button
            textField.get(textField.size() - 1).sendKeys("text");  //write comment on NO answer
            submitBtn.click();
            //super.submitTaskDate=getCurrentDate();        //Get date of task submitting
            ProprtyLoader.writeToFile("Power Fail incorrect task was created");
        }
        else{
            ProprtyLoader.writeToFile("ERROR! Power Fail incorrect task was not created");
            throw new RuntimeException("Power Fail incorrect task was not created");
        }
    }

    public void createAllCorrectTasks(){
        int indexOfCorrect=0;
        for (int i = 1; i < tasksList.size(); i++) {            //take all other task except first
             tasksList.get(i).click();
             String taskName=tasksList.get(i).getText();
             if (findSurvey(taskName.substring(0,taskName.length()-8))) {  //delete year (2015) from task text name
                 for (WebElement yesBtn : yesBtnList) {          //select all YES answers
                     yesBtn.click();
                 }
                 if (taskName.substring(0,taskName.length()-8).contains("Stanchion & Floor Clock")){
                     clockTextField.sendKeys("12:12");
                 }
                 submitBtn.click();
                 ProprtyLoader.writeToFile(taskName+" correct task was created");
             }else{
                 ProprtyLoader.writeToFile("ERROR! Correct "+taskName+" was not created");
                 indexOfCorrect++;
             }
         }
        if (indexOfCorrect==0){
            ProprtyLoader.writeToFile("All other correct tasks were created");
        }else {
            ProprtyLoader.writeToFile("ERROR! Not all other correct tasks were created");
        }
    }

    private boolean findSurvey(String taskName){
      boolean index=false;
        for (int i = survList.size() - 1; i >= 0; i--) {
                survList.get(i).click();
                editBtnTasks.click();
                if (taskId.get(7).getAttribute("value").contains(dataBase.getTaskExtId(gameID, taskName.substring(0,8)))) {   //  сравнение taskId  в сюрвее и в BD
                    index=true;
                    break;
                } else {
                    closeSurveyBtn.click();
                }
            }
      if(!index) {
          ProprtyLoader.writeToFile("ERROR! Can't find survey for task:" + taskName);
        }
        return index;
    }

    public void fixPowerFailTestTask(){
        formTab.click();
        powerFailTestForm.click();
        if(findSurveyFormTab("Power Failure Test")) {
            yesBtnList.get(yesBtnList.size() - 1).click();
            submitBtn.click();
        }else{
            ProprtyLoader.writeToFile("ERROR! Power Fail task fixing was not completed");
            throw new RuntimeException("Power Fail task fixing was not completed");
        }
    }

    private boolean findSurveyFormTab(String taskName){
        boolean index=false;
        for (int i =0;i< survListForm.size() ; i++) {
            survListForm.get(i).click();
            editBtnForms.click();
            if (taskId.get(7).getAttribute("value").contains(dataBase.getTaskExtId(gameID, taskName.substring(0,8)))) {   //  сравнение taskId  в сюрвее и в BD
                index=true;
                break;
            } else {
                closeSurveyBtn.click();
            }
        }
        if(!index) {
            ProprtyLoader.writeToFile("ERROR! Can't find survey for task:" + taskName);
        }
        return index;
    }
}
