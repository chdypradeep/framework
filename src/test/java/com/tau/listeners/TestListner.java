package com.tau.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.tau.reports.ExtentReport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class TestListner implements ITestListener {
	Logger logger = LogManager.getLogger(TestListner.class);

	public void onTestStart(ITestResult result) {
		logger.info(result.getName() + " has started");
		// not implemented
		if (ExtentReport.getTest() == null) {
			ExtentReport.startTest(result.getTestClass().getName());
		}
		ExtentReport.startNode(result.getName(), result.getMethod().getDescription());
		logger.info("<------- " + result.getName() + "has started --------->");
	}

	public void onTestSuccess(ITestResult result) {

		WebDriver driver = null;
		try {
			// Field driverClass =
			// result.getTestClass().getRealClass().getSuperclass().getDeclaredField("driver");
			driver = (WebDriver) result.getTestClass().getRealClass().getSuperclass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(result.getName() + " has passed");
		try {
			ExtentReport.getNode().pass(result.getName() + " is pass",
					MediaEntityBuilder
							.createScreenCaptureFromBase64String(
									new String(getScreenShotString(driver, result), StandardCharsets.US_ASCII))
							.build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("<------- " + result.getName() + "has finished --------->");
//		try {
//			ExtentReport.getTest().pass("Test Failed",
//					MediaEntityBuilder
//							.createScreenCaptureFromBase64String(
//									new String(getScreenShotString(driver, result), StandardCharsets.US_ASCII))
//							.build());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getSuperclass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.fatal(result.getName() + " has failed");
		if (result.getThrowable() != null) {
			logger.error(result.getThrowable().getStackTrace());
			try {
				ExtentReport.getNode().fail(result.getThrowable(),
						MediaEntityBuilder
								.createScreenCaptureFromBase64String(
										new String(getScreenShotString(driver, result), StandardCharsets.US_ASCII))
								.build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				ExtentReport.getNode().fail(result.getMethod().getMethodName() + " has failed",
						MediaEntityBuilder
								.createScreenCaptureFromBase64String(
										new String(getScreenShotString(driver, result), StandardCharsets.US_ASCII))
								.build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("<------- " + result.getName() + "has finished --------->");
//		try {
//			 ExtentReport.getTest().addScreenCaptureFromBase64String(getScreenShotString(driver));
//			ExtentReport.getTest().fail("Test Failed",
//					MediaEntityBuilder
//							.createScreenCaptureFromBase64String(
//									new String(getScreenShotString(driver, result), StandardCharsets.US_ASCII))
//							.build());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void onTestSkipped(ITestResult result) {
		ExtentReport.getNode().log(Status.SKIP, "test is Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onStart(ITestContext context) {
		// ExtentReport.getTest().log(Status.INFO, "test is started");
		ExtentReport.getReporter().flush();

	}

	public void onFinish(ITestContext context) {
		// ExtentReport.getTest().log(Status.INFO, "test is Finished");
		ExtentReport.getReporter().flush();
	}

	@SuppressWarnings("unchecked")
	public byte[] getScreenShotString(WebDriver driver, ITestResult result) throws IOException {
		String imagepath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator
				+ result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".jpg";
		File outputfile = null;
		if (driver instanceof AppiumDriver) {
			outputfile = ((AppiumDriver<MobileElement>) driver).getScreenshotAs(OutputType.FILE);
		} else {
			TakesScreenshot screenShot = (TakesScreenshot) driver;
			outputfile = screenShot.getScreenshotAs(OutputType.FILE);
		}
		FileUtils.copyFile(outputfile, new File(imagepath));
		byte[] fileContent = Base64.encodeBase64(FileUtils.readFileToByteArray(outputfile));
		return fileContent;
	}
}
