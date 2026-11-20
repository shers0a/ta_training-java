package com.epam.training.radu_andrei_chelu.webdriver.practice_task2;

import com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobSearchTest {
    private static final String HOME_URL = "https://www.epam.com/";

    private WebDriver driver;

    @BeforeEach
    public void startTest() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/chromium-browser");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @ParameterizedTest(name = "keyword={0}, location={1}")
    @CsvSource({
            "Java, All available countries",
            "Python, All available countries"
    })
    public void userCanSearchForPositionByCriteria(String keyword, String location) {
        String jobPageText = new HomePage(driver, HOME_URL)
                .open()
                .acceptCookies()
                .clickCareersPageButton()
                .startYourSearchButton()
                .acceptCookies()
                .selectLocation(location)
                .selectRemote()
                .enterKeyword(keyword)
                .search()
                .openLatestJob()
                .getPageText();

        assertTrue(jobPageText.toLowerCase().contains(keyword.toLowerCase()),
                "Job page does not contain the keyword: " + keyword);
    }

    @AfterEach
    public void quitTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
