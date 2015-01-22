import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.LoginPage;
import webDriver.Driver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

//    public BaseTest(WebDriver driver){
//        this.driver=driver;
//    }

    @BeforeSuite
    public void setUpTest() {
        driver = Driver.getInstance();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")), TimeUnit.SECONDS);
        ProprtyLoader.clearResultFile();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
       // WebDriver driver = getDriver(context);
        if (driver != null) {
            driver.quit();
        }
//        context.removeAttribute(getCurrentTestCaseName(context));
//        try{
//            Thread.sleep(10000);   //give driver sometime to breathe.
//        }
//        catch (Exception exc){}

   }

//
//@DataProvider(name = "DataProvider")
//public Object [][] getData(Method m, ITestContext context ){
//    Class<?>[] params = m.getParameterTypes();
//    for (Class<?> param:params){
//        Constructor<?>[]constructors = param.getConstructors();
//        for(Constructor<?> c : constructors){
//            try{
//                Object obj=  c.newInstance(context.getName(), m.getName());
//                return new Object[][]{
//                        new Object[]{obj}  };
//            }
//            catch (Exception exc){
//                exc.printStackTrace();
//            }
//        }
//    }
//    return  null;
//}

//    protected WebDriver getDriver (ITestContext context) {
//        return (WebDriver) context.getAttribute(getCurrentTestCaseName(context));
//    }
//
//    protected String getCurrentTestCaseName(ITestContext context) {
//        return context.getCurrentXmlTest().getName();
//    }
//
//    public String getWindowHandle(WebDriver driver) {
//        return driver.getWindowHandle();
//    }


}
