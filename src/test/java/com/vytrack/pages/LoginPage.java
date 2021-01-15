package com.vytrack.pages;

import com.vytrack.utils.ConfigurationReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

     //Constructor cannot be inherited, but child class can call parent class constructor
    //when we create object from this class, jvm will add runtime constructor, and this constructor can
    //call BasePage constructor, so initElements method will gets executed once it extends to it and create object



    //To not use web elements directly in step definition classes
    //helps to prevent code duplication and keep step definitions CLEAN
    @FindBy(id = "prependedInput")
    public WebElement username;//will prevent u using this WebElement directly in step_definitions

    @FindBy(id="prependedInput2")
    private WebElement password;

    @FindBy(name="_submit")
    private WebElement submitButton;

    public void login(String usernameValue, String passwordValue) {
        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue, Keys.ENTER);//Keys.ENTER will be equal click on login button
    }

   //i am gonna overloading this login method, because i want to read Credential from configuration.properties file
   public void login() {
       String usernameValue = ConfigurationReader.getProperty("salesmanager.username");
       String passwordValue = ConfigurationReader.getProperty("password");

       username.sendKeys(usernameValue);
       password.sendKeys(passwordValue, Keys.ENTER);
   }


    public void login(String role) {
        String usernameValue = "";
        String passwordValue = ConfigurationReader.getProperty("password");

        if (role.equalsIgnoreCase("salesmanager")) {
            usernameValue = ConfigurationReader.getProperty("salesmanager.username");
        } else if (role.equalsIgnoreCase("driver")) {
            usernameValue = ConfigurationReader.getProperty("driver.username");
        } else {
            usernameValue = ConfigurationReader.getProperty("storemanager.username");
        }

        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue, Keys.ENTER);
    }

  //  public void login(String...args){}//dynamic number of arguments











}
