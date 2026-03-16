package Testcase;

import java.awt.Desktop;
import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BaseTest;
import Pages.RecoveryBasePage;
import Utils.ScreenshotUtil;

public class RecoveryBaseTest extends BaseTest {

	WebDriverWait wait;
	RecoveryBasePage RB;
	ExtentTest test;
	ExtentReports extent;
	ExtentSparkReporter spark;
	String Reportpath = "C:\\Reports\\SCMS\\RecoveryBasePage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "Recovery Base Page");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");

	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		RB = new RecoveryBasePage(driver);
	}

	@Test(priority = 1)
	public void verifyNavigationPageFunctionality() {
		test = extent.createTest("Verify Recovery Base Page Open Successfully");

		RB.NavigatePage();

		WebElement PageHeaderName = driver.findElement(By.xpath("//h4[text()='Recovery Base']"));

		try {
			Assert.assertTrue(PageHeaderName.isDisplayed(), "Recovery Base Page Not Open Successfully");
			test.log(Status.PASS, "Recovery Base Page Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Recovery Base Page Not Open Successfully");
			throw e;
		}
	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("Verify Recovey Base Page Title Display Correct");

		RB.NavigatePage();

		String Actualtitle = getDriver().getTitle();
		String Expectedtitle = "Recovery Base | CMSERP";

		try {
			Assert.assertEquals(Actualtitle, Expectedtitle, "Page Title Not Display Correct");
			test.log(Status.PASS, "Page Title Should be Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Page Title Not Display Corrrect , Expected Title :" + Expectedtitle
					+ " , But Actual Title Display : " + Actualtitle);
			throw e;
		}
	}

	@Test(priority = 3)
	public void verifyValidatorMSG() {
		test = extent.createTest("Verify All Mandatory Field Validtion Message Bind");

		RB.NavigatePage().checkValidatorMSG();

		WebElement plantdropdown = driver.findElement(By.id("validatorPlantCode"));
		WebElement Gatdropdown = driver.findElement(By.id("validatorGatNamee"));
		WebElement Recoverybasefield = driver.findElement(By.id("validatorRecoveryBase"));

		try {
			Assert.assertTrue(plantdropdown.isDisplayed(), "Plant Dropdown Validation Mesage Not Bind");
			Assert.assertTrue(Gatdropdown.isDisplayed(), "Gat Dropdown Validation Mesage Not Bind");
			Assert.assertTrue(Recoverybasefield.isDisplayed(), "Recovery Base Field Validation Mesage Not Bind");
			test.log(Status.PASS, "All Mandatory Field Validation Message Bind");

		} catch (AssertionError e) {
			test.log(Status.FAIL, "All Mandatory Field Valiation Message Not Bind");
			throw e;
		}
	}

	@Test(priority = 4)
	public void verifyRecoveryBaseFieldValidation() {
		test = extent.createTest("Verify Reccovery Base Faield Accept Valid Data As Per Validation");

		RB.NavigatePage().checkRcoverBaseFieldValidation();

		List<WebElement> validator = driver.findElements(By.id("validatorRecoveryBase"));

		String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TonnageValidation");

		try {

			Assert.assertTrue(validator.size() == 0, "Validator message displayed even for valid value");

			test.log(Status.PASS, "Recovery Base field accepted valid value and validator not displayed");

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Recovery Base Field Validator message displayed for valid Revoevery Range value")
					.addScreenCaptureFromPath(screenshotPath);

			throw e;
		}

	}

	@Test(priority = 5)
	public void verifyGateDropdownOptionAvailable() {
		test = extent.createTest("Verify Gat Drodown options Available For Select");
		RB.NavigatePage().checkGatDropdownOptionAvailable();

		List<WebElement> noResult = driver.findElements(By.xpath("//li[contains(text(),'No results found')]"));
		
		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "EmptyGatDropdown");

		try {

			Assert.assertEquals(noResult.size(), 0, "Gat dropdown has no options");

			test.log(Status.PASS, "Gat dropdown options displayed successfully");

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Gat Drodown Options Not Available For Select ,  'No results found' Message Display ").addScreenCaptureFromPath(screenshotpath);
			throw e;
		}
	}

	@AfterSuite
	public void tearDownReport() {
		extent.flush();

		try {
			File reportfile = new File(Reportpath);
			if (reportfile.exists()) {
				Desktop.getDesktop().browse(reportfile.toURI());
				System.out.println("Extent Reprot Open Sutomaticcaly Successfully");
			} else {
				System.out.println("Extent Reprot Not Open Sutomaticcaly Successfully");
			}
		} catch (Exception e) {
			System.out.println("Extent Reprot Not Open Sutomaticcaly Successfully");
		}
	}

}
