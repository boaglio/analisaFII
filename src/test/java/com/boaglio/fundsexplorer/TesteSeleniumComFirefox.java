package com.boaglio.fundsexplorer;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteSeleniumComFirefox {

    private static final String DRIVER_DIR="/home/fb/Downloads/firefox.driver/firefox/firefox-bin";

    public static void main(String[] args) {

        System.setProperty("webdriver.gecko.driver",DRIVER_DIR);

        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.setLogLevel(FirefoxDriverLogLevel.TRACE);
        options.setBinary(DRIVER_DIR);

        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver(options);

        driver.get("https://www.google.com");

        driver.quit();

        assertTrue(true);
    }
}