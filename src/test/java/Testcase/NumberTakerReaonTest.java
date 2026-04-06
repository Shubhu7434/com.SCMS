package Testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.BaseTest;
import Pages.NumberTakerReasonPage;
import Utils.ScreenshotUtil;

public class NumberTakerReaonTest extends BaseTest {

	ExtentTest test;
	NumberTakerReasonPage NTR;

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		NTR = new NumberTakerReasonPage(driver);
		NTR.NavigatePage();
	}

	@Test(priority = 1)
	public void verifyNavigationTest() {
		test = extent.createTest("Verify Number Taker Reason Page Open Successfully");

		try {
			Assert.assertTrue(NTR.getPageHeaderName().isDisplayed(), "Number Taker Reason Page NOt Open Successfully");
			test.log(Status.PASS, "Number Taker Page Shoudl be Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.PASS, "Number Taker Page NOt Open Successfully");
			throw e;
		}

	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("Verify Page Title Display Correct");

		String expectedTitle = "Number Taker Reason | CMSERP";
		try {
			Assert.assertEquals(NTR.getPageTitle(), expectedTitle, "Page Title Not Display Correct");
			test.log(Status.PASS, "Page Title Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Page Title Not Display Correct , expected Title : " + expectedTitle
					+ " , But Acutal Title Display : " + NTR.getPageTitle());
			throw e;
		}
	}

	@Test(priority = 3)
	public void verifyValidatorMSG() {
		test = extent.createTest("Verify All Mandatory Field Validation Message Bind");
		NTR.checkValidatorMSG();

		try {
			Assert.assertTrue(NTR.getReasonValidatorMSG().isDisplayed(), "Reason Field Validator Message Display");
			Assert.assertTrue(NTR.getMarathiReasonValidatorMSG().isDisplayed(),
					"ReasonMarathi Field Validator Message Display");
			test.log(Status.PASS, "All Mandatory Field Validaton Message Bind Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "All Mandatory Field Validation Message NOt Bind");
			throw e;
		}

	}

	@Test(priority = 4)
	public void verifyCreateNewReasonDetailsFunctionality() {
		test = extent.createTest("Verify Create New Number Taker Reason Details Working Functionality");
		NTR.checkCreateNewReasonFunctionality();

		String actualMSG = waitForAlertAndGetText();

		try {
			Assert.assertTrue(
					actualMSG.equals("Reason details has been created.") || actualMSG.equals("Reason already exists."),
					"Number Taker Reason  Details Not Created Successfully");
			test.log(Status.PASS, "Number Taker Reason Details  Created Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Number Taker Reason Details Not Created Successfully");
			throw e;
		}
		acceptAlert();
	}

	@Test(priority = 5)
	public void verifyGridSearchFiledFunctionality() {
		test = extent.createTest("Verify Grid Table Search Field Working Functionality");
		NTR.checkGridSearchFunctionality();

		try {
			Assert.assertTrue(NTR.getGridReasonValue().contains("SMS PROBLEM"),
					"Grid Table Search Filed Functionality NOt Working");
			test.log(Status.PASS, "Enter Details Related Data Should Be Load Into Grid Table");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Enter Details Related Data NOt Load Into Grid Table");
			throw e;
		}
	}

	@Test(priority = 6)
	public void verifyEditPageName() throws InterruptedException {
		test = extent.createTest("Verify Edit PageName Display Correct");
		NTR.checkEditPageName();

		String expectedPageName = "Edit - Number Taker Reason";
		Thread.sleep(500);
		String path = ScreenshotUtil.captureScreenshot(driver, "Edit PageName");

		try {
			Assert.assertEquals(NTR.getEditPageName(), expectedPageName, "Edit Page Name NOt Display Correct");
			test.log(Status.PASS, "Edit Page Name Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL,
					"Edit Page Name NOt Display Correct Expected Page Name : " + expectedPageName
							+ " , But Actual PageName Display : " + NTR.getEditPageName())
					.addScreenCaptureFromPath(path);
			throw e;
		}
	}
	
	@Test(priority = 7)
	public void verifyViewPageName() throws InterruptedException {
		test = extent.createTest("Verify View Details  PageName Display Correct");
		NTR.checkViewDetailsPageName();

		String expectedPageName = "Number Taker Reason Details";
		Thread.sleep(500);
		String path = ScreenshotUtil.captureScreenshot(driver, "ViewPageName");

		try {
			Assert.assertEquals(NTR.getViewPageName(), expectedPageName, "View Details Page  Name NOt Display Correct");
			test.log(Status.PASS, "View Details Page Name Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL,
					"View Details  Page Name NOt Display Correct Expected Page Name : " + expectedPageName
							+ " , But Actual PageName Display : " + NTR.getViewPageName()
							
					
					)
					.addScreenCaptureFromPath(path);
			throw e;
		}
	}
	
	@Test(priority = 8)
	public void verifyBlockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Number Taker Reaon Details Block Functionality");
		
		NTR.checkBlockFunctionality();
		
		String ActualMSG = waitForAlertAndGetText();
		String ExpectedMSG = "Reason has been Blocked.";
		
		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Number Taker Reason Details  Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Number Taker Reason Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Number Taker Reason Details Block Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		acceptAlert();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[4]"));
		String Actualstatus = status.getText().trim();
		String Expectedstatus = "Blocked";

		try {
			Assert.assertEquals(Actualstatus, Expectedstatus, "After block Grid Table Status Not update Correctly");
			test.log(Status.PASS, "After Block Grid Table Status  update Correctly");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "After Block Grid Table Status Not update Correctly");
			throw e;
		}
	}
	
	@Test(priority = 9)
	public void verifyUnblockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Number Taker Details Unblock Working Functionality ");
		NTR.checkUnblockFunctionality();
		
		String ActualMSG = waitForAlertAndGetText();
		String ExpectedMSG = "Reason has been Activated.";
		
		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Number Taker Reason Details  Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Number Taker Reason Details Unblocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Number Taker Reason Details Unblock Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		acceptAlert();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[4]"));
		String Actualstatus = status.getText().trim();
		String Expectedstatus = "Active";

		try {
			Assert.assertEquals(Actualstatus, Expectedstatus, "After Unblock Grid Table Status Not update Correctly");
			test.log(Status.PASS, "After Unblock Grid Table Status  update Correctly");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "After Unblock Grid Table Status Not update Correctly");
			throw e;
		}
		
	}
	
	

}
