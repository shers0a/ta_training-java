package com.epam.training.radu_andrei_chelu.webdriver.practice_task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JobSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void startTest(){
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/chromium-browser");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testExistenceOfCookiesAcceptance(){
        driver.manage().window().maximize();

        driver.get("https://www.epam.com/");

//        WebElement acceptCookiesButton = wait.until(elementToBeClickable(By.cssSelector("")));
    }

    @AfterEach
    public void quitTest(){
        if(driver != null)
            driver.quit();
    }
}
