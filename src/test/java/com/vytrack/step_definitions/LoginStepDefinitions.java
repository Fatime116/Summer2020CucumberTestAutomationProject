package com.vytrack.step_definitions;

import com.vytrack.pages.LoginPage;
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
    WebDriver driver;

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        //according to POM, driver cannot be instantiated here
//        WebDriverManager.chromedriver().setup();  //makes the setup of webdriver, it is setting up chromedriver and connects the WebDriver object with the browser
//        driver = new ChromeDriver();
//        driver.get(ConfigurationReader.getProperty("url"));

        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

    }

    @When("user logs in")
    public void user_logs_in() throws InterruptedException {
     //according to POM, elements are directly specified here,

//     driver.findElement(By.id(("prependedInput"))).sendKeys(ConfigurationReader.getProperty("salesmanager110"));
//     driver.findElement(By.id("prependedInput2")).sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);

        //since we are POM, we are creating page objects, literally instance of page class
        //page object contains WebElements and methods,

        loginPage.login();

    }
    @Then("user should see dashboard page")
    public void user_should_see_dashboard_page() {
        String expectedDashBoardName = "Dashboard";

        //title class attribute value always the same and present for all tab header
        String actualDashBoardName = driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText().trim();
        Assert.assertEquals("Title is not correct!!",expectedDashBoardName,actualDashBoardName);//in Junit comments comes before

//        driver.quit();
        Driver.closeDriver();;
    }

}
