package com.tau.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

//	private static String driverType;
//	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>() {
//		public WebDriver initialValue(){
//			WebDriver driver = null;
//			try {
//				driver =  startDriver(driverType);
//				System.out.println("driver initialized");
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return driver;
//		}
//	};

	public static WebDriver getDriver(String driverTypeValue) throws MalformedURLException {
//		driverType = driverTypeValue;
//		return driverLocal.get();	
		return new DriverFactory().startDriver(driverTypeValue);
		// return startDriver(driverTypeValue);
	}

	private WebDriver startDriver(String driverType) throws MalformedURLException {
		driverType = driverType.toLowerCase().trim();
		WebDriver driver;
		switch (driverType) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "android":
			DesiredCapabilities androidCapabilites = new DesiredCapabilities();
			androidCapabilites.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			androidCapabilites.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
			androidCapabilites.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3a XL API 29");
			// androidCapabilites.setCapability(MobileCapabilityType.UDID, "emulator-5554");
			// androidCapabilites.setCapability("appActivity", "");
			// androidCapabilites.setCapability("appPackage", "");
			androidCapabilites.setCapability(MobileCapabilityType.APP, "//Users//pradeepsingh//Desktop//ixigo-app.apk");
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), androidCapabilites);
			break;
		case "ios":
			DesiredCapabilities iOSCapabilites = new DesiredCapabilities();
			iOSCapabilites.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			iOSCapabilites.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			iOSCapabilites.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone8");
			iOSCapabilites.setCapability("bundleId", "");
			iOSCapabilites.setCapability(MobileCapabilityType.APP, "");
			driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723"), iOSCapabilites);
		default:
			DesiredCapabilities remoteDriverCap = DesiredCapabilities.chrome();
			driver = new RemoteWebDriver(new URL(""), remoteDriverCap);
		}
		return driver;
	}

}
