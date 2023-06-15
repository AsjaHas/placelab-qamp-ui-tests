package com.placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    private final static String EXPECTED_LOGIN_PAGE_TITLE = "PlaceLab";
    private static final By LOGIN_FORM = By.id("login_form");
    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final String EXPECTED_LOGIN_ERROR_MESSAGE = "Invalid credentials!";
    private static final By LOGIN_ERROR_MESSAGE = By.cssSelector("div.span12 > div.error-area");
    private final String LOGIN_URL = "https://demo.placelab.com/";
    private static final By FORGOT_PASSWORD_LINK = By.cssSelector("#password-area > a");
    private static final By LOGIN_BUTTON = By.xpath("//input[@value='Log in']");
    private WebDriver driver;

    public LoginPage(final WebDriver driver) {
        this.driver = driver;
    }

    public void validateLoginPage() {
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL, "Validate correct page url.");
        Assert.assertEquals(driver.getTitle(), EXPECTED_LOGIN_PAGE_TITLE, "Validate correct page title is displayed.");
        Assert.assertTrue(driver.findElement(LOGIN_FORM).isDisplayed(), "Validate login form is displayed.");
        Assert.assertTrue(driver.findElement(EMAIL_INPUT).isDisplayed(), "Validate email field is displayed.");
        Assert.assertTrue(driver.findElement(EMAIL_INPUT).isEnabled(), "Validate email input field is enabled.");
        Assert.assertTrue(driver.findElement(PASSWORD_INPUT).isDisplayed(), "Validate password field is displayed.");
        Assert.assertTrue(driver.findElement(PASSWORD_INPUT).isEnabled(), "Validate password input field is enabled.");
        Assert.assertTrue(driver.findElement(FORGOT_PASSWORD_LINK).isDisplayed(), "Validate forget password link is displayed.");
        Assert.assertTrue(driver.findElement(FORGOT_PASSWORD_LINK).isEnabled(), "Validate forget password link is clickable.");
        Assert.assertTrue(driver.findElement(LOGIN_BUTTON).isDisplayed(), "Validate login button is displayed.");
        Assert.assertTrue(driver.findElement(LOGIN_BUTTON).isEnabled(), "Validate login button is clickable.");
    }

    public void enterCredentials(final String email, final String password) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clickOnLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void clickLoginButtonByEnter() {
        driver.findElement(PASSWORD_INPUT).sendKeys(Keys.ENTER);
    }

    public void validateErrorMessageOnLogin() {
        final String actualLoginErrorMessage = driver.findElement(LOGIN_ERROR_MESSAGE).getText();
        Assert.assertEquals(actualLoginErrorMessage, EXPECTED_LOGIN_ERROR_MESSAGE, "Validate correct error message is displayed.");
    }
}
