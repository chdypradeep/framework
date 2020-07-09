package com.tau.tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.tau.pages.googlePages;
import com.tau.tests.BaseTest;

public class TestClass2 extends BaseTest {

	private googlePages googlepages;

	@Test(description = "test1 description")
	public void test1() throws MalformedURLException, InterruptedException {
		driver.get("https://www.google.com");
		googlepages = new googlePages(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		googlepages.enterSearchValue("ashish kumar");
		Thread.sleep(1000);
	}

	@Test(description = "test4 description")
	public void test4() throws MalformedURLException, InterruptedException {
		driver.get("https://www.google.com");
		googlepages = new googlePages(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		googlepages.enterSearchValue("ashish kumar");
		Thread.sleep(1000);
	}

}
