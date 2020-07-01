package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ixigoPages extends Basepage {
	
	public ixigoPages(WebDriver driver ) {
		super(driver);
	}

	@FindBy(xpath = "//button[text() = 'Search']") 
	WebElement searchButton; 
	
	public void clickSearchButton() {
		searchButton.click();
	}
}
