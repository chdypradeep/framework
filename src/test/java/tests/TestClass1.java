package tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import pages.ixigoPages;

public class TestClass1 extends BaseTest {
	private ixigoPages ixigopage;

	@Test(description = "test2 description")
	public void test2() throws MalformedURLException, InterruptedException {
		driver.get("https://www.ixigo.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ixigopage = new ixigoPages(driver);
		ixigopage.clickSearchButton();
		Thread.sleep(1000);

	}

	@Test(description = "test3 description")
	public void test3() throws MalformedURLException, InterruptedException {
		driver.get("https://www.ixigo.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ixigopage = new ixigoPages(driver);
		ixigopage.clickSearchButton();
		Thread.sleep(1000);

	}
}
