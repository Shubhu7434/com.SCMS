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
import Pages.TalukaPage;
import Utils.ScreenshotUtil;

public class Taluka extends BaseTest {

	TalukaPage TP;
	ExtentReports extent;
	ExtentTest test;
	WebDriverWait wait;
	String reportPath = "C:\\Reports\\SCMS\\TalukaPage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("ProjectName", "SCMS");
		extent.setSystemInfo("Module", "Taluka");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");
	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		TP = new TalukaPage(driver);
	}

	@Test(priority = 1)
	public void NavigatetalukaPage() {
		test = extent.createTest("Verify Taluka Page Open Successfully Or Not");
		TP.NavigatePage();

		WebElement pagename = driver.findElement(By.cssSelector(".page-title"));

		String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "TalukaPage");

		try {
			Assert.assertTrue(pagename.isDisplayed(), "Taluka Page Not open Successfully");
			test.pass("Taluka page Should be open Successfully").addScreenCaptureFromPath(screesnshotpath);
		} catch (AssertionError e) {
			test.fail("Taluka Page Not Open Successfully");
			throw e;
		}

	}

	@Test(priority = 2)
	public void CheckPageTitle() {
		test = extent.createTest("Verify Taluka Page Title Display Correct Or Not");
		TP.NavigatePage();

		String Actutaltitle = getDriver().getTitle();
		String Expectedtitle = "Taluka-SCMS";

		try {
			Assert.assertEquals(Actutaltitle, Expectedtitle, "Page Title Not Display Correct");
			test.pass("Page Title Should Be Display Correct");
		} catch (AssertionError e) {
			test.fail("Page Title Not Display Correct Expect '" + Expectedtitle + "' But Display '" + Actutaltitle);
			throw e;
		}

	}

	@Test(priority = 3)
	public void CheckValidatorMSG() {
		test = extent.createTest("Verify All Mandatory Field Validation Message Bind Or Not");
		TP.NavigatePage();
		TP.CheckValidationMSG();

		WebElement TalValidatorMSG = driver.findElement(By.id("validatorTalName"));
		WebElement MarathiTalValidatorMSG = driver.findElement(By.id("validatorTalNameMar"));
		WebElement DistrictdropdownvalidatorMSG = driver.findElement(By.id("validatorDistName"));

		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "ValidatorMSG");

		try {
			Assert.assertTrue(TalValidatorMSG.isDisplayed(), "Taluka Field  Validation Message Not Dipslay");
			Assert.assertTrue(MarathiTalValidatorMSG.isDisplayed(),
					"Marathi Taluka Field Validation Message Not Display");
			Assert.assertTrue(DistrictdropdownvalidatorMSG.isDisplayed(),
					"District Dropdown Validation Message Not Dipslay");
			test.pass("All Manadtory Field Validation Message Display").addScreenCaptureFromPath(screenshotpath);
		} catch (AssertionError e) {
			test.fail("All Mandatory Field Validation Message Not Bind");
			throw e;
		}

	}

	@Test(priority = 4)
	public void CheckDistrictDrodpownExpand() throws InterruptedException {
		test = extent.createTest("Verify District Dropdown Expand Correctly All Avaiable Option Display Or Not");
		TP.NavigatePage();
		TP.CheckDistrictDropdownExpand();

		WebElement option = driver.findElement(By.xpath("//li[contains(text(),'LATUR')]"));

		boolean clickable;
		try {
			option.click();
			clickable = true;
		} catch (Exception e) {
			clickable = false;
		}

		String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "DistrictDropdown");

		try {
			Assert.assertTrue(clickable, "Dropdown options are not clickable —  hidden behind Latitude field");
			test.pass("Dropdown Option Shoud be Clickable And Expand Correctly All Option");
		} catch (AssertionError e) {
			test.fail("Dropdown options are not clickable — hidden behind Latitude field")
					.addScreenCaptureFromPath(screenshotPath);
			throw e;
		}

	}

	@Test(priority = 5)
	public void CheckClearFunctionality() {
		test = extent.createTest("Verify Clear Button Working Functionality");
		TP.NavigatePage();
		TP.CheckClearFunctionality();

		WebElement Talukaname = driver.findElement(By.xpath("//input[@name='TalName']"));
		String Talukavalue = Talukaname.getAttribute("value").trim();
		WebElement MTalukaname = driver.findElement(By.xpath("//input[@name='TalNameMar']"));
		String Mtalukavalue = MTalukaname.getAttribute("value").trim();

		try {
			Assert.assertEquals(Talukavalue, "", " After Clear Taluka Name Details Not Clear");
			Assert.assertEquals(Mtalukavalue, "", "After CLear Marathi Taluka Details Noot Clear ");
			test.pass("After Clear All Selected & Enter All Details Should be Clear");
		} catch (AssertionError e) {
			test.fail("After Clear Selected & Enter All Details Not Clear");
			throw e;
		}
	}

	@Test(priority = 6)
	public void CheckEditFunctionality() {
		test = extent.createTest("Verify Edit Taluka Details Working Functionality");
		TP.NavigatePage();
		TP.CheckEditFunctionality();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Taluka has been Updated.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG, "Taluka Details Not Update Successfully");
			test.pass("Taluka Details Updated Successfully");
		} catch (AssertionError e) {
			test.fail("Taluka Details Not Update Successfully , Popup Messag Display : " + ActualMSG);
			throw e;
		}
		alt.accept();
	}

	@Test(priority = 7)
	public void CheckTalukaBlockFunctionality() {
		test = extent.createTest("Verify Taluka Details Block Working Functionality");
		TP.NavigatePage();
		TP.CheckBlockTaluka();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "District has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG, "Taluka Details Not Blocked Successfully");
			test.pass("Taluka Details Blocked Successfully");
		} catch (AssertionError e) {
			test.fail("Taluka Details Not Blocked Successfully , Popup Messag Display : " + ActualMSG);
			throw e;
		}
		alt.accept();

	}

	@Test(priority = 8)
	public void CheckViewPageStatus() throws InterruptedException {
		test = extent.createTest("Verify Into Taluka Details Page After Block Status Update Or NOt");
		TP.NavigatePage();
		TP.CheckViewPageStatus();

		WebElement Status = driver.findElement(By.xpath("//div[@id='tblData_wrapper']//td[8]"));
		String Gridstatus = Status.getText().trim();

		WebElement Status1 = driver.findElement(By.id("TalukaStatusView"));
		String ViewPageStatus = Status1.getText().trim();

		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "ViewPageStatus");

		try {
			Assert.assertEquals(ViewPageStatus, Gridstatus,
					"Into Grid Table & Taluka Details Popup Page Status Display Mismatch");
			test.pass("Into Grid Table And Taluka Details Page All details Display Correct")
					.addScreenCaptureFromPath(screenshotpath);

		} catch (AssertionError e) {
			Thread.sleep(500);
			test.fail("Into grid Table & Taluka Details Page Stats Display Missmatch")
					.addScreenCaptureFromPath(screenshotpath);
			throw e;
		}
	}

	@Test(priority = 9)
	public void CheckGridTableSearchFuenctionality() {
		test = extent.createTest("Verify Grid Table Search Field Working Functionality");
		TP.NavigatePage();
		TP.CheckGridsearch();

		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[4]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "BARAMATI";

		try {
			Assert.assertEquals(Actualtext, Expectedtext, "Grid table Search Field NOt Working");
			test.pass("Enter Search Related Data Should be Load Into Grid Table");
		} catch (AssertionError e) {
			test.fail("Enter Details Related Data Not Load Into Grid Table");
			throw e;
		}
	}

	@AfterSuite
	public void teardownReport() {
		extent.flush();

		try {

			File reportFile = new File(reportPath);
			if (reportFile.exists()) {
				Desktop.getDesktop().browse(reportFile.toURI());
				System.out.println("extent Report Open Automatically in defualt browser");
			} else {
				System.out.println("extent Report not Open Automatically in defualt browser");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("❌ Unable to open Extent Report automatically. Please open manually.");
		}
	}

}
