package com.tau.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tau.utils.BaseUtils;

public class ixigoPages extends BaseUtils {
	// Logger logger = LogManager.getLogger(ixigoPages.class);

	public ixigoPages(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text() = 'Search']")
	public WebElement searchButton;

	@FindBy(xpath = "//img[@title='ixigo.com']")
	public WebElement logo;

	public void clickSearchButton() {

		click(searchButton);
	}

	public void clickLogo() {

		click(logo);
	}
}
