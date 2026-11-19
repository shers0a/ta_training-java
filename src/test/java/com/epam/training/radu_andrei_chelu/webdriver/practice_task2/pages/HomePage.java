package com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class HomePage extends BasePage {
    private final String homeURL;
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler"); //id locator
    private final By careersPageButton = By.linkText("Careers"); //link text locator

    public HomePage(WebDriver driver, String homeURL){
        super(driver);

        this.homeURL = homeURL;
    }

    public HomePage open(){
        super.open(homeURL);
        return this; //for method chaining :*
    }

    public HomePage acceptCookies(){
        wait.until(elementToBeClickable(acceptCookiesButton)).click();
        return this;
    }

    public CareersPage clickCareersPageButton(){
        wait.until(elementToBeClickable(careersPageButton)).click();
        return new CareersPage(driver);
    }
}
