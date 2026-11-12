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

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/chromium-browser");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
    }

    @Test
    void helloWorldAppearsAfterLoading() {
        WebElement startButton = wait.until(elementToBeClickable(By.cssSelector("#start button")));
        startButton.click();

        wait.until(invisibilityOfElementLocated(By.id("loading")));
        WebElement message = wait.until(visibilityOfElementLocated(By.id("finish")));

        assertEquals("Hello World!", message.getText());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
