package com.placelab.tests;

import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTestInvalidEmail {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setUpDriver(String browser) {
        this.driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com");
    }

    @Parameters
    @BeforeTest
    private void validateLogInPage() {
//        In real life there would be separate test to validate full content and display of log in page
//        Should I do this separately with validation for every element of the page?

        //validate title
        final String actualPageTitle = driver.getTitle();
        final String expectedPageTitle = "PlaceLab";
        Assert.assertEquals(actualPageTitle, expectedPageTitle, "Validate page title");

        //validate log in form
        final boolean logInDisplayed = driver.findElement(By.id("login_form")).isDisplayed();
        Assert.assertTrue(logInDisplayed, "Validate log in form");

    }

    @Parameters({"invalidEmail", "validPassword"})
    @Test
    public void logIn(String invalidEmail, String validPassword) {
        driver.findElement(By.id("email")).sendKeys(invalidEmail);
        driver.findElement(By.id("password")).sendKeys(validPassword);
        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        final String actualNewPageUrl = driver.getCurrentUrl();
        final String expectedNewPageUrl = "https://demo.placelab.com/";
        Assert.assertEquals(actualNewPageUrl, expectedNewPageUrl, "Validate that user stayed on the login page after invalid login try");
        final boolean invalidCredentialsMessage = driver.findElement(By.className("error-area")).isDisplayed();
        Assert.assertTrue(invalidCredentialsMessage, "Validate user is not logged in and there is an error message displayed");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}
