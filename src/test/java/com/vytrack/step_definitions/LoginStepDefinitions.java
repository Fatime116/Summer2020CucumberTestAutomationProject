package com.vytrack.step_definitions;

import com.vytrack.pages.BasePage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utils.BrowserUtils;
import com.vytrack.utils.ConfigurationReader;
import com.vytrack.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();
   // WebDriver driver; since we are using POM, driver object did not use this class

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        //according to POM, driver cannot be instantiated here
//        WebDriverManager.chromedriver().setup();  //makes the setup of webdriver, it is setting up chromedriver and connects the WebDriver object with the browser
//        driver = new ChromeDriver();
//        driver.get(ConfigurationReader.getProperty("url"));

        Driver.getDriver().get("http://qa2.vytrack.com");

    }

    @When("user logs in")
    public void user_logs_in() throws InterruptedException {
     //according to POM, elements are directly specified here,

//     driver.findElement(By.id(("prependedInput"))).sendKeys(ConfigurationReader.getProperty("salesmanager110"));
//     driver.findElement(By.id("prependedInput2")).sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);

        //since we are POM, we are creating page objects, literally instance of page class
        //page object contains WebElements and methods,

        loginPage.login();
        Thread.sleep(3000);

    }
    @Then("user should see dashboard page")
    public void user_should_see_dashboard_page() {
        String expectedDashBoardName = "Dashboard";

        //title class attribute value always the same and present for all tab header
       // String actualDashBoardName = driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText().trim();
        String actualDashBoardName = loginPage.getPageSubTitleText();
        System.out.println("actualDashBoardName = " + actualDashBoardName);

        Assert.assertEquals("Title is not correct!!",expectedDashBoardName,actualDashBoardName);//in Junit comments comes before
        System.out.println("I see the Dashboard page!");
//        driver.quit();
        Driver.closeDriver();
    }

    @Then("user should see {string} page")
    public void user_should_see_page(String string) {
       String  expected = string;

       String actual = loginPage.getPageSubTitleText().trim();
       Assert.assertEquals("Page title is not correct",expected,actual);
    }

    //When user logs in as a "driver" --> public void user_logs_in_as_a(String string) -> loginPage.login(string); -> public void login(String role) { if role == "" do this..}
    //When user logs in as a "sales manager"
    //When user logs in as a "store manager"
    @When("user logs in as a {string}")
    public void user_logs_in_as_a(String string) throws InterruptedException{

        loginPage.login(string );
        Thread.sleep(3000);

    }

    //When user logs in with "storemanagers85" and "wrong" password
    //string is whatever value inside of first "" , which is storemanagers85 , String string ="storemanagers85"
    //string2 is whatever value inside of second "" , which is wrong, String string2 = "wrong"
    @When("user logs in with {string} and {string} password")
    public void user_logs_in_with_and_password(String string, String string2) {
     loginPage.login(string,string2);

    }

    //expected text: string ----Invalid user name or password.
    @Then("user verifies that {string} message is displayed")
    public void user_verifies_that_message_is_displayed(String expectedWarningMessage) {

        String actualWarningMessage = loginPage.getWarningMessageText();
        Assert.assertEquals(expectedWarningMessage,actualWarningMessage);
       // Assert.assertTrue(loginPage.warningMessage.isDisplayed());  //if the webelement is setted as public



    }
}
