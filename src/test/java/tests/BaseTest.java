package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utils.DriverFactory;

public class BaseTest {

	public WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	@Parameters({ "browser" })
	@BeforeClass()
	public void beforeTest(String browser) throws MalformedURLException {
		driver = DriverFactory.getDriver(browser);
	}

	@AfterClass()
	public void afterMethod() {
		driver.quit();
	}

}
