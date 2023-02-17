package com.framework.reports;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportsClass {
	
	public static ExtentHtmlReporter html = null;
	public static ExtentReports extent = null;
	public static ExtentTest logger=null;
	
	@BeforeSuite(alwaysRun=true)
	public static void setupReport() {
		html = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationTestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(html);
		extent.setSystemInfo("Project Name", "Parabank");
		extent.setSystemInfo("Company Name", "Parasoft");
	}
	
	public static void startReporting(String testMethod) {
		logger = extent.createTest(testMethod);
	}
		
	public static void stopReporting() {
		extent.flush();
	}

}
