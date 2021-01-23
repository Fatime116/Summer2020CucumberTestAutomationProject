package com.vytrack.pages;

import com.vytrack.utils.BrowserUtils;
import com.vytrack.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {//it is abstract class, no one can create object from this class, it is a base class, it is not page class
    //it does not correspond to any page in the application
    //this class stores some common element and methods
    //child class inherit everything from parent class except it is private and static

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }////once object is created from this class, this line will be gets executed
    //first it will look for driver instance, then it will initialize usernameElement WebElement, give them life, otherwise this webElement value will be null
    //so @FindBy annotation will find that Element(@FindBy(name="ctl00$MainContent$username")) and assign it to the usernameElement

    @FindBy(className = "oro-subtitle")
    protected WebElement pageSubTitle;//we can make it visible to the child classes(page class), still cannot have this webElement direct get access to step_definitions class


   //we control access to WebElement through the private or protected keyword
    //private encapsulate data inside the class, no one can get access to it
    //protected: makes it available within the same package, and subclasses that inherits this class
    //step definitions class are located in different package, and not going to inherit this class,so step_definition does not have any access to it


    //this save and close webElement is present on every page , so it is better to put it on one common page like on BasePage/ and in step_definitions like common step_definitions class
    //in our project, like reset, cancel, navigate, save button are present for every page
    //so we dont need to save this common button WebElement to every page seperately
    @FindBy(xpath="(//button[contains(text(),'Save and Close')])[1]")
    protected WebElement saveAndCloseBtn;

    //Sometimes Page is loaded, but it keeps calling the server to retrieve the data. Meanwhile since page is not ready, page is showing the spinner, when we are calling the selenium, we are waiting for element to be clickable, element is clickable, but because of that screen, our click has failed.
    //That spin also part of DOM, we just need to locate it, and use explicit wait and use invisibilityOfElement until this element is invisible.
    @FindBy(css = "[class='loader-mask']")
    protected List<WebElement> loaderMask;//in our project we have 2 webelements present on the DOM, so we used List<WebElement>


    public String getPageSubTitleText() {//this is instance method, it can inherit to child class
        return pageSubTitle.getText();
    }

    /**
     * Method for navigation in vytrack app
     *
     * @param tabName     , for example: Fleet, Dashboard, Sales, Activities..
     * @param module, one of the values that will be visible after clicking on the tab.
     *                For Fleet, these are the modules: Vehicles, Vehicle Odometer, Vehicle Costs, etc..
     */
    public void navigateTo(String tabName, String module) {



//        Driver.getDriver().findElement(By.linkText(tabName)).click();
//        Driver.getDriver().findElement(By.linkText(Module)).click();

//    (//*[contains(text(),'Fleet')])[3]
//    //*[contains(text(),'Fleet') and @class='title title-level-1']

        // we have 8 tabs, Like Dashboard, Fleet, Customers,Sales, everytime we pass the tabName, we will locate respective tab
        //the reason we put this in this BasePage is that attribute are all same using custom xpath
        //we stored all the common webelements here
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
        String tabXpath = "//*[contains(text(),'" + tabName + "') and @class='title title-level-1']";
        String moduleXpath = "//*[contains(text(),'" + module + "') and @class='title title-level-2']";

        //wait until loader mask disappears
        wait.until(ExpectedConditions.invisibilityOfAllElements(loaderMask));

        BrowserUtils.wait(3);
        //wait for presence and ability co click on element
        WebElement tabElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(tabXpath)));
        wait.until(ExpectedConditions.elementToBeClickable(tabElement)).click();

        WebElement moduleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleXpath)));
        wait.until(ExpectedConditions.elementToBeClickable(moduleElement)).click();

        //wait until loader mask disappears
        wait.until(ExpectedConditions.invisibilityOfAllElements(loaderMask));

        BrowserUtils.wait(3);
    }

    public void clickSaveAndClose(){
        BrowserUtils.elementToBeVisible(saveAndCloseBtn);
        BrowserUtils.clickOnElement(saveAndCloseBtn);
        //public static void clickOnElement(WebElement element){
        //    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        //    }
    }

}
