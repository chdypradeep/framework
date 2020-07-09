package com.tau.reports;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	static ExtentReports extent;
	final static String filePath = "Extent.html";
	static Map<Integer, ExtentTest> extentTestMap = new ConcurrentHashMap<>();
	static Map<Integer, ExtentTest> extentNodeMap = new ConcurrentHashMap<>();
	// static ExtentTest test;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			ExtentHtmlReporter html = new ExtentHtmlReporter(
					System.getProperty("user.dir") + File.separator + "logs" + File.separator + "Extent.html");
			html.config().setDocumentTitle("Chai Pe Charcha");
			html.config().setReportName("Tau ki report");
			html.config().setTheme(Theme.DARK);
			extent = new ExtentReports();
			extent.attachReporter(html);
		}
		return extent;
	}

	public static ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
		// return test;
	}

	public static ExtentTest getNode() {
		return (ExtentTest) extentNodeMap.get((int) (Thread.currentThread().getId()));
	}

	public static ExtentTest startTest(String testName) {
		ExtentTest test = getReporter().createTest(testName);
		extentTestMap.put((int) (Thread.currentThread().getId()), test);
		return test;
	}

	public static ExtentTest startNode(String testName, String desc) {
		ExtentTest test = extentTestMap.get((int) (Thread.currentThread().getId()));
		ExtentTest node = test.createNode(testName, desc);
		extentNodeMap.put((int) (Thread.currentThread().getId()), node);
		return node;
	}

}
