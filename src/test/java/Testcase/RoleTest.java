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
import Pages.RolePage;
import Utils.ScreenshotUtil;

public class RoleTest extends BaseTest {
	
	WebDriverWait wait;
	RolePage RP ;
	ExtentTest test;
	ExtentReports extent;

	ExtentSparkReporter spark;
	String Reportpath = "C:\\Reports\\SCMS\\RolePage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "Role Page");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");

	}
	
	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		RP = new RolePage(driver);
				
	}
	
	@Test(priority = 1)
	public void verifyNavigationPage() {
		test= extent.createTest("Verify Role Page Open Successfully");
		RP.checkNavigationPage();
		
		WebElement PageHeaderName = driver.findElement(By.xpath("//h4[text()='Roles']"));

		try {
			Assert.assertTrue(PageHeaderName.isDisplayed(), "Roles Page Not Open Successfully");
			test.log(Status.PASS, "Roles Page Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Roles Page Not Open Successfully");
			throw e;
		}
	}
	
	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("Verify Roles Page Title Display Correct");
		
		RP.checkNavigationPage();
		
		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Roles | CMSERP";

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
		test = extent.createTest("Verify Create Role Page All Mandatory Field Validaion Message Bind");
		
		RP.checkNavigationPage()
		   .checkValidatorMSG();
		
		WebElement Rolename = driver.findElement(By.id("validatorRoleName"));
		WebElement RolenameMarathi = driver.findElement(By.id("validatorRoleNameMar"));

		try {
			Assert.assertTrue(Rolename.isDisplayed(), "Role Name Field Validation Mesage Not Bind");
			Assert.assertTrue(RolenameMarathi.isDisplayed(), "Role Name Marathi Field Validation Mesage Not Bind");
			test.pass("All Mandatory Field Validation Message Should  Bind Successfully");
		} catch (AssertionError e) {
			test.fail("All Mandatory Field Valiation Message Not Bind");
		}
	}
	
	@Test(priority = 4)
	public void checkCreateNewRoleFunctinality() {
		test = extent.createTest("Verify Create New Role Details Working Functionality");
		RP.checkNavigationPage()
		  .checkCreateNewRoleFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();

		try {
			Assert.assertTrue(
					actualMSG.equals(" Role Details Saved Successfully") || actualMSG.equals("  Role Details Already Exists."),
					"Role Details Success Message Not Display");
			test.log(Status.PASS, "Role Details  Created Successfully And Success Message Display Correct ");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Role Details Created Successfully But Success Message Not Display Correct :" +actualMSG);
			throw e;
		}
	}
	
	
	@Test(priority = 5)
	public void verifyBlockCheckboxEnableCreateRolePage() {
		test = extent.createTest("Verify Block Checkboox Display Create Role Page After Alert Message");
		RP.checkNavigationPage()
		.checkBlockCheckboxEnableCreateRolePage();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		alt.accept();
		
		WebElement blockCB =driver.findElement(By.id("IsDeleted"));
		
		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "CreatePageBlockCB");
		
		try {
			Assert.assertFalse(blockCB.isDisplayed());
			test.log(Status.PASS, "Block Checkbox Should Not be Display On create Role Page");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "After Already Exist Alert Message Accept Block Checkbox Appear Into Create Role Page").addScreenCaptureFromPath(screenshotpath);
			throw e;
		}
		
	}
	
	@Test(priority = 6)
	public void verifyGridSearchFunctionality() {
		test = extent.createTest("Verify Grid Table Search Field Working Functionality");
		RP.checkNavigationPage()
		.checkGridSearchFunctionality();
		
		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[3]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "Office Boy";

		try {
			Assert.assertEquals(Actualtext, Expectedtext, "Enter Search Related Data No Load Into grid Table");
			test.log(Status.PASS, "Enter Search Details Related Data Load Into Grid Table");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Enter Search Related Data No Load Into grid Table");
			throw e;
		}

	}
	
	@Test(priority = 7)
	public void verifyEditPageData() throws InterruptedException {
		test = extent.createTest("Verify Edit Role Page Data Bind Successfully");
		RP.checkNavigationPage()
		  .checkEditPageData();
		
		Thread.sleep(500);
		
		 WebElement rolename = driver.findElement(By.name("RoleName"));
		 
		      String actualValue = rolename.getAttribute("value");
		      
		      String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "EditData");
		      try {
				Assert.assertTrue(actualValue.contains("Office Boy"), "Edit Role Page Details Not Bind Successfully");
				test.log(Status.PASS , "Edit Role Page Role  details Bind Successfully Into Perticular Field");
			} catch (AssertionError e) {
				test.log(Status.FAIL , "Edit Role Page Role  details Not Bind Successfully Into Perticular Field, Empty Field Display").addScreenCaptureFromPath(screenshotpath);
				throw e;
			}
	}
	
	@Test(priority = 8)
	public void verifyRoleBlockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Role Details Block WWorking Functionality");
		RP.checkNavigationPage()
		  .checkRoleBlockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Role has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Role Details  Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Role Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Role Details Block Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[5]"));
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
	public void verifyRoleUnblockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Role Details Unblock Functionality");
		RP.checkNavigationPage()
		  .checkRoleUnblockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Role has been Activated.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Role Details  Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Role Details Unblocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Role Details Unblock Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[5]"));
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
