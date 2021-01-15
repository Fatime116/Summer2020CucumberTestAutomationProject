package com.vytrack.step_definitions;

import com.vytrack.utils.Driver;
import io.cucumber.java.After;

public class Hooks {

    @After//import @After from Cucumber
    public void tearDown(){
       //this is coming from cucumber, hook after
        Driver.closeDriver();//Runs automatically after every test scenario
        System.out.println(":::(^_^) End of Test Execution:::");
    }



}
