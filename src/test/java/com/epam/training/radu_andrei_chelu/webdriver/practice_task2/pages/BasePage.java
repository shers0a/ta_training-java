package com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private static final Duration TIMEOUT = Duration.ofSeconds(15);

    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler"); //id locator
    private final By acceptCookiesBanner = By.id("onetrust-banner-sdk");

    protected BasePage(WebDriver driver){
        this.driver = driver;

        wait = new WebDriverWait(driver, TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    protected void open(String URL){
        driver.get(URL);
    }

    protected void acceptCookiesAndWaitForBannerDisappear(){
        wait.ignoring(ElementClickInterceptedException.class)
                .until(d -> {d.findElement(acceptCookiesButton).click();
                        return true;
                });
        wait.until(invisibilityOfElementLocated(acceptCookiesBanner));
    }
}