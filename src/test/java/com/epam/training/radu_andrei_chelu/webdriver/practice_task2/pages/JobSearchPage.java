package com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class JobSearchPage extends BasePage {
    private static final String LOCATION_OPTION_XPATH =
            "//div[contains(@class,'dropdown__option') and normalize-space()='%s']"; // XPath with functions/operators

    private final By resultsCounter = By.xpath("//div[not(*) and contains(., 'jobs found')]"); // XPath with functions

    @FindBy(name = "search") // Name locator
    private WebElement keywordInput;

    @FindBy(className = "dropdown__control") // ClassName locator
    private WebElement locationDropdown;

    @FindBy(xpath = "//input[@name='vacancy_type-Remote']/following-sibling::label") // XPath with axes
    private WebElement remoteLabel;

    @FindBy(xpath = "//form//button[@type='submit']") // relative XPath
    private WebElement searchButton;

    @FindBy(css = "[data-testid='job-card-link']") // CSS selector
    private List<WebElement> jobTitles;

    public JobSearchPage(WebDriver driver) {
        super(driver);
    }

    public JobSearchPage acceptCookies() {
        acceptCookiesAndWaitForBannerDisappearIfShown();
        return this;
    }

    public JobSearchPage selectLocation(String location) {
        wait.until(elementToBeClickable(locationDropdown)).click();
        
        By option = By.xpath(String.format(LOCATION_OPTION_XPATH, location));
        
        wait.until(elementToBeClickable(option)).click();

        return this;
    }

    public JobSearchPage selectRemote() {
        clickAndWaitForResultsRefresh(remoteLabel);

        return this;
    }

    public JobSearchPage enterKeyword(String keyword) {
        wait.until(visibilityOf(keywordInput));
        keywordInput.clear();
        keywordInput.sendKeys(keyword);

        return this;
    }

    public JobSearchPage search() {
        clickAndWaitForResultsRefresh(searchButton);

        return this;
    }


    public JobDetailsPage openLatestJob() {
        wait.until(d -> !jobTitles.isEmpty());
        wait.until(elementToBeClickable(jobTitles.get(0))).click();

        return new JobDetailsPage(driver);
    }

    private void clickAndWaitForResultsRefresh(WebElement element) {
        wait.until(d -> !jobTitles.isEmpty());
        WebElement oldFirstJob = jobTitles.get(0);
        String oldCounter = wait.until(visibilityOfElementLocated(resultsCounter)).getText();

        wait.until(elementToBeClickable(element)).click();

        wait.until(stalenessOf(oldFirstJob));
        wait.until(d -> !jobTitles.isEmpty()
                && !d.findElement(resultsCounter).getText().equals(oldCounter));
    }
}
