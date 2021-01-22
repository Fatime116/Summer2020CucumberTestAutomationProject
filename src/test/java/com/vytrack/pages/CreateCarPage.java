package com.vytrack.pages;

import com.vytrack.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCarPage extends BasePage {

    @FindBy(xpath = "//a[@title='Create Car']")
    private WebElement createCarBtn;

    public void clickOnCreateCar(){//since webelement is private, we need to have some method to make this WebElement work

        //synchronization issue
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),30);
        wait.until(ExpectedConditions.elementToBeClickable(createCarBtn)).click();
        System.out.println("Clicking on 'Create car' button");
    }



}
