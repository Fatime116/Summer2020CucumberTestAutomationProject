package com.vytrack.pages;

import com.vytrack.utils.BrowserUtils;
import com.vytrack.utils.Driver;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCarPage extends BasePage {

    @FindBy(xpath = "//a[@title='Create Car']")//not let anyone to use WebElements outside of page class
    private WebElement createCarBtn;

    @FindBy(name="custom_entity_type[LicensePlate]")
    private WebElement licensePlateInputBox;

    @FindBy(name="custom_entity_type[ModelYear]")
    private WebElement modelYearInputBox;


    public void clickOnCreateCar(){//since webelement is private, we need to have some method to make this WebElement work

        //synchronization issue
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
//        wait.until(ExpectedConditions.elementToBeClickable(createCarBtn)).click();
        BrowserUtils.clickOnElement(createCarBtn);
        System.out.println("Clicking on 'Create car' button");
    }

    public void enterLicensePlate(String LicensePlate) {//this LicensePlate is coming from our feature file

//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
//        wait.until(ExpectedConditions.visibilityOf(licensePlateInputBox));
//
//        licensePlateInputBox.clear();//before entering the text, we need to clear inout box
//        licensePlateInputBox.sendKeys(LicensePlate);

        BrowserUtils.enterText(licensePlateInputBox, LicensePlate);//call this from Browser Utils class
        //                       WebElement,       String that we want to Enter--->SDET


        /*
         * sometimes, for very long string webdriver might enter text not fully. so for this issue we can add extra waits
         *         element.sendKeys(text);
         *         wait.until(ExpectedConditions.attributeToBe(element,"value",text));//webDriver will wait until text you enter are completely entered
         *
         *
         */
    }

    public void enterModelYear(String ModelYear){

        BrowserUtils.enterText(modelYearInputBox,ModelYear);
    }

    //last step of save and close is in common step_definitions class,
    //this save and close webElement is present on every page , so it is better to put it on one common page like on BasePage/ and in step_definitions like common step_definitions class
    //in our project, like reset, cancel, navigate, save button are present for every page
    //so we dont need to save this common button WebElement to every page seperately


}
