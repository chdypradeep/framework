package listeners;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import reports.ExtentReport;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestListner implements ITestListener {
	Logger logger = LogManager.getLogger(TestListner.class);

	public void onTestStart(ITestResult result) {
		// not implemented
		ExtentReport.startTest(result.getName(), result.getMethod().getDescription());
	}

	/**
	 * Invoked each time a test succeeds.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */
	public void onTestSuccess(ITestResult result) {
		ExtentReport.getTest().log(Status.PASS, "test is Skipped");
	    
//		try {
//			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
//					.get(result.getInstance());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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

	/**
	 * Invoked each time a test fails.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */
	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;
//		try {
//			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
//					.get(result.getInstance());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		if (result.getThrowable() != null) {
			logger.error(result.getThrowable().getStackTrace());
			ExtentReport.getTest().fail(result.getThrowable());

		} else {
			ExtentReport.getTest().fail(result.getMethod().getMethodName() + " has failed");
		}
//		try {
//			// ExtentReport.getTest().addScreenCaptureFromBase64String(getScreenShotString(driver));
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

	/**
	 * Invoked each time a test is skipped.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SKIP
	 */
	public void onTestSkipped(ITestResult result) {
		ExtentReport.getTest().log(Status.SKIP, "test is Skipped");
	}

	/**
	 * Invoked each time a method fails but has been annotated with
	 * successPercentage and this failure still keeps it within the success
	 * percentage requested.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	/**
	 * Invoked each time a test fails due to a timeout.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 */
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	/**
	 * Invoked before running all the test methods belonging to the classes inside
	 * the &lt;test&gt; tag and calling all their Configuration methods.
	 */
	public void onStart(ITestContext context) {
		// ExtentReport.getTest().log(Status.INFO, "test is started");
		ExtentReport.getReporter().flush();
		
	}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have run and all their Configuration methods have been
	 * called.
	 */
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
			BufferedImage bi = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
					.takeScreenshot(driver).getImage();
			ImageIO.write(bi, "jpg", outputfile);
		}
		FileUtils.copyFile(outputfile, new File(imagepath));
		byte[] fileContent = Base64.encodeBase64(FileUtils.readFileToByteArray(outputfile));
		return fileContent;
	}
}
