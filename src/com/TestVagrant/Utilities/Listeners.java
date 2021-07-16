package com.TestVagrant.Utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Listeners extends TestListenerAdapter {

	public static ExtentReports extent;
	public static ExtentTest test;

	public void onStart(ITestContext testContest) {
		extent = new ExtentReports("../Reports/testReport.html");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.startTest(result.getTestName());// create new entry
		test.log(LogStatus.PASS, "Test case PASSED is: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.startTest(result.getTestName());// create new entry
		test.log(LogStatus.FAIL, "Test case FAILED is: " + result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.startTest(result.getTestName());// create new entry
		test.log(LogStatus.SKIP, "Test case SKIPPED is: " + result.getName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
