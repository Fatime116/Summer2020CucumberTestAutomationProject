package com.vytrack.pages;

import com.vytrack.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {//it is abstract class, no one can create object from this class, it is a base class, it is not page class
    //it does not correspond to any page in the application
    //this class stores some common element and methods
    //child class inherit everything from parent class except it is private and static

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(className = "oro-subtitle")
    protected WebElement pageSubTitle;//we can make it visible to the child classes(page class), still cannot have this webElement direct get access to step_definitions class


   //we control access to WebElement through the private or protected keyword
    //private encapsulate data inside the class, no one can get access to it
    //protected: makes it available within the same package, and subclasses that inherits this class
    //step definitions class are located in different package, and not going to inherit this class,so step_definition does not have any access to it

    public String getPageSubTitleText(){ //this is instance method, in can inherit to child class
        return pageSubTitle.getText();
    }


}
