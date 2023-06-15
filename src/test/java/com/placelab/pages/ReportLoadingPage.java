package com.placelab.pages;

import org.openqa.selenium.WebDriver;

public class ReportLoadingPage {
    private final WebDriver driver;

    public ReportLoadingPage(final WebDriver driver) {
        this.driver = driver;
    }

    public String getReportId() {
        String currentUrl = driver.getCurrentUrl();
        String basicUrl = "https://demo.placelab.com/progress/";
        String reportId = extractDifference(currentUrl, basicUrl);
        return reportId;
    }

    public static String extractDifference(String currentUrl, String basicUrl) {
        int startIndex = basicUrl.length();
        return currentUrl.substring(startIndex);
    }
}
