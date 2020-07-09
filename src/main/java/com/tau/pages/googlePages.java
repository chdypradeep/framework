package com.tau.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tau.utils.BaseUtils;

public class googlePages extends BaseUtils {
	Logger logger = LogManager.getLogger(googlePages.class);

	public googlePages(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='q']")
	public WebElement googleSearchBar;

	public void enterSearchValue(String input) {
		logger.info("inside enter search value method");
		click(googleSearchBar);
		sendKeys(googleSearchBar, input);
		sendKeys(googleSearchBar, Keys.chord(Keys.ENTER));
//		googleSearchBar.click();
//		googleSearchBar.sendKeys(input);
//		googleSearchBar.sendKeys(Keys.ENTER);
	}
}
