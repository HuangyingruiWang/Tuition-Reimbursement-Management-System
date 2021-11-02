//package runners;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import pages.LoginPage;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(features = "src/test/resources", glue = {"steps"})
//public class LoginRunner {
//
//    public static WebDriver driver;
//    public static LoginPage loginPage;
//
//    @BeforeClass
//    public static void setUp(){
//        String path = "C:/Work/chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", path);
//
//        driver = new ChromeDriver();
//        loginPage = new LoginPage(driver);
//    }
//
//    @AfterClass
//    public static void tearDown(){
//        driver.quit();
//    }
//}
