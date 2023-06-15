package com.placelab.tests;

import com.placelab.pages.*;
import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreateSinglePlaceSearchReport {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private CreateSinglePlaceSearchPage createSinglePlaceSearchPage;
    private ReportLoadingPage reportLoadingPage;
    private ReportsPage reportsPage;

    @Parameters("browser")
    @BeforeTest
    public void setUp(String browser) {
        this.driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com");
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.createSinglePlaceSearchPage = new CreateSinglePlaceSearchPage(driver);
        this.reportLoadingPage = new ReportLoadingPage(driver);
        this.reportsPage = new ReportsPage(driver);
    }

    @Parameters({"validEmail", "validPassword"})
    @Test(priority = 1)
    public void createSinglePlaceSearchReport(String validEmail, String validPassword) {
        this.loginPage.validateLoginPage();
        this.loginPage.enterCredentials(validEmail, validPassword);
        this.loginPage.clickOnLoginButton();
        this.homePage.validateHomePage();
        this.homePage.createReportDropdownClick();
        this.homePage.singlePlaceSearchReportClick();
        this.createSinglePlaceSearchPage.validateCreateSinglePlaceSearchPage();
        this.createSinglePlaceSearchPage.fillInCreateSinglePlaceSearchReport();
        final String reportId = this.reportLoadingPage.getReportId();

        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.urlToBe("https://demo.placelab.com/queries"));
        this.reportsPage.validateReportsPage();
        this.reportsPage.validateReportById(reportId);

        this.homePage.signOut();
        this.loginPage.validateLoginPage();
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}
