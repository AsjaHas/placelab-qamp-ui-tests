package com.placelab.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSetup {
    public static WebDriver getWebDriver(String browser){
        if (browser.equals("Chrome")){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (browser.equals("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        throw new IllegalArgumentException("Please select Chrome or Firefox as browser");
    }
}
