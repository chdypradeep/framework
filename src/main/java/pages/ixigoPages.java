package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ixigoPages  extends Basepage  {
	WebDriver driver;
	Logger logger = LogManager.getLogger(ixigoPages.class);
	public ixigoPages(WebDriver driver ){
		super(driver);
	}

	@FindBy(xpath = "//button[text() = 'Search']") 
	public WebElement searchButton; 
	
	public void clickSearchButton() {
	//	System.out.println(searchButton.getText());
		
		searchButton.click();
	}
}
