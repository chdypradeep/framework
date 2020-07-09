package com.tau.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUtils {
	Logger logger = LogManager.getLogger(BaseUtils.class);
	WebDriver driver;

	public BaseUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void click(WebElement element) {
		logger.debug("clicking on element " + element);
		waitForElementVisibility(element);
		element.click();
	}

	public void sendKeys(WebElement element, String text) {
		logger.debug("sending keys to element " + element);
		waitForElementVisibility(element);
		element.sendKeys(text);
	}

}
