package pages.offline_Form;

import framework.DataBase;
import framework.Loader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class TaskOfflinePage extends MainOfflinePage {

    private final String gameID= Loader.loadProperty("gameID");
    private boolean portal=Loader.loadProperty("portal").equals("1");  //true-production false-test
    private DataBase dataBase;

    public TaskOfflinePage(WebDriver driver){
        super(driver);
        dataBase=new DataBase();
        waitDownLoad();
        taskTab.click();
    }

//    @FindBy (xpath = "//div[class='dialog']")
//    private WebElement alertDownloadMes;
//    @FindBy (xpath = "//div[class='formLine']")
//    private WebElement alertOkBtn;

    @FindBy (id="task")
    private WebElement taskTab;
    @FindBy (id="form")
    private WebElement formTab;

    //region Task Name WebElements
    @FindBy (linkText = "Power Failure Test QA 2015")
    private WebElement powerFailTestTask;
    @FindBy (linkText = "Power Failure Test")
    private WebElement powerFailTestTaskProd;
    @FindBy (xpath="//a[@class='ksList__listItem showTasks']//h3[contains(text(),'2015')]")
    private List<WebElement> tasksListTest;
    @FindBy (xpath="//a[@class='ksList__listItem showTasks']//h3")
    private List<WebElement> tasksListProd;
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
    @FindBy (xpath = "//div[@id='tasks']//span[@class='nextPageBtn']")
    private WebElement nextPageBtnTasks;
    @FindBy (xpath = "//div[@id='tasks']//span[@class='butContainer']/following-sibling::*[1]/self::span")
    private WebElement backPageBtnTasks;
    @FindBy (xpath = "//div[@id='tasks']//span[contains(text(),'/')]")
    private WebElement countPage;

    //region Survey WebElements
    @FindBy (css="table~input")
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
        if (portal) {
            tasksListProd.get(0).click();
        } else tasksListTest.get(0).click();
        if(findSurveyTaskTab("Power Failure Test")) {
            noBtnList.get(0).click();  //select last NO-Button
            textField.get(0).sendKeys("text");  //write comment on NO answer
            submitBtn.click();
            Loader.logWritter("Power Fail incorrect task was created");
        }
        else{
            Loader.logWritter("ERROR! Power Fail incorrect task was not created");
            throw new RuntimeException("Power Fail incorrect task was not created");
        }
    }

    public void createAllCorrectTasks(){
        boolean createIndex=false;
        List<WebElement> tasksList;
        if (portal) {
            tasksList=tasksListProd;
        } else tasksList=tasksListTest;
        for (int i = 1; i < tasksList.size(); i++) {            //take all other task except first
             tasksList.get(i).click();
             String taskName=tasksList.get(i).getText();
             if (findSurveyTaskTab(taskName.substring(0, taskName.length() - 8))) {  //delete year (2015) from task text name
                 for (WebElement yesBtn : yesBtnList) {          //select all YES answers
                     yesBtn.click();
                 }
                 if (taskName.substring(0,taskName.length()-8).contains("Stanchion & Floor Clock")){
                     clockTextField.sendKeys("12:12");
                 }
                 submitBtn.click();
                 createIndex=false;
                 Loader.logWritter(taskName + " correct task was created");
             }else{
                 Loader.logWritter("ERROR! Correct " + taskName + " was not created");
//                 createIndex=false;
             }
         }
        if(!createIndex){
            throw new RuntimeException("All other correct tasks were not created");
        }
    }

    private boolean findSurveyTaskTab(String taskName){
        boolean findSurvey=false;
        int pageQuantity=1;
        if (isElementPresent(countPage)){
            pageQuantity = Integer.parseInt(countPage.getText().substring(4));
        }
        for (int i=pageQuantity;i>0;i--) {
            for (int y = i-1;y >0;y--) {
                nextPageBtnTasks.click();
            }
            findSurvey = serchInSurveyList(taskName, i-1);
            if (findSurvey) {
                break;
            }
        }
        if (!findSurvey) {
            Loader.logWritter("ERROR! Can't find survey for task:" + taskName);
        }
        return findSurvey;
    }


    private boolean serchInSurveyList(String taskName, int pageNumber){
        boolean index=false;
        for (int i = survList.size() - 1; i >= 0; i--) {
            survList.get(i).click();
            editBtnTasks.click();
            if (taskId.get(7).getAttribute("value").contains(dataBase.getTaskExtId(gameID, taskName.substring(0, 8)))) {   //  сравнение taskId  в сюрвее и в BD
                index = true;
                break;
            } else {
                closeSurveyBtn.click();
                if (i!=0) {
                    for (int y = pageNumber; y > 0; y--) {
                        nextPageBtnTasks.click();
                    }
                }
            }
        }
        return index;
    }

    public void fixPowerFailTestTask(){
        formTab.click();
        powerFailTestForm.click();
        if (findSurveyFormTab("Power Failure Test")) {
            for (WebElement yesBtn : yesBtnList) {  //select all YES answers
                yesBtn.click();
            }
            submitBtn.click();
        }else{
            Loader.logWritter("ERROR! Power Fail task fixing was not completed");
            throw new RuntimeException("Power Fail task fixing was not completed");
        }
    }

    private boolean findSurveyFormTab(String taskName){
        boolean index=false;
        for (int i =0;i< survListForm.size() ; i++) {
            survListForm.get(i).click();
            editBtnForms.click();
            for(WebElement element:taskId) {
                if (element.getAttribute("value").contains(dataBase.getTaskExtId(gameID, taskName.substring(0, 8)))) {   //  сравнение taskId  в сюрвее и в BD
                    index = true;
                    break;
                }
            }
            if(!index){
                closeSurveyBtn.click();
            }
        }
        if(!index) {
            Loader.logWritter("ERROR! Can't find survey for task:" + taskName);
        }
        return index;
    }
}
