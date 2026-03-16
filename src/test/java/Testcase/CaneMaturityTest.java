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
import Pages.CaneMaturityPage;
import Utils.ScreenshotUtil;

public class CaneMaturityTest extends BaseTest {

	WebDriverWait wait;
	CaneMaturityPage CM;
	ExtentTest test;
	ExtentReports extent;
	ExtentSparkReporter spark;
	String Reportpath = "C:\\Reports\\SCMS\\CaneMaturityPage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "Cane Maturity Page");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");

	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		CM = new CaneMaturityPage(driver);
	}

	@Test(priority = 1)
	public void NavigatePage() {
		test = extent.createTest("Verify Cane Materuity Period Page Open Successfully");

		CM.NavigatePage();

		WebElement PageHeaderName = driver.findElement(By.xpath("//h4[text()='Cane Maturity  Period']"));

		try {
			Assert.assertTrue(PageHeaderName.isDisplayed(), "Cane Maturity Period Page Not Open Successfully");
			test.log(Status.PASS, "Cane Maturity Period  Page Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Cane Maturity Period  Page Not Open Successfully");
			throw e;
		}
	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("verify Cane Maturity Period Page Title Display Correct");

		CM.NavigatePage();

		String Actualtitle = getDriver().getTitle();
		String Expectedtitle = "Cane Maturity Period | CMSERP";

		try {
			Assert.assertEquals(Actualtitle, Expectedtitle, "Page Title Not Display Correct");
			test.log(Status.PASS, "Page Title Should be Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Page Title Not Display Corrrect , Expected Title :" + Expectedtitle
					+ " , But Actual Title Display : " + Actualtitle);
			throw e;
		}
	}

	@Test(priority = 4)
	public void verifyTonnageFieldValidation() {
		test = extent.createTest("Verify Tonnage Field Accept valid Data As Per Validation");

		CM.NavigatePage().checkTonnageFieldValidation();

		List<WebElement> validator = driver.findElements(By.id("validatorTonnage"));

		String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TonnageValidation");

		try {

			Assert.assertTrue(validator.size() == 0, "Validator message displayed even for valid value");

			test.log(Status.PASS, "Tonnage field accepted valid value and validator not displayed");

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Validator message displayed for valid tonnage value")
					.addScreenCaptureFromPath(screenshotPath);

			throw e;
		}
	}
	
	@Test(priority = 5)
	public void verifyRecoverBasevalidation() {
		test = extent.createTest("Verify Recovery base Field Accept Valid Data As Per validation");
		
		CM.NavigatePage()
		  .checkRecoveryBaseValidation();
		
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
	
	
	@Test(priority = 6)
	public void verifyValidatorMSG() {
		test = extent.createTest("Verify All Mandatory Field Validation Message Bind");
		
		CM.NavigatePage()
		  .checkValidatorMSG();
		
		WebElement Hangamdropdown = driver.findElement(By.id("validatorHangamName"));
		WebElement TonnageField = driver.findElement(By.id("validatorTonnage"));
		WebElement Varietydropdown = driver.findElement(By.id("validatorVarietyName"));
		WebElement Recoveryfield = driver.findElement(By.id("validatorRecoveryBase"));
		WebElement MatureDaysField = driver.findElement(By.id("validatorMatureDays"));
		
		try {
			Assert.assertTrue(Hangamdropdown.isDisplayed(), "Hanggam Dropdown Validation Mesage Not Bind");
			Assert.assertTrue(TonnageField.isDisplayed(), "Tonnage Field Validation Mesage Not Bind");
			Assert.assertTrue(Varietydropdown.isDisplayed(), "Variety NameDropdown Validation Mesage Not Bind");
			Assert.assertTrue(Recoveryfield.isDisplayed(), "Recovery Base Field Validation Mesage Not Bind");
			Assert.assertTrue(MatureDaysField.isDisplayed(), "Mature Days Field Validation Mesage Not Bind");
			test.log(Status.PASS, "All Mandatory Field Validation Message Bind");
					
		} catch (AssertionError e) {
			test.log(Status.FAIL, "All Mandatory Field Valiation Message Not Bind");
			throw e;
		}
		
	}
	
	
	@Test(priority = 7)
	public void verifyCreateNewCaneMaturity() {
		test = extent.createTest("Verify Create new Cane Maturity Period Details Working Functionalty");
		
		CM.NavigatePage()
		   .checkCreateNewCaneMaturityPeriod();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();

		try {
			Assert.assertTrue(
					actualMSG.equals("Cane Maturity has been Created.") || actualMSG.equals("Cane Maturity already exists."),
					"Cane Maturity  Details Not Created Successfully");
			test.log(Status.PASS, "Cane Maturity Details  Created Successfully And Success Messgae Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Cane Maturity  Details Not Created Successfully"); 
			throw e;
		}
		alt.accept();
	}
	
	@Test(priority = 8)
	public void verifyGridSearchFunctionality() {
		test = extent.createTest("Verify grid Table Search Field Working Functionalty");
		
		CM.NavigatePage()
		  .checkGridSearchFunctionality();
		
		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[3]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "SURU";
		
		try {
			Assert.assertEquals(Actualtext, Expectedtext , "Enter Search Related Data No Load Into grid Table");
			test.log(Status.PASS , "Enter Search Details Related Data Load Into Grid Table");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Enter Search Related Data No Load Into grid Table");
			throw e;
		}
	}
	
	@Test(priority = 8)
	public void verifyBlockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Cane Maturity Period Details Block Working Functionality");
		
		CM.NavigatePage()
		.checkBlockFunctipnality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Cane Maturity has been Blocked.";
		
		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG, "Cane Maturity Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS ,"Cane Maturity Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL ,"Cane Maturity  Block Success Message Not Display Correct :" +ActualMSG + "Display");
			throw e;
		}
		alt.accept();
		
		Thread.sleep(500);
		
		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[8]"));
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
	
	@Test(priority = 9)
	public void verifyUnblockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Cane Matruity Details Unblock Working Functionality");
		
		CM.NavigatePage()
		.checkUnblockfunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Cane Maturity has been Activated.";
		
		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG, "Cane Maturity Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS ,"Cane Maturity Details Unblock Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL ,"Cane Maturity  Unblock Success Message Not Display Correct :" +ActualMSG + "Display");
			throw e;
		}
		alt.accept();
		
		Thread.sleep(500);
		
		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[8]"));
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
	
	@Test(priority = 3)
	public void verifyPageHeaderName() {
		test = extent.createTest("Verify Cane Maturity Period Page Header Name Display Correct");
		
		CM.NavigatePage();
		
		WebElement Headername = driver.findElement(By.xpath("//a[contains(normalize-space(),'Agriculture / CanePlantation/Approvals')]"));
		String Actualheadername = Headername.getText().trim();
		String Expectedheadername = "Agriculture / CanePlantation/Settings";
		
		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "Headername");
		
		try {
			Assert.assertEquals(Actualheadername, Expectedheadername , "Cane Maturity Period Page Header Not Display Correct");
			test.log(Status.PASS, "Cane Maturity period Page Header Name Display Coorect");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Cane Maturity Period Page Header name Not Display Correct , Expected :" + Expectedheadername + " , but Acutal Page Header Name :" +Actualheadername).addScreenCaptureFromPath(screenshotpath);
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
