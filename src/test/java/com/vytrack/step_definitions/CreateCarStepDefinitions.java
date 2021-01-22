package com.vytrack.step_definitions;

import com.vytrack.pages.CreateCarPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.Map;

public class CreateCarStepDefinitions {

    CreateCarPage createCarPage = new CreateCarPage();

    @Given("user clicks on create car button")
    public void user_clicks_on_create_car_button() {
        createCarPage.clickOnCreateCar();
    }


    /*
    *
     * When user adds new vehicle information
     * | License Plate | SDET |
     * | Model Year    | 2021 |
     * key           value
     *
     * dataTable.get("License Plate") --> SDET
     * dataTable.get("Model Year")    --> 2021
     *
    * we are starting the pipes |, which means border
    *DataTable is cucumber data Structure, and it can auto-converted to list or map by Cucumber
    *
    ** map it's data structure where every value is referenced by key
     * (in arraylist it's always index)
     *
     * dataTable = { License Plate = SDET,
                     Model Year = 2021 }
     *
     * If you want to turn data table into map, it MUST be exactly 2 columns
     *
     * If it's a 1 column, it can be just List:
     *
              @Then user verifies following list:      just 1 colums
     *        |10|
     *        |20|
     *        |50|
     * public void user_verifies_following_list( List<String> dataTable){}
     *
     * DataTable - cucumber data structure/data type. We convert it into Map, List or List<Map<>>
    * */
    @When("user adds new vehicle information")
    public void user_adds_new_vehicle_information(Map<String,String> map) {//auto-converted to map by cucumber

        //get all the keys and values one by one
        map.forEach((key,value) -> System.out.println("Key: " + key + ", Value : " + value));


        System.out.println("License plate: " + map.get("License Plate: "));//map.get(key) return us value : SDET
        System.out.println("Model year : " + map.get("Model Year"));//map.get(key) return us value :2021


        createCarPage.enterLicensePlate(map.get("License Plate"));
        createCarPage.enterModelYear(map.get("Model Year"));

    }

}
