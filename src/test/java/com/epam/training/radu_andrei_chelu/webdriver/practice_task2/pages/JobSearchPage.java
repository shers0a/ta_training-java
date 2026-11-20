package com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class JobSearchPage extends BasePage {

    @FindBy(name = "search")
    private WebElement keywordInput;

    @FindBy(className = "dropdown__control")
    private WebElement locationDropdown;

    @FindBy(xpath = "//input[@name='vacancy_type-Remote']/following-sibling::label")
    private WebElement remoteLabel;

    @FindBy(xpath = "//form//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(css = "a[class*='JobCard_labelLink']")
    private List<WebElement> jobTitles;

    public JobSearchPage(WebDriver driver){
        super(driver);
    }

    public JobSearchPage enterKeyword(String keyword){
        wait.until(visibilityOf(keywordInput));
        keywordInput.clear();
        keywordInput.sendKeys(keyword);

        return this;
    }

    public JobSearchPage selectLocation(String location){
        wait.until(elementToBeClickable(locationDropdown)).click();

        By option = By.xpath("//div[contains(@class,'dropdown__option') and normalize-space()='" + location + "']");
        wait.until(elementToBeClickable(option)).click();

        return this;
    }

    public JobSearchPage selectRemote(){
        WebElement oldFirstJob = null;

        if(jobTitles != null && !jobTitles.isEmpty()){
            oldFirstJob = jobTitles.get(0);
        }

        wait.until(elementToBeClickable(remoteLabel)).click();

        if(oldFirstJob != null){
            wait.until(stalenessOf(oldFirstJob));
        }

        return this;
    }

    public JobSearchPage search(){
        WebElement oldFirstJob = null;

        if(jobTitles != null && !jobTitles.isEmpty()){
            oldFirstJob = jobTitles.get(0);
        }

        wait.until(elementToBeClickable(searchButton)).click();

        if(oldFirstJob != null){
            wait.until(stalenessOf(oldFirstJob));
        }

        return this;
    }

    public JobDetailsPage openLatestJob(){
        wait.until(driver -> !jobTitles.isEmpty());
        wait.until(elementToBeClickable(jobTitles.get(0))).click();
        return new JobDetailsPage(driver);
    }

    public JobSearchPage acceptCookies(){
        super.acceptCookiesAndWaitForBannerDisappear();

        return this;
    }
}
