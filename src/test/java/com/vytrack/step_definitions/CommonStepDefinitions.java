package com.vytrack.step_definitions;

import com.vytrack.pages.LoginPage;
import io.cucumber.java.en.Given;

public class CommonStepDefinitions {

    @Given("user navigates to {string} and {string}")//first String is tabName, second string is module name
    public void user_navigates_to_and(String string, String string2) {


        LoginPage loginPage = new LoginPage();//since it is in the BasePage, and loginPage is extending BasePage, so we created object from this class
        loginPage.navigateTo(string,string2);

    }

}
