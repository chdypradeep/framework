package reports;

import java.io.File;

import java.util.HashMap;
import java.util.Map;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	static ExtentReports extent;
	final static String filePath = "Extent.html";
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	//static ExtentTest test;
	
	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			ExtentHtmlReporter html = new ExtentHtmlReporter(System.getProperty("user.dir")+File.separator+"logs"+File.separator+"Extent.html");
			html.config().setDocumentTitle("Chai Pe Charcha");
			html.config().setReportName("Tau ki report");
			html.config().setTheme(Theme.DARK);
			extent = new ExtentReports();
			extent.attachReporter(html);
		}
		return extent;
	}

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
		//return test;
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = getReporter().createTest(testName, desc);
		extentTestMap.put((int)(Thread.currentThread().getId()), test);
		return test;
	}
	
}
