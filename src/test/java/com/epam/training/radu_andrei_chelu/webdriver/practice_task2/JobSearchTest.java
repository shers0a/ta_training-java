package com.epam.training.radu_andrei_chelu.webdriver.practice_task2;

import com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @ParameterizedTest
    @CsvSource({
            "java, all available countries"
    })
    public void userCanSearchForPositionByCriteria(String keyword, String location){
        String jobPageText = new HomePage(driver, "https://www.epam.com/")
                .open()
                .acceptCookies()
                .clickCareersPageButton()
                .startYourSearchButton()
                .selectLocation(location)
                .enterKeyword(keyword)
                .selectRemote()
                .search()
                .openLatestJob()
                .getPageText();

        assertTrue(jobPageText.toLowerCase().contains(keyword.toLowerCase()),
                "job page doesn't have the following keyword: " + keyword);
    }

    @AfterEach
    public void quitTest(){
        if(driver != null)
            driver.quit();
    }
}
