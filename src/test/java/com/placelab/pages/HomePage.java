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
        Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, "Incorrect page url.");
        Assert.assertEquals(driver.getTitle(), EXPECTED_HOME_PAGE_TITLE, "Incorrect page title is displayed.");
        Assert.assertEquals(driver.findElement(USER_ROLE).getText(), EXPECTED_USER_ROLE, "Incorrect user role.");
        Assert.assertTrue(driver.findElement(USER_DROPDOWN_MENU).isDisplayed(), "User dropdown menu is not displayed.");
        Assert.assertTrue(driver.findElement(CREATE_REPORT_DROPDOWN).isDisplayed(), "Create report menu is not displayed.");
    }

    public void clickOnUserDropdownMenu() {
        Assert.assertTrue(driver.findElement(USER_DROPDOWN_MENU).isDisplayed(), "Dropdown menu arrow is not displayed. ");
        driver.findElement(USER_DROPDOWN_MENU).click();
    }

    public void signOut() {
        this.clickOnUserDropdownMenu();
        Assert.assertTrue(driver.findElement(SIGN_OUT_BUTTON).isDisplayed(), "Sign out button is not displayed.");
        this.driver.findElement(SIGN_OUT_BUTTON).click();
    }

    public void createReportDropdownClick() {
        Assert.assertTrue(driver.findElement(CREATE_REPORT_DROPDOWN).isEnabled(), "Create report dropdown is not displayed.");
        driver.findElement(CREATE_REPORT_DROPDOWN).click();
    }

    public void singlePlaceSearchReportClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement singlePlaceSearchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SINGLE_PLACE_SEARCH));
        Assert.assertTrue(singlePlaceSearchElement.isDisplayed(), "Single Place Search is displayed");
        driver.findElement(SINGLE_PLACE_SEARCH).click();
    }
}
