package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class googlePages extends Basepage {

	public googlePages(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@name='q']")
	public WebElement googleSearchBar;
	
	public void enterSearchValue(String input) {
		googleSearchBar.click();
		googleSearchBar.sendKeys(input);
		googleSearchBar.sendKeys(Keys.ENTER);
	}
}
