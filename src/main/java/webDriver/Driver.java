package webDriver;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by sstep on 12/22/2014.
 */
public class Driver {
    public static WebDriver getInstance(){
        WebDriver webDriver = null;
        if (ProprtyLoader.loadProperty("browser.name").isEmpty()){
            webDriver=new FirefoxDriver();
        }
        if (ProprtyLoader.loadProperty("browser.name").equalsIgnoreCase("chrome")){
            webDriver=ChromeWebDriver.getInstance();
        }
        if (ProprtyLoader.loadProperty("browser.name").equalsIgnoreCase("IE")){
           webDriver=IEWebDriver.getInstance();
        }
        return webDriver;
    }
}
