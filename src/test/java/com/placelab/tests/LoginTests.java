package com.placelab.tests;

import com.placelab.pages.HomePage;
import com.placelab.pages.LoginPage;
import com.placelab.utils.RandomEmailGenerator;
import com.placelab.utils.RandomPasswordGenerator;
import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Parameters("browser")
    @BeforeTest
    public void setUp(String browser) {
        this.driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com");
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
    }

    //Positive test with valid credentials by click
    @Parameters({"validEmail", "validPassword"})
    @Test(priority = 1)
    public void logInAndSignOutSucceedsByClick(final String validEmail, final String validPassword) {
        this.loginPage.validateLoginPage();
        this.loginPage.enterCredentials(validEmail, validPassword);
        this.loginPage.clickOnLoginButton();
        this.homePage.validateHomePage();
        this.homePage.signOut();
        this.loginPage.validateLoginPage();
    }

    //Positive test with valid credentials by enter
    @Parameters({"validEmail", "validPassword"})
    @Test(priority = 2)
    public void logInAndSignOutSucceedsByKeyPress(final String validEmail, final String validPassword) {
        this.loginPage.validateLoginPage();
        this.loginPage.enterCredentials(validEmail, validPassword);
        this.loginPage.clickLoginButtonByEnter();
        this.homePage.validateHomePage();
        this.homePage.signOut();
        this.loginPage.validateLoginPage();
    }

    //Negative test with invalid email and valid password
    @Parameters("validPassword")
    @Test(priority = 3)
    public void logInFailsWithInvalidEmail(final String validPassword) {
        this.loginPage.validateLoginPage();
        String invalidEmail = RandomEmailGenerator.generateRandomEmail();
        this.loginPage.enterCredentials(invalidEmail, validPassword);
        this.loginPage.clickOnLoginButton();
        this.loginPage.validateLoginPage(); //Validate that user stayed on the login page after invalid login try
    }

    //Negative test with valid email and invalid password
    @Parameters("validEmail")
    @Test(priority = 4)
    public void logInFailsWithInvalidPassword(final String validEmail) {
        this.loginPage.validateLoginPage();
        String invalidPassword = RandomPasswordGenerator.generateRandomPassword(10);
        this.loginPage.enterCredentials(validEmail, invalidPassword);
        this.loginPage.clickOnLoginButton();
        this.loginPage.validateLoginPage(); //Validate that user stayed on the login page after invalid login try
    }

    @Parameters("validEmail")
    @Test(priority = 5)
    public void logInFailsWithEmptyPassword(String validEmail) {
        this.loginPage.validateLoginPage();
        this.loginPage.enterCredentials(validEmail, "");
        this.loginPage.clickOnLoginButton();
        this.loginPage.validateLoginPage(); //Validate that user stayed on the login page after invalid login try
        this.loginPage.validateErrorMessageOnLogin();
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}
