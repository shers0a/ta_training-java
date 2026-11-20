package com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class HomePage extends BasePage {
    private final By careersPageButton = By.linkText("Careers"); // LinkText locator

    private final String homeURL;

    public HomePage(WebDriver driver, String homeURL) {
        super(driver);
        this.homeURL = homeURL;
    }

    public HomePage open() {
        super.open(homeURL);
        return this;
    }

    public HomePage acceptCookies() {
        acceptCookiesAndWaitForBannerDisappear();
        return this;
    }

    public CareersPage clickCareersPageButton() {
        wait.until(elementToBeClickable(careersPageButton)).click();
        return new CareersPage(driver);
    }
}
