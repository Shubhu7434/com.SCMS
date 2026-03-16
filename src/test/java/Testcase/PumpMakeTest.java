package Testcase;

import java.awt.Desktop;
import java.io.File;
import java.time.Duration;
import java.util.List;

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
import Pages.PumpMakePage;
import Utils.ScreenshotUtil;

public class PumpMakeTest extends BaseTest{
	
	
	  PumpMakePage PM;
	  WebDriverWait wait ;
	  ExtentReports extent ;
	   ExtentTest test ;
	   String Reportpath = "C:\\Reports\\SCMS\\PumpMakePage.html";
	   
	   @BeforeSuite
	   public void Setupreport() {
		   ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		    extent = new ExtentReports();
		    extent.attachReporter(report);
		    extent.setSystemInfo("Project Name", "SCMS");
			extent.setSystemInfo("Module", "Pump Make Master");
			extent.setSystemInfo("Tester", "Shubham Mohite");
			extent.setSystemInfo("Browser", "Chrome");
			extent.setSystemInfo("Environment", "QA");
	   }
	   
	   @BeforeMethod
	   public void setup() {
		   Setup();
		   Login();
		   PM = new PumpMakePage(driver);
	   }
	   
	   
	   @Test(priority = 1)
	   public void NavigatePage() {
	     test = extent.createTest("Verify Pump Make Page Open SUccessfully");
		 PM.NavigatePage();
		 
		WebElement PageHeadername = driver.findElement(By.xpath("//h4[text()='Pump Make']"));
		
		String Screenshotpath = ScreenshotUtil.captureScreenshot(driver, "PumpMakePage");
		
		try {
			Assert.assertTrue(PageHeadername.isDisplayed() , "Pump Make Page Not Open Successfully");
			test.log(Status.PASS , "Pump Make Page Open Successfully").addScreenCaptureFromPath(Screenshotpath);
			} catch (AssertionError e) {
			test.log(Status.FAIL , "Pump Make Page Not Open Successfully");
			throw e;
		}
		 
   }
	   
	   
	   @Test(priority = 2)
	   public void verifyPageTitle() {
		   test = extent.createTest("Verify Page Title Display Correct");
		   
		   PM.NavigatePage();
		   
		  String ActualTitle = getDriver().getTitle();
		  String ExpectedTitle = "Pump Make | CMSERP";
		  
		  String Screenshotpath = ScreenshotUtil.captureScreenshot(driver, "PumpMakePage");
			
			try {
				Assert.assertEquals(ActualTitle, ExpectedTitle , "Page Title Not Display Correct");
				test.log(Status.PASS , "Page Title Should be Disaply Correct");
				} catch (AssertionError e) {
				test.log(Status.FAIL , "Page Title Not Display Correct Expect :" + ExpectedTitle  + " But Display :" + ActualTitle).addScreenCaptureFromPath(Screenshotpath);
				throw e;
			}
	   }
	   
	   @Test(priority = 3)
	   public void verifyValidatorMSG() {
		   test = extent.createTest("Verify Create Pump Make Page All Mandatory Filed Validator Message Bound");
		   PM.NavigatePage()
		     .CheckValidatorMSG();
		   
		   WebElement PumpmakeMSG = driver.findElement(By.id("validatorPumpCompany"));
			WebElement MarathiPMMSG = driver.findElement(By.id("validatorPumpCompanyMarathi"));
			
			
			String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "PumpMAke-ValidatorMSG");

