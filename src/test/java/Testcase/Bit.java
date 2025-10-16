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
import Pages.BitPage;
import Utils.ScreenshotUtil;

public class Bit extends BaseTest {

	BitPage bit;
	ExtentReports extent;
	ExtentTest test;
	WebDriverWait wait;
	String reportpath = "C:\\Reports\\SCMS\\Bitpage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("ProjectName", "SCMS");
		extent.setSystemInfo("Module", "Bit Master");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");
	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		bit = new BitPage(driver);
	}

	@Test(priority = 1)
	public void NavigateBitPage() {
		test = extent.createTest("Verify Bit Page Open Successfully Or Not");
		bit.NavigatePage();

		WebElement pagename = driver.findElement(By.cssSelector(".page-title"));

		String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "BitPage");

		try {
			Assert.assertTrue(pagename.isDisplayed(), "Bit Page Not open Successfully");
			test.pass("Bit page Should be open Successfully").addScreenCaptureFromPath(screesnshotpath);
		} catch (AssertionError e) {
			test.fail("Bit Page Not Open Successfully");
			throw e;
		}
	}

	@Test(priority = 2)
	public void CheckPageTitle() {
		test = extent.createTest("Verify Page Title Correct Or NOt ");
		bit.NavigatePage();

		String Actutaltitle = getDriver().getTitle();
		String Expectedtitle = "Bit-SCMS";

		try {
			Assert.assertEquals(Actutaltitle, Expectedtitle, "Page Title Not Display Correct");
			test.pass("Page Title Should Be Display Correct");
		} catch (AssertionError e) {
			test.fail("Page Title Not Display Correct Expect '" + Expectedtitle + "' But Display '" + Actutaltitle);
			throw e;
		}
	}

	@Test(priority = 3)
	public void CheckvalidationMSG() {
		test = extent.createTest("Verify All Mandatory Fild Validation Message Bind or Not");
		bit.NavigatePage();
		bit.CheckValidatorMSG();

		WebElement GatVaidatorMSG = driver.findElement(By.id("validatorGatName"));
		WebElement BitnameValidator = driver.findElement(By.id("validatorBitName"));
		WebElement MarathiBitValidator = driver.findElement(By.id("validatorBitNameMar"));

		String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "BitPage-ValidatorMSG");

		try {
			Assert.assertTrue(GatVaidatorMSG.isDisplayed(), "Gat Dropdown Validation Mesage Not Bind");
			Assert.assertTrue(BitnameValidator.isDisplayed(), "Bit Name Field Validation Mesage Not Bind");
			Assert.assertTrue(MarathiBitValidator.isDisplayed(), " Marathi Gat Name Field  Validation Mesage Not bind");
			test.pass("All Mandatory Field Validation Message Bind");
		} catch (AssertionError e) {
			test.fail("All Mandatory Field Valiation Message Not Bind").addScreenCaptureFromPath(screesnshotpath);
			throw e;
		}
	}

	@Test(priority = 4)
	public void Checkclearfunctionality() {
		test = extent.createTest("Verify Clear Button Working Functionality");
		bit.NavigatePage();
		bit.Checkclearfunctionality();

		WebElement Bitvalue = driver.findElement(By.id("BitName"));
		String actualvalue = Bitvalue.getAttribute("value");

		try {
			Assert.assertEquals(actualvalue, "", "After Clear Enter All Details Not Clear");
			test.pass("After Clear Select And Enter All Details has Been Clear");
		} catch (AssertionError e) {
			test.fail("After Clear And Select All Details Has been Not Clear");
			throw e;
		}

	}

	@Test(priority = 5)
	public void CheckcreatenewBitDetails() {
		test = extent.createTest("Verify Create New Bit Details Working Functionality");
		bit.NavigatePage();
		bit.CheckCreateNewBitDetails();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();
		alt.accept();

		try {
			Assert.assertTrue(actualMSG.equals("Bit has been Created.") || actualMSG.equals("Bit already exists."),
					"Bit Details Not Created Successfully");
			test.pass("Newly create Bit Details Should be Save Successfully");
		} catch (AssertionError e) {
			test.fail("Bit Details Not Save Successfully");
		}

	}

	@Test(priority = 6)
	public void CheckBlockCBEnable() {
		test = extent.createTest("Verify Block Checkbox Enable Into Create Bit Page Or Not");
		bit.NavigatePage();
		bit.CheckCreateNewBitDetails();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		alt.accept();

		WebElement BlockCB = driver.findElement(By.id("IsDeleted"));
		String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "BlockCheckbox-Enable");

		try {
			Assert.assertFalse(BlockCB.isEnabled(), "Create Bit Page Block Check Has Been Enable");
			test.pass("Create Bit Page Block checkbox Has been Disable");

		} catch (AssertionError e) {
			test.fail("Create Bit Page Block Check Has Been Enable").addScreenCaptureFromPath(screesnshotpath);
			throw e;
		}
	}

	@Test(priority = 7)
	public void CheckEditFunctinality() {
		test = extent.createTest("Verify Bit Details Edit Working Fuctionality");
		bit.NavigatePage();
		bit.CheckEditFunctionality();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();
		String expectedMSG = "Bit has been Updated.";
		alt.accept();

		try {
			Assert.assertEquals(actualMSG, expectedMSG, "Bit Details Not Updated Successfully");
			test.pass("Bit Details Updated Successfully");
		} catch (AssertionError e) {
			test.fail("Bit Details Not Updated Successfully");
			throw e;

		}

	}
	
	@Test(priority = 8)
	public void CheckgridSearchFunctionality() {
		test  = extent.createTest("Verify Grid Table search Field Woirking Fuunctionality");
		bit.NavigatePage();
		bit.CheckGridSearchFunctionality();
		
		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[3]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "Location";

		try {
			Assert.assertEquals(Actualtext, Expectedtext, "Grid table Search Field NOt Working");
			test.pass("Enter Search Related Data Should be Load Into Grid Table");
		} catch (AssertionError e) {
			test.fail("Enter Details Related Data Not Load Into Grid Table");
			throw e;
		}
	}
	
	@Test(priority = 9)
	public void checkViewDetailsOpen() throws InterruptedException {
		test = extent.createTest("Verify View Details popup Page open Or not ");
		bit.NavigatePage();
		bit.CheckViewDetailsPageOpen();
		
		WebElement viewpage = driver.findElement(By.id("modalpopupviewLabel"));
		
		try {
			Thread.sleep(500);
			Assert.assertTrue(viewpage.isDisplayed() , "Bit Details Page Is Not Open ");
			test.pass("After Click On View Icon Into grid Table view Popup page Open");
		} catch (AssertionError e) {
			test.fail("Bit Details Page Is Not Open");
			throw e;
		}
	}
	
	@Test(priority = 10)
	public void Cheeckblockfunctionality() {
		test = extent.createTest("Verify Bit Details block Functionality");
		bit.NavigatePage();
		bit.CheckBlockdetails();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Bit has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG, "Bit Details Not Blocked Successfully");
			test.pass("Bit Details Blocked Successfully");
		} catch (AssertionError e) {
			test.fail("Bit Details Not Blocked Successfully");
			throw e;
		}
		alt.accept();
	}
	
	@Test(priority = 11)
	public void Checkunblockfunctionality() {
		test = extent.createTest("Verify Bit details Unblock Functonality");
		bit.NavigatePage();
		bit.CheckUnblockdetails();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Bit has been Activated .";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG, "Bit Details Not Unblocked Successfully");
			test.pass("Bit Details Unblocked Successfully");
		} catch (AssertionError e) {
			test.fail("Bit Details Not Unblocked Successfully");
			throw e;
		}
		alt.accept();
	}

	@AfterSuite
	public void teardownReport() { 
		extent.flush();

		try {
			File reportFile = new File(reportpath);
			if (reportFile.exists()) {
				Desktop.getDesktop().browse(reportFile.toURI());
				System.out.println("extent report file should be open automatically defualt browser");
			} else {
				System.out.println("extent report file should not be open automatically defualt browser");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("extent report file should not be open automatically defualt browser , Open Manually report File");
		}
	}
	

}
