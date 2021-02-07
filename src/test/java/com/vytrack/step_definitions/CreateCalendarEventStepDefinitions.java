package com.vytrack.step_definitions;

import com.vytrack.pages.CreateCalendarEventPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class CreateCalendarEventStepDefinitions {


    CreateCalendarEventPage createCalendarEventPage = new CreateCalendarEventPage();

    @Given("user clicks on create calendar event button")
    public void user_clicks_on_create_calendar_event_button() {

      createCalendarEventPage.clickOnCreateCalendarEvenBtn();
    }



    //      | Title | B20 Graduation Party |
    //      | Description | All B20 friends are invited for this party! |
    @When("user adds new calendar event information")
    public void user_adds_new_calendar_event_information(Map<String,String> dataTable) {

           String title = dataTable.get("Title");//value
           String description = dataTable.get("Description");


           createCalendarEventPage.enterDescription(description);//if you try to enter the title, it will be some kinda problems
           createCalendarEventPage.enterTitle(title);

    }

    @Then("user verifies that new calendar event is displayed:")
    public void user_verifies_that_new_calendar_event_is_displayed( Map<String,String> dataTable) {
        String title = dataTable.get("Title");//expected coming from feature file
        String description = dataTable.get("Description");//expected

        Assert.assertEquals(title,createCalendarEventPage.getDataFromGeneralInfo("Title"));
        Assert.assertEquals(description,createCalendarEventPage.getDataFromGeneralInfo("Description"));

    }

}
