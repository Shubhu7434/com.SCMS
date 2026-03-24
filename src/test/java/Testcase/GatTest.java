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
import Pages.GatPage;

public class GatTest extends BaseTest{

	WebDriverWait wait;
	GatPage gatpage;
	ExtentTest test;
	ExtentReports extent;

	ExtentSparkReporter spark;
	String Reportpath = "C:\\Reports\\SCMS\\GatPage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "Gat Page");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");

	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		gatpage = new GatPage(driver);

	}
	
	@Test(priority = 1)
	public void verifyGatPageNavigation() {
		test = extent.createTest("Verify Gat Page Open Successfully");
		gatpage.checkNavigation();
		
		try {
			Assert.assertTrue(gatpage.getPageHeaderName().isDisplayed());
			test.log(Status.PASS, "Gat Page Should be Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Gat Page Not Open Successfully");
		}
	}
	
	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("Verify Page Title Display Correct");
		gatpage.checkNavigation();
		
		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Gat | CMSERP";

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
		test = extent.createTest("Verify Create Gat Page All Madatory Field Validation Message Bind");
		gatpage.checkNavigation()
		       .checkValidatorMSG();
		
		try {
			Assert.assertEquals(gatpage.getCompanyDropdownValidatorMSG().getText() , "Please Select Company.");
			Assert.assertEquals(gatpage.getGatValidatorMSG().getText() ,"Please Enter Gat.");
			test.log(Status.PASS, "All Mandatory Field Validation Message Bind");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "All mandatory Filed Validation Message Not Display");
			throw e;
		}
	}
	
	@Test(priority = 4)
	public void verifyCreateNewGatDeatils() {
		test = extent.createTest("Verify Create New Gat Details Workin Functionality");
		gatpage.checkNavigation()
		       .checkCreateNewGat();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();
		
		try {
			Assert.assertTrue(actualMSG.equals("Gat has been Created.") || actualMSG.equals("Gat already exists.") , "Gat Details Not Created Successfully");
			test.pass("Gat Details  Created Successfully");
		} catch (AssertionError e) {
			test.fail("Gat Details Not Created Successfully");
			throw e;
		}
		alt.accept();
	}
	
	
	@Test(priority = 5)
	public void verifyGridSarchFunctionality() {
		test = extent.createTest("Verify Grid Table Search Filed Working Functionality");
		
		gatpage.checkNavigation()
		        .checkGridsearchFunctionality();
		
		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[6]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "Karad";

		try {
			Assert.assertEquals(Actualtext, Expectedtext, "Grid table Search Field NOt Working");
			test.pass("Enter Search Related Data Should be Load Into Grid Table");
		} catch (AssertionError e) {
			test.fail("Enter Details Related Data Not Load Into Grid Table");
			throw e;
		}
	}
	
	@Test(priority = 6)
	public void verifyBlockGatFunctonality() throws InterruptedException {
		test= extent.createTest("Verify Gat Details Block Working Functionality");
		gatpage.checkNavigation()
		       .checkBlockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Gat has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Gat  Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Gat Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Gat Block Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[13]"));
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
	
	@Test(priority = 7)
	public void verifyUnblockGatFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Gat Details Unblock Working Functionality");
		gatpage.checkNavigation()
		        .checkUnblockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Gat has been Activated.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Gat  Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Gat Details Unblocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Gat Unblock Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[13]"));
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
