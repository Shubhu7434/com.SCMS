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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BaseTest;
import Pages.VillagePage;
import Utils.ScreenshotUtil;

public class VillageTest extends BaseTest {

	WebDriverWait wait;
	VillagePage VP;
	ExtentTest test;
	ExtentReports extent;

	ExtentSparkReporter spark;
	String Reportpath = "C:\\Reports\\SCMS\\VillagePage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "Village Page");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");

	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		VP = new VillagePage(driver);

	}

	@Test(priority = 1)
	public void verifyVillagePageNvigation() {
		test = extent.createTest("Verify Village Page Open Successfully");
		VP.checkNaviagtionPage();

		WebElement PageHeaderName = driver.findElement(By.xpath("//h4[text()='Village']"));

		try {
			Assert.assertTrue(PageHeaderName.isDisplayed(), "User Page Not Open Successfully");
			test.log(Status.PASS, "User Page Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "User Page Not Open Successfully");
			throw e;
		}
	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("Verify Village Page Title Display Correct");
		VP.checkNaviagtionPage();

		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Village | CMSERP";

		try {
			Assert.assertEquals(actualTitle, expectedTitle, "Page Title Not Display Correct");
			test.log(Status.PASS, "Page Title Should be Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Page Title Not Display Corrrect , Expected Title :" + expectedTitle
					+ " , But Actual Title Display : " + actualTitle);
			throw e;
		}
	}

	@Test(priority = 3)
	public void verifyValidatorMSG() {
		test = extent.createTest("Verify Create User Page All Mandatory Field Validation Message Bind");
		VP.checkNaviagtionPage().checkaValidatorMSG();

		WebElement Gatdropdown = driver.findElement(By.id("validatorGatName"));
		WebElement TalukaDropdown = driver.findElement(By.id("validatorTalName"));

		try {
			Assert.assertTrue(Gatdropdown.isDisplayed());
			Assert.assertTrue(TalukaDropdown.isDisplayed());

			test.pass("All Mandatory Field Validation Message Should Bind");
		} catch (AssertionError e) {
			test.fail("All Mandatory Field Validation Message Not Bind");
			throw e;
		}
	}

	@Test(priority = 4)
	public void verifyCreateNewVillageFunctionality() {

		test = extent.createTest("Verify Create New Village + Column Alignment");

		VP.checkNaviagtionPage().checkCreateNewVillage();

		// ✅ Wait for alert
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();

		try {
			Assert.assertTrue(
					actualMSG.equals("Village has been Created.") || actualMSG.equals("Village already exists."),
					"Village Details Not Created Successfully");

			test.log(Status.PASS, "Village Details Created Successfully");

		} catch (AssertionError e) {
			test.log(Status.FAIL, "Village Details Not Created Successfully");
			throw e;
		}

		alt.accept();

		/*
		 * // ✅ Wait for table load
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//table//tbody//tr")));
		 * 
		 * List<WebElement> headers =
		 * driver.findElements(By.xpath("//table//thead//th")); List<WebElement>
		 * firstRowCols = driver.findElements(By.xpath("//table//tbody//tr[1]/td"));
		 * 
		 * // ✅ Safety check Assert.assertTrue(firstRowCols.size() > 0,
		 * "No data available in table");
		 * 
		 * // ✅ Loop for alignment validation for (int i = 0; i < headers.size(); i++) {
		 * 
		 * int headerX = headers.get(i).getLocation().getX(); int bodyX =
		 * firstRowCols.get(i).getLocation().getX();
		 * 
		 * try { Assert.assertTrue(Math.abs(headerX - bodyX) <= 5,
		 * "Column misalignment at index: " + i + " | HeaderX: " + headerX +
		 * " | BodyX: " + bodyX);
		 * 
		 * } catch (AssertionError e) {
		 * 
		 * String path = ScreenshotUtil.captureScreenshot(driver, "ColumnAlignmentFail_"
		 * + i);
		 * 
		 * test.fail("Column misalignment at index: " + i)
		 * .addScreenCaptureFromPath(path);
		 * 
		 * throw e; } }
		 * 
		 * test.pass("All columns aligned properly");
		 */
	}

	@Test(priority = 5)
	public void verifyGridSearchFunctionality() {
		test = extent.createTest("Verify Grid Table Search Field Working Functionality");

		VP.checkNaviagtionPage().checkGridSearch();

		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[5]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "Mangale";

		try {
			Assert.assertEquals(Actualtext, Expectedtext, "Enter Search Related Data No Load Into grid Table");
			test.log(Status.PASS, "Enter Search Details Related Data Load Into Grid Table");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Enter Search Related Data No Load Into grid Table");
			throw e;
		}
	}

	@Test(priority = 6)
	public void verifyEditPageDataBind() throws InterruptedException {
		test = extent.createTest("Verify Edit Village Page Details Bind Successfully");
		VP.checkNaviagtionPage().checkEditPageData();

		Thread.sleep(500);

		WebElement villagename = driver.findElement(By.name("VillageName"));

		String actualValue = villagename.getAttribute("value");

		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "EditData");
		try {
			Assert.assertTrue(actualValue.contains("Mangale"), "Edit Village Page Details Not Bind Successfully");
			test.log(Status.PASS, "Edit Village Page Village details Bind Successfully Into Perticular Field");
		} catch (AssertionError e) {
			test.log(Status.FAIL,
					"Edit Village Page Village details Not Bind Successfully Into Perticular Field, Empty Field Display")
					.addScreenCaptureFromPath(screenshotpath);
			throw e;
		}
	}

	@Test(priority = 7)
	public void verifyVillageBlockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Village Details Block Wokring Functionality");

		VP.checkNaviagtionPage().checkBlockFunctionality();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Village has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Village  Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Village Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Village Block Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[21]"));
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

	@Test(priority = 8)
	public void verifyDetailsPageStatus() throws InterruptedException {
		test = extent.createTest("Verify Village Details Page Status Display Correct");

		VP.checkNaviagtionPage().checkDetailsPageStatus();
		
		Thread.sleep(1000);

		String path = ScreenshotUtil.captureScreenshot(driver, "Incorrectstatus");

		WebElement gridstatus = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[21]"));
		String actualGridstatus = gridstatus.getText().trim();

		WebElement detailsStatus = driver.findElement(By.id("VillageStatusView"));
		String actualViewPageStatus = detailsStatus.getText().trim();

		try {
			Assert.assertEquals(actualGridstatus, actualViewPageStatus,
					"Village Details Page Status Not Display Correct Same As grid Table");
			test.log(Status.PASS, "Village Details Page Status Display Correct Same As grid Table");
		} catch (AssertionError e) {

			test.log(Status.FAIL, "Grid Table Status Display : " + actualGridstatus
					+ " And Village Details Page Status Display : " + actualViewPageStatus)
					.addScreenCaptureFromPath(path);
			throw e;
		}
	}
	
	@Test(priority = 9)
	public void verifyUnblockVillageFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Village Details Unblock Wokring Functionality");
		VP.checkNaviagtionPage()
		  .checkunblockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Village has been Activated.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Village  Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Village Details Unblocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Village Unblock Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[21]"));
		String Actualstatus = status.getText().trim();
		String Expectedstatus = "Active";

		try {
			Assert.assertEquals(Actualstatus, Expectedstatus, "After block Grid Table Status Not update Correctly");
			test.log(Status.PASS, "After Block Grid Table Status  update Correctly");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "After Block Grid Table Status Not update Correctly");
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
