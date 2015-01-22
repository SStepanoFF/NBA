package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergey on 27.12.2014.
 */
public class Operations {

    protected Actions action;
    protected WebDriver driver;

    public Operations(WebDriver driver){
        this.driver=driver;
        this.action=new Actions(driver);
    }

    public void clickOn(WebElement webElement){
        webElement.click();
    }

    public void waitAndClick(WebElement webElement){
        WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(ProprtyLoader.loadProperty("timeout")));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public void mouseoverTab(WebElement tab){
        action.moveToElement(tab).build().perform();
    }

    public boolean isElementPresent(WebElement element){
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            if (element.isDisplayed()) return true;
            else return false;
        }catch (org.openqa.selenium.NoSuchElementException e){
            return false;
        }finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")),TimeUnit.SECONDS);
        }
    }

    public Date dataConvertfromString(String text) {
        Date date=null;
        try{
            SimpleDateFormat format=new SimpleDateFormat("dd.MM.yyyy", Locale.UK);
            date=format.parse(text);
        }catch (ParseException e){}
        return date;
    }
}