package com.placelab.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CreateSinglePlaceSearchPage {
    private final WebDriver driver;
    private final String CREATE_SINGLE_PLACE_SEARCH_REPORT_URL = "https://demo.placelab.com/places/single_place_searches/new";
    private final static String EXPECTED_REPORT_HEADER = "placeCreate Single Place Search Report";
    private static final By REPORT_HEADER = By.cssSelector("div.report-header");
    private static final By REPORT_NAME_INPUT = By.id("name");
    private static final By PLACE_NAME_INPUT = By.id("single_text");
    private static final By PHONE_INPUT = By.id("single_phone");
    private static final By LOCATION_INPUT = By.id("location_name");
    private static final By CATEGORY_DROPDOWN_MENU_BUTTON = By.cssSelector("div.btn-group > button.multiselect > b.caret");
    private static final By LOCATION_LATITUDE = By.id("city_lat");
    private static final By LOCATION_LONGITUDE = By.id("city_lng");
    private static final String EXPECTED_REPORT_NAME_INPUT_PLACEHOLDER = "Enter report name...";
    private static final String EXPECTED_PLACE_NAME_INPUT_PLACEHOLDER = "Place Name";
    private static final String EXPECTED_LOCATION_INPUT_PLACEHOLDER = "e.g. W Illinois St, Chicago";
    private static final By CREATE_REPORT_BUTTON = By.xpath("//button[contains(text(), 'Create Report')]");
    private final static By SUGGESTED_ADDRESS_FROM_DROPDOWN = By.cssSelector("ul.typeahead.dropdown-menu");
    private final static By IS_THIS_YOUR_LOCATION_CONFIRMATION = By.xpath("//button[contains(text(), 'Yes')]");


    public CreateSinglePlaceSearchPage(final WebDriver driver) {
        this.driver = driver;
    }

    public void validateCreateSinglePlaceSearchPage() {
        Assert.assertEquals(driver.getCurrentUrl(), CREATE_SINGLE_PLACE_SEARCH_REPORT_URL, "Validate correct page url.");
        Assert.assertTrue(driver.findElement(REPORT_HEADER).isDisplayed(), "Validate report page header is displayed.");
        Assert.assertEquals(driver.findElement(REPORT_HEADER).getText(), EXPECTED_REPORT_HEADER, "Validate correct report page header.");
        Assert.assertTrue(driver.findElement(REPORT_NAME_INPUT).isDisplayed(), "Validate report name input field is displayed.");
        Assert.assertTrue(driver.findElement(PLACE_NAME_INPUT).isDisplayed(), "Validate place name input field is displayed.");
        Assert.assertTrue(driver.findElement(PHONE_INPUT).isDisplayed(), "Validate phone input field is displayed.");
        Assert.assertTrue(driver.findElement(LOCATION_INPUT).isDisplayed(), "Validate location input field is displayed.");
        Assert.assertTrue(driver.findElement(LOCATION_LATITUDE).isDisplayed(), "Validate location latitude field is displayed.");
        Assert.assertTrue(driver.findElement(LOCATION_LONGITUDE).isDisplayed(), "Validate location longitude field is displayed.");
        Assert.assertTrue(driver.findElement(CATEGORY_DROPDOWN_MENU_BUTTON).isEnabled(), "Validate category dropdown menu field is clickable.");
        Assert.assertEquals(driver.findElement(REPORT_NAME_INPUT).getAttribute("placeholder"), EXPECTED_REPORT_NAME_INPUT_PLACEHOLDER, "Validate correct text is on the report name field.");
        Assert.assertEquals(driver.findElement(PLACE_NAME_INPUT).getAttribute("placeholder"), EXPECTED_PLACE_NAME_INPUT_PLACEHOLDER, "Validate correct text is on the place name field.");
        Assert.assertEquals(driver.findElement(LOCATION_INPUT).getAttribute("placeholder"), EXPECTED_LOCATION_INPUT_PLACEHOLDER, "Validate correct text is on the location field.");
        Assert.assertTrue(driver.findElement(CREATE_REPORT_BUTTON).isDisplayed(), "Validate create report submit button is displayed.");
    }

    public void fillInCreateSinglePlaceSearchReport() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //generate fake report name, place name, phone number and address
        Faker faker = new Faker();
        Random random = new Random();
        final String location = faker.country().capital();
//        final String location = "Chicago"; // Used to run the test, because some faker locations didn't have suggested address from dropdown
        final String placeName = faker.country().name();
        int randomReportNumber = (int) (Math.random() * 100); // Generate a random number between 0 and 99
        final String reportName = location.toString() + " Report" + randomReportNumber;
        final String phoneNumber = faker.phoneNumber().cellPhone();

        // fill out input fields
        driver.findElement(LOCATION_INPUT).sendKeys(location);
        this.driver.findElement(LOCATION_INPUT).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUGGESTED_ADDRESS_FROM_DROPDOWN));
        driver.findElement(SUGGESTED_ADDRESS_FROM_DROPDOWN).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(IS_THIS_YOUR_LOCATION_CONFIRMATION));
        driver.findElement(IS_THIS_YOUR_LOCATION_CONFIRMATION).click();

        // I had a lot of trouble finding a way to select, some options that seemed logical to me weren't working, and it took me way too much time to figure out
        // <select> has display:none and it is useless.
        // The real select is actually this div.category-list
        driver.findElement(By.className("category-list")).click();

        // We select the options, and click on a random one, to select it
        int randomNum = random.nextInt(73);
        List<WebElement> options = driver.findElement(By.className("options-wrapper")).findElements(By.cssSelector("li"));
        options.get(randomNum).click();

        driver.findElement(PLACE_NAME_INPUT).sendKeys(placeName);
        driver.findElement(REPORT_NAME_INPUT).sendKeys(reportName);
        driver.findElement(PHONE_INPUT).sendKeys(phoneNumber);
        driver.findElement(CREATE_REPORT_BUTTON).click();
    }
}
