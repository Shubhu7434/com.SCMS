package Testcase;

import java.awt.Desktop;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BaseTest;
import Pages.FuelTypePage;
import Utils.ScreenshotUtil;

public class FuelType extends BaseTest {

	FuelTypePage FT;
	ExtentReports extent;
	ExtentTest test;
	WebDriverWait wait;
	String Reportpath = "C:\\Reports\\SCMS\\FuelType.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "Fuel Type Master");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");
	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		FT = new FuelTypePage(driver);
	}
	
	
	@Test(priority = 1)
	public void ChekNavigationFueltypePage() {
		test = extent.createTest("Verify Fuel type Page Open SUccessfully Or Not");
		FT.NavigatePage();
		
		WebElement pagename = driver.findElement(By.cssSelector(".page-title"));
		
		String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "FuelTypePage");

		try {
			Assert.assertTrue(pagename.isDisplayed(), "Fuel Type Page Not open Successfully");
			test.pass("Fuel type page Should be open Successfully").addScreenCaptureFromPath(screesnshotpath);
		} catch (AssertionError e) {
			test.fail("Fuel type Page Not Open Successfully");
			throw e;
		}
	
	}
	
	@Test(priority = 2)
	public void CheckPageTitle() {
		test = extent.createTest("Verify Page Title Display Correct");
		FT.NavigatePage();
		
		String actualtitle = getDriver().getTitle();
		String expectedtitle = "Fuel Type - SCMS";
		
		try {
			Assert.assertEquals(actualtitle, expectedtitle, "Page Title Not Display Correct");
			test.pass("Page Title Should Be Display Correct");
		} catch (AssertionError e) {
			test.fail("Page Title Not Display Correct Expect '" + expectedtitle + "' But Display '" + actualtitle);
			throw e;
		}
	}
	
	@Test(priority = 3)
	public void CheckValidatorMSG() {
		test = extent.createTest("Verify All Mandatory Field Validation Message Display Or Not");
		FT.NavigatePage();
		FT.CheckValidatorMSG();
		
		WebElement FueltypeMSG = driver.findElement(By.id("validatorFuelType"));
		WebElement MarathiFTMSG = driver.findElement(By.id("validatorFuelTypeMarathi"));
		WebElement FueltypeIDMSG = driver.findElement(By.id("validatorReferenceFuelTypeId"));
		
		
		String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "FueltypePage-ValidatorMSG");

		try {
			Assert.assertTrue(FueltypeMSG.isDisplayed(), "Fuel Type Field Validation Mesage Not Bind");
			Assert.assertTrue(MarathiFTMSG.isDisplayed(), "Fuel Type Marathi Field Validation Mesage Not Bind");
			Assert.assertTrue(FueltypeIDMSG.isDisplayed(), " Fuel type ID Field  Validation Mesage Not bind");
			test.pass("All Mandatory Field Validation Message Bind").addScreenCaptureFromPath(screesnshotpath);
		} catch (AssertionError e) {
			test.fail("All Mandatory Field Valiation Message Not Bind").addScreenCaptureFromPath(screesnshotpath);
			throw e;
		}
	}
	
	@Test(priority = 4)
	public void CheckValidatorMSGDisplayCorrect() {
		test = extent.createTest("Verify Fuel type Field Validation Message Display Correct");
		FT.NavigatePage();
		FT.CheckValidatorMSG();
		
		WebElement FueltypeMSG = driver.findElement(By.id("validatorFuelType"));
		String actualMSG = FueltypeMSG.getText().trim();
		String expectedMSG = "Please Enter Fuel Type";
		String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "FueltypePage-ValidatorMSG");
		
		try {
			Assert.assertEquals(actualMSG,  expectedMSG , "Fuel type Field Validation Message Not Display Correct");
			test.pass("Fuel type Field Validation Message  Display Correct");
		} catch (AssertionError e) {
			test.fail("Fuel type Field Validation Message Not Display Correct -:'" +actualMSG + "' Validation Message Display").addScreenCaptureFromPath(screesnshotpath);
			throw e;
		}
	}
	
	@Test(priority = 5)
	public void checkvalidatorMSGClear() {
		test = extent.createTest("Verify Afte Clear Invalid details Validation Message Clear Properly Or Not");
		FT.NavigatePage();
		FT.CheckvalidatorMSGClear();
		
		WebElement MarathiFTMSG = driver.findElement(By.id("validatorFuelTypeMarathi"));
		String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "FueltypePage-ValidatorMSG");
		
		try {
			Assert.assertFalse(MarathiFTMSG.isDisplayed() , "After Clear invalid Details And Enter Valid Details Still Validation Message Display");
			test.pass("After Clear invalid Details And Enter Valid Details  Validation Message Not Display");
		} catch (AssertionError e) {
			test.fail("After Clear invalid Details And Enter Valid Details Still Validation Message Display").addScreenCaptureFromPath(screesnshotpath);
		}
	}
	
	@Test(priority = 6)
	public void Checkcreatenewfueltypedetails() {
		test = extent.createTest("Verify Create new Fuel Type Details Working FUnctionality");
		FT.NavigatePage();
		FT.CheckCreateNewFuelType();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();
		
		try {
			Assert.assertTrue(actualMSG.equals("Fuel Type has been Created.") || actualMSG.equals("Fuel Type already exists.") , "Fuel Type Details Not Created Successfully");
			test.pass("Fuel Type Details  Created Successfully");
		} catch (AssertionError e) {
			test.fail("Fuel Type Details Not Created Successfully");
			throw e;
		}
		alt.accept();
		
	}
	
	@Test(priority = 7)
	public void CheckMaxLengthInputDetailsSave() {
		test = extent.createTest("Verify Max Lenth Input Details Save Suucessfully Or NOt");
		FT.NavigatePage();
		FT.CheckMaxLengthInputDetailsSave();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();
		
		try {
			Assert.assertTrue(actualMSG.equals("Fuel Type has been Created.") || actualMSG.equals("Fuel Type already exists.") , "Max Length Input Fuel Type Details Not Created Successfully");
			test.pass("Max length Input Fuel Type Details  Created Successfully");
		} catch (AssertionError e) {
			test.fail("Max Length Input Fuel Type Details Not Created Successfully -: '" +actualMSG + "' Popup Message Display");
			throw e;
		}
		alt.accept();
	}
	
	@Test(priority = 8)
	public void CheckGridSearchFunctionality() {
		test = extent.createTest("Verify Grid Table Search Field Working Functionality");
		FT.NavigatePage();
		FT.CHeckGridSearchFunctionality();
		
		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[2]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "Petrol";

		try {
			Assert.assertEquals(Actualtext, Expectedtext, "Grid table Search Field NOt Working");
			test.pass("Enter Search Related Data Should be Load Into Grid Table");
		} catch (AssertionError e) {
			test.fail("Enter Details Related Data Not Load Into Grid Table");
			throw e;
		}
	}
	
	@Test(priority = 9)
	public void CheckBlockFunctionality() {
		test = extent.createTest("Verify Fuel Type Details Block Working Functionality");
		FT.NavigatePage();
		FT.CheckBlockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Fuel Type has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG, "Fuel Type  Details Not Blocked Successfully");
			test.pass("Fuel Type Details Blocked Successfully");
		} catch (AssertionError e) {
			test.fail("Fuel Type Details Not Blocked Successfully");
			throw e;
		}
		alt.accept();
	}
	
	
	@Test(priority = 10)
	public void CheckUnblockFunctionality() {
		test = extent.createTest("Verify Fuel Type Details Unblock Working Functionality");
		FT.NavigatePage();
		FT.Checkunblockfunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Fuel Type has been Activated.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG, "Fuel type Details Not Unblocked Successfully");
			test.pass("Fuel type Details Unblocked Successfully");
		} catch (AssertionError e) {
			test.fail("Fuel type Details Not Unblocked Successfully");
			throw e;
		}
		alt.accept();
	}
	
	
	
	
	@AfterSuite
	public void teardownReport() {
		extent.flush();
		
		try {
			File reportfile = new File(Reportpath);
			if(reportfile.exists()) {
				Desktop.getDesktop().browse(reportfile.toURI());
				System.out.println("Extent Report Open Automatically on defualt browser");
			} else {
				System.out.println("Extent Report Not Open Automatically on defualt browser");
			}
		} catch (Exception e) {
			System.out.println("Extent Report Open Automatically on defualt browser ,Open Manually");
		}
	}

}
