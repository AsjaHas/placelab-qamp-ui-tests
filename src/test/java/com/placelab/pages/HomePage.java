package com.placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private static WebDriverWait wait;
    private final static String EXPECTED_HOME_PAGE_TITLE = "PlaceLab - demo";
    private final String HOME_PAGE_URL = "https://demo.placelab.com/dashboard/traffic";
    private final By USER_ROLE = By.id("user-role");
    private final String EXPECTED_USER_ROLE = "Group Admin";
    private static final By USER_DROPDOWN_MENU = By.cssSelector("ul.pull-right li.dropdown a");


    private static final By SIGN_OUT_BUTTON = By.linkText("Sign out");
    private static final By CREATE_REPORT_DROPDOWN = By.id("create-menu");
    private static final By SINGLE_PLACE_SEARCH = By.id("singleplacesearch");

    public HomePage(final WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void validateHomePage() {
        Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, "Validate correct page url.");
        Assert.assertEquals(driver.getTitle(), EXPECTED_HOME_PAGE_TITLE, "Validate correct page title is displayed.");
        Assert.assertEquals(driver.findElement(USER_ROLE).getText(), EXPECTED_USER_ROLE, "Validate correct user role.");
        Assert.assertTrue(driver.findElement(USER_DROPDOWN_MENU).isDisplayed(), "Validate user dropdown menu is displayed.");
        Assert.assertTrue(driver.findElement(CREATE_REPORT_DROPDOWN).isDisplayed(), "Validate Create report menu is displayed.");
    }

    public void clickOnUserDropdownMenu() {
        Assert.assertTrue(driver.findElement(USER_DROPDOWN_MENU).isDisplayed(), "Validate dropdown menu arrow is displayed. ");
        driver.findElement(USER_DROPDOWN_MENU).click();
    }

    public void signOut() {
        this.clickOnUserDropdownMenu();
        Assert.assertTrue(driver.findElement(SIGN_OUT_BUTTON).isDisplayed(), "Validate Sign out button is displayed.");
        this.driver.findElement(SIGN_OUT_BUTTON).click();
    }

    public void createReportDropdownClick() {
        Assert.assertTrue(driver.findElement(CREATE_REPORT_DROPDOWN).isEnabled(), "Validate Create report dropdown is displayed.");
        driver.findElement(CREATE_REPORT_DROPDOWN).click();
    }

    public void singlePlaceSearchReportClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement singlePlaceSearchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SINGLE_PLACE_SEARCH));
        Assert.assertTrue(singlePlaceSearchElement.isDisplayed(), "Validate Single Place Search is displayed");
        driver.findElement(SINGLE_PLACE_SEARCH).click();
    }
}
