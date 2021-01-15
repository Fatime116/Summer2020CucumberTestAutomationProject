package com.vytrack.step_definitions;

import com.vytrack.utils.ConfigurationReader;
import com.vytrack.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStepDefinitions {
   WebDriver driver;
    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        //according to POM, driver cannot be instantiated here
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get(ConfigurationReader.getProperty("url"));
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));


    }

    @When("user logs in")
    public void user_logs_in() throws InterruptedException {
   //according to POM, elements are directly specified here
     driver.findElement(By.id(("prependedInput"))).sendKeys(ConfigurationReader.getProperty("salesmanager110"));
     driver.findElement(By.id("prependedInput2")).sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
     Thread.sleep(3000);
    }
    @Then("user should see dashboard page")
    public void user_should_see_dashboard_page() {
        String expectedDashBoardName = "Dashboard";
        String actualDashBoardName = driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText().trim();
        Assert.assertEquals("Title is not correct!!",expectedDashBoardName,actualDashBoardName);//in Junit comments comes before

        driver.quit();
    }

}
