package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ixigoPages extends Basepage {
	Logger logger = LogManager.getLogger(ixigoPages.class);

	public ixigoPages(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[text() = 'Search']")
	public WebElement searchButton;

	public void clickSearchButton() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		searchButton.click();
	}
}
