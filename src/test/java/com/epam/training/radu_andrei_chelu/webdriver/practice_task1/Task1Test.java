package com.epam.training.radu_andrei_chelu.webdriver.practice_task1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

class Task1Test {

    private static final String DYNAMIC_LOADING_URL = "https://the-internet.herokuapp.com/dynamic_loading/1";

    private static final By START = By.cssSelector("#start button");
    private static final By LOADING = By.id("loading");
    private static final By FINISH_MESSAGE = By.id("finish");

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUpTest() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/chromium-browser");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get(DYNAMIC_LOADING_URL);
    }

    @Test
    void helloWorldAfterLoading() {
        WebElement startButton = wait.until(elementToBeClickable(START));
        startButton.click();

        wait.until(invisibilityOfElementLocated(LOADING));
        WebElement message = wait.until(visibilityOfElementLocated(FINISH_MESSAGE));

        assertEquals("Hello World!", message.getText());
    }

    @AfterEach
    void testDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
