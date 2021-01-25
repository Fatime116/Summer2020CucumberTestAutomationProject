package com.vytrack.pages;

import com.vytrack.utils.ConfigurationReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
     //we put PageFactory.initElements(Driver.getDriver(), this); in the BasePage class constructor,
     //Constructor cannot be inherited, but child class can call parent class constructor
    //when we create object from this class, jvm will add runtime constructor, and this constructor can
    //call BasePage constructor, so initElements method will gets executed once it extends to it and create object


    //private WebElement: To not use web elements directly in step definition classes
    //helps to prevent code duplication and keep step definitions CLEAN

    @FindBy(id = "prependedInput")//to make this @FindBy work, we need to use PageFactory class init elements method
    private WebElement username;//will prevent u using this WebElement directly in step_definitions

    @FindBy(id="prependedInput2")
    private WebElement password;

    @FindBy(name="_submit")
    private WebElement submitButton;

    @FindBy(xpath="//div[@class='alert alert-error']")
    public WebElement warningMessage;//when we make it private, we can enforce everyone to create methods here

    public String getWarningMessageText()  {//we make everyone enforce to use method instead of webElements, so =that our step_definitions class is clean
        try {//used try catch block, because we use these methods again in step_definitions
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return warningMessage.getText().trim();
    }

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
