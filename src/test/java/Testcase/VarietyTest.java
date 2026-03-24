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
import Pages.VarietyPage;
import Utils.ScreenshotUtil;

public class VarietyTest extends BaseTest {

	WebDriverWait wait;
	VarietyPage varietypage;
	ExtentTest test;
	ExtentReports extent;
	ExtentSparkReporter spark;
	String Reportpath = "C:\\Reports\\SCMS\\varietyPage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "variety Page");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");

	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		varietypage = new VarietyPage(driver);

	}

	@Test(priority = 1)
	public void verifyNavigation() {
		test = extent.createTest("Verify Varierty Page Open Successfully");
		varietypage.checkNavigationPage();

		try {
			Assert.assertTrue(varietypage.getPageHeaderName().isDisplayed());
			test.log(Status.PASS, "variety Page Should be Open Sucesfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Variety Page NOt Open Successfully");
			throw e;
		}

	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("Verify Page Title Display Correct");
		varietypage.checkNavigationPage();

		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Varierty | CMSERP";

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
	public void verifyvalidatorMSG() {
		test = extent.createTest("Verify Create Variety Page All Madatory Field Vaalidation Message Bind");
		varietypage.checkNavigationPage()
                     .checkValidatorMSG();
		
		try {
			Assert.assertTrue(varietypage.getVarietynameValidatorMSG().isDisplayed());
			Assert.assertTrue(varietypage.getMarathiVarietyNameValidatorMSG().isDisplayed());
			Assert.assertTrue(varietypage.getAcerValidatorMSG().isDisplayed());
			test.log(Status.PASS, "All Mandatory Field Validation Message Bind Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "All Madatory Field Validation Message Not Bind");
			throw  e;
		}

	}
	
	@Test(priority = 4)
	public void verifyCreateNewVariety() {
		test = extent.createTest("Verify Create New vVariety Details  Working Functionality");
		varietypage.checkNavigationPage()
		            .checkCreateNewVariety();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();

		try {
			Assert.assertTrue(
					actualMSG.equals("Variety has been Created.") || actualMSG.equals("Variety already exists."),
					"Variety Details Not Created Successfully");
			test.log(Status.PASS, "Variety Details  Created Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Variety  Details Not Created Successfully");
			throw e;
		}
	}
	
	@Test(priority = 5)
	public void verifyGridSearchFunctonality() {
		test = extent.createTest("Verify Grid Table Search Field Working Functionality");
		varietypage.checkNavigationPage()
		.checkGridSearchFunctionality();
		
		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[3]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "2001";

		try {
			Assert.assertEquals(Actualtext, Expectedtext, "Grid table Search Field NOt Working");
			test.pass("Enter Search Related Data Should be Load Into Grid Table");
		} catch (AssertionError e) {
			test.fail("Enter Details Related Data Not Load Into Grid Table");
			throw e;
		}
		
	}
	
	@Test(priority = 6)
	public void verifyEditPageData() throws InterruptedException {
		test = extent.createTest("Verify Edit Vaariety Page Data Display Correct");
		varietypage.checkNavigationPage()
		.checkEditPageData();
		
		Thread.sleep(500);
		
		 WebElement varietyname = driver.findElement(By.name("VarietyName"));
		 
		      String actualValue = varietyname.getAttribute("value");
		      
		      String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "EditData");
		      try {
				Assert.assertTrue(actualValue.contains("2001"), "Edit Variety Page Details Not Bind Successfully");
				test.log(Status.PASS , "Edit Variety Page Variety details Bind Successfully Into Perticular Field");
			} catch (AssertionError e) {
				test.log(Status.FAIL , "Edit Variety Page Variety  details Not Bind Successfully Into Perticular Field, Empty Field Display").addScreenCaptureFromPath(screenshotpath);
				throw e;
			}
	}
	
	
	@Test(priority = 7)
	public void verifyDetailsPageData() {
		test = extent.createTest("Verify Variety Details Page All Data Display Correct");
		varietypage.checkNavigationPage()
		       .checkDetailsPageData();
		
		 WebElement gridvalue = driver.findElement(By.xpath("//table[@id='tblData']//td[2]"));
		 String actuaGridlValue = gridvalue.getText().trim();
		 
		 WebElement viewpagevalue = driver.findElement(By.id("VarietyCodeView"));
		  String actualViewPageValue = viewpagevalue.getText().trim();
		  
		  String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "EditData");
		  
		  try {
			Assert.assertEquals(actuaGridlValue, actualViewPageValue , "Variety Details Page Data Not Bind Correctly");
			test.log(Status.PASS, "Variety Details Page All Data Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Variety Details Page Data Not Bind Correctly").addScreenCaptureFromPath(screenshotpath);
			throw e;
		}
		          
	} 
	
	@Test(priority = 8)
	public void verifyBlockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Variety Details Block Functionality");
		
		varietypage.checkNavigationPage()
		          .checkBlockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Variety has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Variety Details  Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Variety Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Variety Details Block Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[6]"));
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
		test = extent.createTest("Verify VAriety details Unblock Working Functionality");
		
		varietypage.checkNavigationPage()
		          .checkUnblockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Variety has been Activated.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Variety Details  Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Variety Details Unblocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Variety Details Unblock Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[6]"));
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
