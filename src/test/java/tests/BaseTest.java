package tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import pages.googlePages;
import pages.ixigoPages;
import utils.DriverFactory;

public class BaseTest {
	
	public ixigoPages ixigopage;
	public googlePages googlepages;
	
	@Test
	public void test() throws MalformedURLException, InterruptedException {
		WebDriver driver1 =  DriverFactory.getDriver("chrome");
		driver1.get("https://www.ixigo.com");
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ixigopage = new ixigoPages(driver1);
		ixigopage.clickSearchButton();
		Thread.sleep(4000);
		driver1.close();
		//driver.launchApp();
	}
	
	@Test
	public void test1() throws MalformedURLException, InterruptedException {
		WebDriver driver2 =  DriverFactory.getDriver("chrome");
		driver2.get("https://www.google.com");
		googlepages = new googlePages(driver2);
		driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		googlepages.enterSearchValue("ashish kumar");
		Thread.sleep(4000);
		driver2.close();
		//driver.launchApp();
	}
}
