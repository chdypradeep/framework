package tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.googlePages;
import pages.ixigoPages;
import utils.DriverFactory;

public class BaseTest {
	
	public ixigoPages ixigopage;
	public googlePages googlepages;
	public WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}
	@Test
	public void test() throws MalformedURLException, InterruptedException {
		driver =  DriverFactory.getDriver("chrome");
		driver.get("https://www.ixigo.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ixigopage = new ixigoPages(driver);
		ixigopage.clickSearchButton();
		Thread.sleep(1000);
		driver.close();
		driver.quit();
		//driver.launchApp();
	}
	
	@Test
	public void test1() throws MalformedURLException, InterruptedException {
		driver =  DriverFactory.getDriver("chrome");
		driver.get("https://www.google.com");
		googlepages = new googlePages(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		googlepages.enterSearchValue("ashish kumar");
		Thread.sleep(1000);
		driver.close();
		driver.quit();
		//driver.launchApp();
	}
	
	
}
