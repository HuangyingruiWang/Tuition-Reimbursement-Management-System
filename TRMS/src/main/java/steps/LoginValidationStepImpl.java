//package steps;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.junit.Assert;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import pages.LoginPage;
//import runners.LoginRunner;
//
//public class LoginValidationStepImpl {
//    public static LoginPage loginPage = LoginRunner.loginPage;
//    public static WebDriver driver = LoginRunner.driver;
//
//    @Given("The User is on the Login Page")
//    public void the_user_is_on_the_login_page() {
//        driver.get("C:/Work/HTML&CSS/project1/LogIn.html");
//    }
//    @When("The User enter {string} in the username input and {string} in the password input")
//    public void the_user_enter_in_the_username_input_and_in_the_password_input(String string, String string2) {
//        loginPage.loginBar.sendKeys(string, string2, Keys.ENTER);
//    }
//
//    @Then("The User should be on the Main Page")
//    public void the_user_should_be_on_the_main_page() {
//        Assert.assertEquals("Main Page", driver.getTitle());
//    }
//
//    @Then("The User should see an alert pop up and the username input and password input should be empty")
//    public void the_user_should_see_an_alert_pop_up_and_the_username_input_and_password_input_should_be_empty() {
//        Assert.assertEquals("Login Page", driver.getTitle());
//    }
//
//}
