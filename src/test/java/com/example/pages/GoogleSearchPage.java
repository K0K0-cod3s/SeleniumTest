package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {
    private WebDriver driver;

    // Use @FindBy for locating elements
    @FindBy(name = "q")
    private WebElement searchBox;

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void search(String query) {
        searchBox.sendKeys(query);
        searchBox.submit();
    }
}
