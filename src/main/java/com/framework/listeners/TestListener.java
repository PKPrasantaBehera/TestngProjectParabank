package com.framework.listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.framework.commons.WebCommons;
import com.framework.reports.ReportsClass;

public class TestListener implements ITestListener {

	public void onTestStart(ITestResult result) {
		ReportsClass.startReporting(result.getMethod().getMethodName());
		ReportsClass.logger.info("Test Execution Started for Test Case : "+result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		ReportsClass.logger.pass("Test Execution is Completed for Test Case : "+result.getMethod().getMethodName());
		ReportsClass.stopReporting();
	}

	public void onTestFailure(ITestResult result) {
		ReportsClass.logger.fail("Test Execution is Failed for Test Case : "+result.getMethod().getMethodName());
		ReportsClass.logger.fail("Test Execution is Failed because : "+result.getThrowable().getMessage());
		new WebCommons().attachScreenshot(result.getMethod().getMethodName());
		ReportsClass.stopReporting();
	}

	public void onTestSkipped(ITestResult result) {
		ReportsClass.logger.skip("Test Execution is Skipped for Test Case : "+result.getMethod().getMethodName());
		ReportsClass.logger.skip("Test Execution is Skipped because : "+result.getThrowable().getMessage());
		new WebCommons().attachScreenshot(result.getMethod().getMethodName());
		ReportsClass.stopReporting();
	}

}
