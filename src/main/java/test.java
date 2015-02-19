//import io.selendroid.SelendroidDriver;
//import io.selendroid.SelendroidCapabilities;
//import io.selendroid.device.DeviceTargetPlatform;
//import io.selendroid.SelendroidConfiguration;
//import io.selendroid.SelendroidLauncher;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
//public class test {
//
//
////import io.selendroid.standalone.SelendroidConfiguration;
////import io.selendroid.standalone.SelendroidLauncher;
//
//    private String application = "apk_signed.apk";
//    private String pack = "com.form.nba.offline:4.0.21";
//    private long portalId = 111111;
//   // private static String location = AppUtil.getRelativePathToImageWithClass(SUP_10536.class.getResource("/"), "resources/");
//    private WebDriver webDriver;
//
//    @BeforeClass
//    public void beforeClass(){
//        SelendroidConfiguration config = new SelendroidConfiguration();
//        config.addSupportedApp("D:/MY/Automation/NBA_Project/NBA/src/main/resources/apk_signed.apk");
//        SelendroidLauncher selendroidServer = new SelendroidLauncher(config);
//        selendroidServer.lauchSelendroid();
//        try {
//            webDriver = new SelendroidDriver("http://localhost:5555/wd/hub", SelendroidCapabilities.device(DeviceTargetPlatform.ANDROID19, pack));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void test(){
////        if(!webDriver.getWindowHandle().equals("WEBVIEW")){
////            webDriver.switchTo().window("WEBVIEW");
////        }
//        webDriver.findElement(By.id("usernameField")).sendKeys("http://ksbeta.pr1.ssstest.com");
//
//    }
//
//}




import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.common.device.DeviceTargetPlatform;
import io.selendroid.standalone.SelendroidConfiguration;
import io.selendroid.standalone.SelendroidLauncher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
* Created by sergii.stepanov on 20.01.2015.
*/
public class test {

    private static String application = "apk_signed.apk";
    private static String pack = "com.form.offline:6.0.356";
    //private WebDriver webDriver;

    public static void main(String []args) throws Exception {
//        WebDriver webDriverdriver=null;
        SelendroidConfiguration config = new SelendroidConfiguration();
        config.addSupportedApp("src/main/resources/" + application);
        SelendroidLauncher selendroidServer = new SelendroidLauncher(config);
        selendroidServer.launchSelendroid();
//        try {
//            webDriver = new SelendroidDriver("http://localhost:4444/wd/hub", SelendroidCapabilities.device(DeviceTargetPlatform.ANDROID19, pack));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        SelendroidCapabilities capa = new SelendroidCapabilities("io.selendroid.testapp:0.14.0");
        capa.setPlatformVersion(DeviceTargetPlatform.ANDROID19);
        capa.setEmulator(true);
        capa.setModel("AVD_for_Nexus_5");
//        WebDriver driver = new SelendroidDriver("http://localhost:4444/wd/hub", SelendroidCapabilities.device(DeviceTargetPlatform.ANDROID19, pack));
        WebDriver driver = new SelendroidDriver(capa);
        driver.findElement(By.id("usernameField")).sendKeys("http://ksbeta.pr1.ssstest.com");
    }
}
