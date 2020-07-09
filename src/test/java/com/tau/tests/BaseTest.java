package com.tau.tests;

import java.io.File;
import java.net.MalformedURLException;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.tau.utils.DriverFactory;

public class BaseTest {

	public WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	@Parameters({ "browser" })
	@BeforeClass()
	public void beforeTest(String browser) throws MalformedURLException {
		String strFile = "logs" + File.separator + Thread.currentThread().getName();
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		ThreadContext.put("ROUTINGKEY", strFile);
		driver = DriverFactory.getDriver(browser);
	}

	@AfterClass()
	public void afterMethod() {
		driver.quit();
	}

}
