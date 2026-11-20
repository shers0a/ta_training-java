package com.epam.training.radu_andrei_chelu.webdriver.practice_task2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class JobDetailsPage extends BasePage {
    public JobDetailsPage(WebDriver driver){
        super(driver);
    }

    public String getPageText(){
        wait.until(urlContains("/vacancy/"));
        return driver.findElement(By.tagName("body")).getText();
    }
}
