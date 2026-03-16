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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BaseTest;
import Pages.FuelPumpPage;
import Utils.ScreenshotUtil;

public class FuelPumpTest extends BaseTest {

	WebDriverWait wait;
	ExtentReports extent;
	ExtentTest test;
	String Reportpath = "C:\\Reports\\SCMS\\FuelPumpPage.html";
	FuelPumpPage FP;

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "Fuel Pump Master");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");
	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		FP = new FuelPumpPage(driver);
	}

	@Test(priority = 1)
	public void verifyNavigatePage() {
		test = extent.createTest("Verify Fuel Pump Page Open Successfully");

		FP.NavigatePage();

		WebElement PageHeadername = driver.findElement(By.xpath("//h4[text()='Fuel Pump']"));

		String Screenshotpath = ScreenshotUtil.captureScreenshot(driver, "PumpMakePage");

		try {
			Assert.assertTrue(PageHeadername.isDisplayed(), "Fuel Pump Page Not Open Successfully");
			test.log(Status.PASS, "Fuel Pump Page Open Successfully").addScreenCaptureFromPath(Screenshotpath);
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Fuel Pump Page Not Open Successfully");
			throw e;
		}

	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("Verify FuelPump Page Title Display Correct");

		FP.NavigatePage();
		String Actualtitle = getDriver().getTitle();
		String Expectedtitle = "Fuel Pump | CMSERP";

		try {
			Assert.assertEquals(Actualtitle, Expectedtitle, "Page Title Not Display Correct");
			test.log(Status.PASS, "Page Title Should be Disaply Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL,
					"Page Title Not Display Correct Expect :" + Expectedtitle + " But Display :" + Actualtitle);
			throw e;
		}
	}

	@Test(priority = 3)
	public void verifyValidatorMessage() {
		test = extent.createTest("Verify All Mandatory Field Vaidator Message Bind");

		FP.NavigatePage().checkvalidatorMessage();

		WebElement PumpnameMSG = driver.findElement(By.id("validatorPumpName"));
		WebElement MarathiPNMSG = driver.findElement(By.id("validatorPumpNameMarathi"));
		WebElement MarathiPMDMSG = driver.findElement(By.id("validatorPumpMake"));
		WebElement AddressFieldMSG = driver.findElement(By.id("validatorPumpAddress"));

		String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "PumpMAke-ValidatorMSG");

		try {
			Assert.assertTrue(PumpnameMSG.isDisplayed(), "Pump Name  Field Validation Mesage Not Bind");
			Assert.assertTrue(MarathiPNMSG.isDisplayed(), "Pump Name Marathi Field Validation Mesage Not Bind");
			Assert.assertTrue(MarathiPMDMSG.isDisplayed(), "Pump Make Dropdown Validation Mesage Not Bind");
			Assert.assertTrue(AddressFieldMSG.isDisplayed(), "Address  Field Validation Mesage Not Bind");
			test.log(Status.PASS, "All Mandatory Field Validation Message Bind")
					.addScreenCaptureFromPath(screesnshotpath);
		} catch (AssertionError e) {
			test.log(Status.FAIL, "All Mandatory Field Valiation Message Not Bind")
					.addScreenCaptureFromPath(screesnshotpath);
			throw e;
		}
	}

	@Test(priority = 4)
	public void verifyCreateNewFuelPump() {
		test = extent.createTest("verify Create New Fuel Pump Workin Functionality");

		FP.NavigatePage().checkCreateFuelPump();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();

		try {
			Assert.assertTrue(
					actualMSG.equals("Diesel Pump has been Created.") || actualMSG.equals("Diesel Pump already exists."),
					"Fuel Pump  Details Not Created Successfully");
			test.log(Status.PASS, "Fuel Pump Details  Created Successfully And Success Messgae Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "After create Success Message Not Display Correcty : " + actualMSG); 
			throw e;
		}
		alt.accept();

	}
	
	@Test(priority = 5)
	public void verifyGridSearchField() {
		test = extent.createTest("verify Grid Table Search FieldWorking Functionlaity");
		FP.NavigatePage()
		  .checkGridSearch();
		
		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[2]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "Shakari Pump";
		
		try {
			Assert.assertEquals(Actualtext, Expectedtext , "Enter Search Related Data No Load Into grid Table");
			test.log(Status.PASS , "Enter Search Details Related Data Load Into Grid Table");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Enter Search Related Data No Load Into grid Table");
			throw e;
		}
	}
	
	@Test(priority = 6)
	public void verifyBlockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Fuel Pump Details Block Working Functionality");
		
		FP.NavigatePage()
		  .checkBlockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Fuel Pump has been Blocked.";
		
		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG, "Fuel Pump Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS ,"Fuel Pump Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL ,"Fuel Pump Block Success Message Not Display Correct :" +ActualMSG + "Display");
			throw e;
		}
		alt.accept();
		
		Thread.sleep(500);
		
		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[6]"));
		String Actualstatus = status.getText().trim();
		String Expectedstatus = "Blocked";
		
		try {
			Assert.assertEquals(Actualstatus, Expectedstatus , "After block Grid Table Status Not update Correctly");
			test.log(Status.PASS , "After Block Grid Table Status  update Correctly");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "After Block Grid Table Status Not update Correctly");
			throw e;
		}
   }
	
	
	@Test(priority = 7)
	public void verifyUnblockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Fuel Pump details Unblock Working Functionlity");
		
		FP.NavigatePage()
		  .checkunblockFunctionlity();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Fuel Pump has been Activated.";
		
		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG, "Fuel Pump Unblock Success Message Not Display Correct :" +ActualMSG + "Display");
			test.log(Status.PASS ,"Fuel Pump Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL ,"Fuel Pump Block Success Message Not Display Correct :" +ActualMSG + " Display");
			throw e;
		}
		alt.accept();
		
		Thread.sleep(500);
		
		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[6]"));
		String Actualstatus = status.getText().trim();
		String Expectedstatus = "Active";
		
		try {
			Assert.assertEquals(Actualstatus, Expectedstatus , "After block Grid Table Status Not update Correctly");
			test.log(Status.PASS , "After Block Grid Table Status  update Correctly");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "After Block Grid Table Status Not update Correctly");
			throw e;
		}
   }

	@AfterSuite
	public void teardownReport() {
		extent.flush();

		try {
			File reportfile = new File(Reportpath);
			if (reportfile.exists()) {
				Desktop.getDesktop().browse(reportfile.toURI());
				System.out.println("Extent Report Open Succesfully");
			} else {
				System.out.println("Extent Report Not Open Succesfully");
			}
		} catch (Exception e) {
			System.out.println("Extent Report Open Succesfully");
		}
	}

}
