package com.vytrack.step_definitions;

import com.vytrack.pages.BasePage;
import com.vytrack.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CommonStepDefinitions {

    //LoginPage loginPage = new LoginPage();//since it is in the BasePage, and loginPage is extending BasePage, so we created object from this class
    BasePage basePage = new LoginPage();

    @Given("user navigates to {string} and {string}")//first String is tabName, second string is module name
    public void user_navigates_to_and(String string, String string2) {
           // loginPage.navigateTo(string,string2);
        basePage.navigateTo(string,string2);
    }

    //this save and close webElement is present on every page , so it is better to put it on one common page like on BasePage/ and in step_definitions like common step_definitions class
    //in our project, like reset, cancel, navigate, save button are present for every page
    //so we dont need to save this common button WebElement to every page seperately
    @When("user clicks on save and close button")
    public void user_clicks_on_save_and_close_button() {
        basePage.clickSaveAndClose();
    }

}
