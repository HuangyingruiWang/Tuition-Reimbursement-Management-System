//package pages;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class LoginPage {
//    public WebDriver driver;
//
//    //Fields that represent Elements in the HTML
//    @FindBy(id = "inputUserName")
//    public WebElement name;
//    @FindBy(id = "inputPassword")
//    public WebElement pass;
//
//    @FindBy(id = "submitButton")
//    public WebElement loginBtn;
//
//    public LoginPage(WebDriver driver){
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//}
