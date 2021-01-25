package com.vytrack.pages;

import com.vytrack.utils.BrowserUtils;
import com.vytrack.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCalendarEventPage extends BasePage{

    //we have PageFactory class inside of BasePage constructor

    @FindBy(css = "[title='Create Calendar event']")
    private WebElement createCalendarEventBtn;

    @FindBy(name = "oro_calendar_event_form[title]")
    private WebElement titleInputBoxElement;

    @FindBy(id = "tinymce")
    private WebElement descriptionInputBoxElement;


    public void clickOnCreateCalendarEvenBtn(){

        BrowserUtils.clickOnElement(createCalendarEventBtn);

        System.out.println("Clicking on 'Create Calendar Event Btn' button");
    }

    public void enterTitle(String title){

        BrowserUtils.enterText(titleInputBoxElement,title);

        //      public static void enterText(WebElement element,String text){
        //
        //        wait.until(ExpectedConditions.visibilityOf(element));
        //        element.clear();
        //        element.sendKeys(text);
        //        wait.until(ExpectedConditions.attributeToBe(element,"value",text));//webDriver will wait until text you enter are completely entered
        //        System.out.println("Enter Text: " + text);//just to make sure we are entering right text
        //    }
    }


    public void enterDescription(String description){
//in description, we have iframe, so we switch driver focus to specific iframe

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
        Driver.getDriver().switchTo().defaultContent();//first make sure, we are not in any iframe, so before everything, we need to exit from all frames

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//explicit wait has the wait condition for the iframe to be switched into specific iframe automatically, then we can add text

        //  Driver.getDriver().switchTo().frame(0);//we used explicit wait switch

        //enter text in description box
       // BrowserUtils.enterText(descriptionInputBoxElement,description);//description box is text editor, it is not same as regular input box,
        //below line will not work for if it is not input box
       // wait.until(ExpectedConditions.attributeToBe(element,"value",text));//webDriver will wait until text you enter are completely entered

        descriptionInputBoxElement.sendKeys(description);// so just used regular webelement to sendkeys
        //exit from the iframe
        Driver.getDriver().switchTo().defaultContent();
    }

    public String getDataFromGeneralInfo(String parameterName){

       WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
       String xpath = "//label[text()='" + parameterName +  "']/../div/div";//this custom xpath is used to return the value of each parameter in General Information page under create calendar

       WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
       return element.getText().trim();

    }


}
