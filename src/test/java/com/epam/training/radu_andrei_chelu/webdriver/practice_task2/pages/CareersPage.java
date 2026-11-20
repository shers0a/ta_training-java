package com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class CareersPage extends BasePage {
    private final By startSearchButton = By.partialLinkText("START YOUR SEARCH"); // PartialLinkText locator

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public JobSearchPage startYourSearchButton() {
        wait.until(elementToBeClickable(startSearchButton)).click();
        return new JobSearchPage(driver);
    }
}
