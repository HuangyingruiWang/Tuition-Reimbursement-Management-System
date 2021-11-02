package app;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public static void main(String[] args) {
        WebDriver driver = null;
        try {
        String driverPath = "C:/Work/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);

        String url = "C:/Work/HTML&CSS/project1/LogIn.html";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        loginPage.name.sendKeys("CS");
        loginPage.pass.sendKeys("CS123");
        loginPage.loginBtn.click();

        Thread.sleep(2000);
        } catch (NoSuchElementException | InterruptedException e) {
            e.printStackTrace();
        }
        if (driver != null) driver.quit();
    }
}
