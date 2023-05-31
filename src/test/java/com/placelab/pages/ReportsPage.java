package com.placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ReportsPage {
    private final WebDriver driver;
    private static WebDriverWait wait;
    private final static String EXPECTED_REPORTS_PAGE_TITLE = "PlaceLab - demo";
    private final String EXPECTED_REPORTS_PAGE_LINK = "https://demo.placelab.com/queries";
    private final static By REPORTS_HEADER = By.linkText("Reports");
    private final static By SEARCH_BAR = By.id("query_search_name");
    private final static By SEARCH_FILTERS = By.id("filters-table");

    public ReportsPage(final WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void validateReportsPage() {
        Assert.assertEquals(driver.getCurrentUrl(), EXPECTED_REPORTS_PAGE_LINK, "Validate correct page url.");
        Assert.assertEquals(driver.getTitle(), EXPECTED_REPORTS_PAGE_TITLE, "Validate correct page title is displayed.");
        Assert.assertTrue(driver.findElement(REPORTS_HEADER).isDisplayed(), "Validate reports header on Reports page is displayed.");
        Assert.assertTrue(driver.findElement(SEARCH_BAR).isDisplayed(), "Validate search bar is displayed.");
        Assert.assertTrue(driver.findElement(SEARCH_FILTERS).isDisplayed(), "Validate search filters are displayed.");

    }

    public void validateReportById(String reportId) {
        String requestReportId = "request_" + reportId;
        Assert.assertEquals(driver.findElement(By.id(requestReportId)).getAttribute("id"), requestReportId, "Validate report with report ID is found.");
    }
}
