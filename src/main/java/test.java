//package performanceIU;
//
//import io.selendroid.SelendroidCapabilities;
//import io.selendroid.SelendroidConfiguration;
//import io.selendroid.SelendroidDriver;
//import io.selendroid.SelendroidLauncher;
//import io.selendroid.device.DeviceTargetPlatform;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import utils.AppUtil;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created with IntelliJ IDEA.
// * User: ivan.halyavka
// * Date: 2/4/15
// * Time: 10:07 AM
// * To change this template use File | Settings | File Templates.
// */
//public class test1 {
//
//    private String application = "Formcom356.apk";
//    private String pack = "com.form.offline:6.0.356";
//    private long portalId = 111111;
//    //private static String location = AppUtil.getRelativePathToImageWithClass(SUP_10536.class.getResource("/"), "resources/");
//    private WebDriver webDriver;
//
//    @BeforeClass
//    public void beforeClass(){
//        SelendroidConfiguration config = new SelendroidConfiguration();
//        config.addSupportedApp(location + "offline_applications/" + application);
//        SelendroidLauncher selendroidServer = new SelendroidLauncher(config);
//        selendroidServer.lauchSelendroid();
//        try {
//            webDriver = new SelendroidDriver("http://localhost:5555/wd/hub", SelendroidCapabilities.device(DeviceTargetPlatform.ANDROID19, pack));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//    }
//    pack= com.form.nba.offline
//    @Test
//    public void test(){
//        if(!webDriver.getWindowHandle().equals("WEBVIEW")){
//            webDriver.switchTo().window("WEBVIEW");
//        }
//        webDriver.findElement(By.id("serverUrlField")).sendKeys("http://ksbeta.pr1.ssstest.com");
//        webDriver.findElement(By.id("portalIdField")).sendKeys(String.valueOf(portalId));
//        webDriver.findElement(By.name("username")).sendKeys("test1");
//        webDriver.findElement(By.name("password")).sendKeys("fc");
//        webDriver.findElement(By.id("loginButton")).click();
//    }
//
//}
//
//
//
//
////import io.selendroid.client.SelendroidDriver;
////import io.selendroid.common.SelendroidCapabilities;
////import io.selendroid.common.device.DeviceTargetPlatform;
////import org.openqa.selenium.By;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.WebElement;
////import org.testng.Assert;
////
/////**
//// * Created by sergii.stepanov on 20.01.2015.
//// */
////public class test {
////    private enum GameStatus{
////        Overdue(0) , Done(1), CompleteWithIncorrect(2);
////        private int value;
////
////         GameStatus(int value) {
////             this.value=value;
////        }
////    };
////    public static void main(String []args) throws Exception {
////        //SelendroidCapabilities.emulator(String.valueOf(true));
////        SelendroidCapabilities capa = new SelendroidCapabilities("io.selendroid.testapp:0.14.0");
////        capa.setPlatformVersion(DeviceTargetPlatform.ANDROID10);
////        capa.setEmulator(true);
////        WebDriver driver = new SelendroidDriver(capa);
////        WebElement inputField = driver.findElement(By.id("my_text_field"));
////        Assert.assertEquals("true", inputField.getAttribute("enabled"));
////        inputField.sendKeys("Selendroid");
////        Assert.assertEquals("Selendroid", inputField.getText());
////        driver.quit();
////    }
////}
