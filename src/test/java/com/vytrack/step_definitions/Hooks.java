package com.vytrack.step_definitions;

import com.vytrack.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.concurrent.TimeUnit;

/**
 * Hooks name is not reserved. You may name this class in any way.
 * For example: SuiteSetupAndTearDown
 * Hooks triggered based on tags not class name or their location.
 * These methods can be a part of any step definition class.
 * Common practice is to store them in the separate class.
 */
public class Hooks {

    //hook Before = @BeforeMethod in TestNG

    //hook After =@AfterMethod in TestNG
    //close browser, close DB connection, close tunnel, capture screenshot of error

    @Before
    public void setup(Scenario scenario){
        System.out.println(scenario.getSourceTagNames());
        System.out.println(":::starting automation:::");
        Driver.getDriver().manage().window().maximize();
        //it is better to implicit waits put here
        Driver.getDriver().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
    }

    /**
     * @db Scenario: I don't know but here I'm connecting to DB
     * Given user runs following query "SELECT * ...."
     * <p>
     * order = 0 - to define hooks execution order
     */
    //this hook will run only before scenarios with the tag @db
    @Before(value = "@db", order=0)
    //we can make this test annotation run for specific test scenarios that has @db annotation
    public void dbSetup(){
        System.out.println(":::Connecting to the DataBase");
    }

     @After//import @After from Cucumber
    public void tearDown(){
       //this is coming from cucumber, hook after
        Driver.closeDriver();//Runs automatically after every test scenario
        System.out.println(":::(^_^) End of Test Execution:::");
    }

    @After("@db")//this hook will only run after scenario with the tag @db
   public void dbTearDown(){
       System.out.println(":::Disconnecting to the database:::");
   }

}
