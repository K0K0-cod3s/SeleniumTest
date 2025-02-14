package com.example.tests;

import com.example.pages.GoogleSearchPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {
    private static WebDriver driver;
    private static GoogleSearchPage googleSearchPage;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        googleSearchPage = new GoogleSearchPage(driver);
    }

    @Test
    public void testGoogleSearch() {
        boolean testPassed = false;
        try {
            driver.get("https://www.google.com");

            // Use WebDriverWait to ensure the search box is clickable before interacting
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(160)); // Wait for max 10 seconds
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
            googleSearchPage.search("Selenium WebDriver");

            // Wait for the page title to update after search
            wait.until(ExpectedConditions.titleContains("Selenium WebDriver"));
            WebElement firstLink = driver.findElement(By.cssSelector("h3")); // First result title
            firstLink.click();
            // Validate the page title contains the expected keyword
            assertTrue(driver.getTitle().contains("Selenium WebDriver"), "Title does not contain expected text");

            // If the assertion passes, set testPassed to true
            testPassed = true;
        } catch (Exception e) {
            e.printStackTrace(); // Print the error stack trace in case of failure
        } finally {
            // Print the message based on the test result
            if (testPassed) {
                System.out.println("Test Passed: Google search title contains 'Selenium WebDriver'.");
            } else {
                System.out.println("Test Failed: Google search title did not contain 'Selenium WebDriver'.");
            }
        }
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
