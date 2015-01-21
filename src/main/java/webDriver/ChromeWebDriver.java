package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by sstep on 12/22/2014.
 */
public class ChromeWebDriver {
    public static WebDriver getInstance(){
        String chromeFile="..\\src\\main\\resources\\webDriver\\Chrome\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromeFile);
        return new ChromeDriver();
    }
}
