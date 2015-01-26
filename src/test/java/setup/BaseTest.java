package setup;

import framework.ProprtyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import webDriver.Driver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver dashDriver;

    @BeforeTest(alwaysRun = true)
    public void setUpTest(ITestContext context) {
        dashDriver = Driver.getInstance();
        context.setAttribute(getCurrentTestCaseName(context), dashDriver);
        dashDriver.manage().timeouts().implicitlyWait(Integer.parseInt(ProprtyLoader.loadProperty("timeout")), TimeUnit.SECONDS);
        ProprtyLoader.clearResultFile();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(ITestContext context1) {
        dashDriver = getDriver(context1);
        if (dashDriver != null) {
            dashDriver.quit();
        }

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

    protected WebDriver getDriver (ITestContext context) {
        return (WebDriver) context.getAttribute(getCurrentTestCaseName(context));
    }

    protected String getCurrentTestCaseName(ITestContext context) {
        return context.getCurrentXmlTest().getName();
    }

//    public String getWindowHandle(WebDriver driver) {
//        return driver.getWindowHandle();
//    }


}
