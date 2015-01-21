package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by Sergey on 22.12.2014.
 */
public class IEWebDriver {
    public static WebDriver getInstance() {
        String IEFile = "..\\src\\main\\resources\\webDriver\\IE\\IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", IEFile);
        return new InternetExplorerDriver();
    }
}