			try {
				Assert.assertTrue(PumpmakeMSG.isDisplayed(), "Pump Make  Field Validation Mesage Not Bind");
				Assert.assertTrue(MarathiPMMSG.isDisplayed(), "Pump Make  Marathi Field Validation Mesage Not Bind");
				test.log(Status.PASS ,"All Mandatory Field Validation Message Bind").addScreenCaptureFromPath(screesnshotpath);
			} catch (AssertionError e) {
				test.log(Status.FAIL ,"All Mandatory Field Valiation Message Not Bind").addScreenCaptureFromPath(screesnshotpath);
				throw e;
			}
	   }
	   
	   @Test(priority = 4)
	   public void verifyCreateNewPumpMake() {
		   test =extent.createTest("verify create New Pump Make Details Working Functionality");
		   
		   PM.NavigatePage()
		     .checkCreatenewPumpMake("Cell", "सेल पम्प");
		   

			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alt = driver.switchTo().alert();
			String actualMSG = alt.getText().trim();
			
			try {
				Assert.assertTrue(actualMSG.equals("Pump Make has been Created.") || actualMSG.equals("Pump Make already exists.") , "Pump Make  Details Not Created Successfully");
				test.log(Status.PASS ,"Pump Make Details  Created Successfully");
			} catch (AssertionError e) {
				test.log(Status.FAIL ,"Pump Make Details Not Created Successfully");
				throw e;
			}
			alt.accept();
			
			// 🔹 Assertion for column header alignment
		    List<WebElement> headers = driver.findElements(By.cssSelector("#pumpMakeTable thead th"));
		    for (WebElement header : headers) {
		        String headerText = header.getText().trim();
		        String height = header.getCssValue("height");
		        String lineHeight = header.getCssValue("line-height");
		        String verticalAlign = header.getCssValue("vertical-align");

		        // Example: assert that height is not unusually large
		        Assert.assertTrue(Integer.parseInt(height.replace("px","")) <= 40,
		            "Column header '" + headerText + "' height is misaligned: " + height);

		        // Example: assert vertical alignment is middle
		        Assert.assertEquals(verticalAlign, "middle",
		            "Column header '" + headerText + "' vertical alignment incorrect");
		    }

		    test.log(Status.PASS, "Grid table column headers alignment verified successfully");
	   }
	   
	   @Test(priority = 5)
	   public void verifyGridTableSearch() {
		   test = extent.createTest("Verify Grid Table Search Field Working Functionlaity");
		   
		   PM.NavigatePage()
		      .checkgridTableSearch("Cell");
		   
		   WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[2]"));
			String Actualtext = Searchtext.getText().trim();
			String Expectedtext = "Cell";
			
			try {
				Assert.assertEquals(Actualtext, Expectedtext , "Enter Search Related Data No Load Into grid Table");
				test.log(Status.PASS , "Enter Search Details Related Data Load Into Grid Table");
			} catch (AssertionError e) {
				test.log(Status.FAIL, "Enter Search Related Data No Load Into grid Table");
				throw e;
			}
	   }
	   
	   
	   @Test(priority = 6)
	   public void verifyBlockFunctonality() throws InterruptedException {
		   test = extent.createTest("Verify pump Make details Block Working Functiolaity");
		   
		   PM.NavigatePage()
		     .checkBlockFunctionlaity();
		   
		   wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.alertIsPresent());

			Alert alt = driver.switchTo().alert();
			String ActualMSG = alt.getText().trim();
			String ExpectedMSG = "Pump Make has been Blocked.";
			
			try {
				Assert.assertEquals(ActualMSG, ExpectedMSG, "Pump Make Block Success Message Not Display Correct");
				test.log(Status.PASS ,"Pump make Details Blocked Successfully And Success Message Display Correct");
			} catch (AssertionError e) {
				test.log(Status.FAIL ,"Pump Make Block Success Message Not Display Correct");
				throw e;
			}
			alt.accept();
			
			Thread.sleep(500);
			
			WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[4]"));
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
		   test = extent.createTest("verify Pupmp  Make Details Unblock Working Functionality");
		   PM.NavigatePage()
		   .checkUnblockFunctionality();
		   
		   wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.alertIsPresent());

			Alert alt = driver.switchTo().alert();
			String ActualMSG = alt.getText().trim();
			String ExpectedMSG = "Pump Make has been Activated.";
			
			try {
				Assert.assertEquals(ActualMSG, ExpectedMSG, "Pump Make Unblock Success Message Not Display Correct");
				test.log(Status.PASS ,"Pump Make  Details Unblocked Successfully And Success Message Display Correct");
			} catch (AssertionError e) {
				test.log(Status.FAIL ,"Pump Make Unblock Success Message Not Display Correct");
				throw e;
			}
			alt.accept();
			
			Thread.sleep(500);
			
			WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[4]"));
			String Actualstatus = status.getText().trim();
			String Expectedstatus = "Active";
			
			try {
				Assert.assertEquals(Actualstatus, Expectedstatus , "After Unblock Grid Table Status Not update Correctly");
				test.log(Status.PASS , "After Unblock Grid Table Status  update Correctly");
			} catch (AssertionError e) {
				test.log(Status.FAIL, "After Unblock Grid Table Status Not update Correctly");
				throw e;
			}
		   
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
