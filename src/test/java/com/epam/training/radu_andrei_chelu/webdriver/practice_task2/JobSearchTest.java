package com.epam.training.radu_andrei_chelu.webdriver.practice_task2;

import com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobSearchTest {
    private static final String HOME_URL = "https://www.epam.com/";

    // Snap Chromium has a private /tmp, so its default profile dir (where Chrome writes
    // DevToolsActivePort) is invisible to chromedriver. The snap's "common" dir is shared with the host.
    private static final Path SNAP_CHROMIUM = Path.of("/snap/bin/chromium");
    private static final Path SNAP_CHROMIUM_DATA = Path.of(System.getProperty("user.home"), "snap", "chromium", "common");

    private WebDriver driver;
    private Path profileDir;

    @BeforeEach
    public void startTest() throws IOException {
        ChromeOptions options = new ChromeOptions();
        if (Files.isExecutable(SNAP_CHROMIUM) && Files.isDirectory(SNAP_CHROMIUM_DATA)) {
            profileDir = Files.createTempDirectory(SNAP_CHROMIUM_DATA, "selenium-profile-");
            options.setBinary(SNAP_CHROMIUM.toString());
            options.addArguments("--user-data-dir=" + profileDir);
        }

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
        if (profileDir != null) {
            FileHandler.delete(profileDir.toFile());
        }
    }
}