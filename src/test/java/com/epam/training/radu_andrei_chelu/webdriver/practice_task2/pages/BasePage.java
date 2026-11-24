package com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages;

import com.epam.training.radu_andrei_chelu.webdriver.practice_task2.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public abstract class BasePage {
    private static final Duration TIMEOUT = TestConfig.timeout();
    private static final Duration OPTIONAL_BANNER_TIMEOUT = TestConfig.optionalBannerTimeout();

    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler"); // ID locator
    private final By acceptCookiesBanner = By.id("onetrust-banner-sdk");               // ID locator

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    protected void open(String URL) {
        driver.get(URL);
    }

    protected void acceptCookiesAndWaitForBannerDisappear() {
        wait.ignoring(ElementClickInterceptedException.class)
                .until(d -> {
                    d.findElement(acceptCookiesButton).click();
                    return true;
                });
        wait.until(invisibilityOfElementLocated(acceptCookiesBanner));
    }

    protected void acceptCookiesAndWaitForBannerDisappearIfShown() {
        try {
            new WebDriverWait(driver, OPTIONAL_BANNER_TIMEOUT).until(visibilityOfElementLocated(acceptCookiesButton));
        } catch (TimeoutException e) {
            return;
        }
        acceptCookiesAndWaitForBannerDisappear();
    }
}
