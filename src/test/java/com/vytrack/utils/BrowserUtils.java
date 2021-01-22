package com.vytrack.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {

    private static WebDriverWait wait = new WebDriverWait(Driver.getDriver(),30);
    public static void wait(int seconds){

        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickOnElement(WebElement element){
    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void enterText(WebElement element,String text){

        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        System.out.println("Enter Text: " + text);
    }
    public static void clickWithJS(WebElement element){

        wait.until(ExpectedConditions.elementToBeClickable(element));//explicit wait: wait for specific webelement to be clickable or  present in the DOM
        ((JavascriptExecutor)(Driver.getDriver())).executeScript("arguments[0].click()",element);
    }

}
