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
import Pages.VillageRoutePage;
import Utils.ScreenshotUtil;

public class VillageRouteTest extends BaseTest {

	WebDriverWait wait;
	VillageRoutePage VR;
	ExtentTest test;
	ExtentReports extent;

	ExtentSparkReporter spark;
	String Reportpath = "C:\\Reports\\SCMS\\VillageRoutePage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "Village Route  Page");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");

	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		VR = new VillageRoutePage(driver);

	}

	@Test()
	public void verifyNavigationPage() {
		test = extent.createTest("Verify Village Route Page Open Successfully");

		VR.checkNavigationPage();

		try {
			Assert.assertTrue(VR.getPageHeaderName().isDisplayed(), "Village Route Page Not Open Successfully");
			test.log(Status.PASS, "Village Route Page Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Village Route Page Not Open Successfully");
			throw e;
		}

	}

	@Test(enabled = false)
	public void verifyPageTitle() {
		test = extent.createTest("Veerify Village Route Page Title Display Correct");

		VR.checkNavigationPage();

		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Village Route | CMSERP";

		try {
			Assert.assertEquals(actualTitle, expectedTitle, "Page Title Not Display Correct");
			test.log(Status.PASS, "Page Title Should be Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Page Title Not Display Corrrect , Expected Title :" + expectedTitle
					+ " , But Actual Title Display : " + actualTitle);
			throw e;
		}

	}

	@Test(enabled = false)
	public void verifyValidatorMSG() {
		test = extent.createTest("Verify Create Village Route Page All Mandatory Field Validation Message Bind");

		VR.checkNavigationPage().chekckValidatorMSG();

		try {
			Assert.assertTrue(VR.getPlantValidatorMSG().isDisplayed());
			Assert.assertTrue(VR.getVillageRouteValidatorMSG().isDisplayed());

			test.pass("All Mandatory Field Validation Message Should Bind");
		} catch (AssertionError e) {
			test.fail("All Mandatory Field Validation Message Not Bind");
			throw e;
		}
	}

	@Test(enabled = false)
	public void verifyGatDropdownOption() {
		test = extent.createTest("Verify Gat Dropdown Data Avaiable For Selection");

		VR.checkNavigationPage().checkGatDropdownData();

		List<WebElement> noResult = driver.findElements(By.xpath("//li[contains(text(),'No results found')]"));

		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "EmptyGatDropdown");

		try {

			Assert.assertEquals(noResult.size(), 0, "Gat dropdown has no options");

			test.log(Status.PASS, "Gat dropdown options displayed successfully");

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Gat Drodown Options Not Available For Select ,  'No results found' Message Display ")
					.addScreenCaptureFromPath(screenshotpath);
			throw e;
		}

	}
	
	@Test(enabled = false)
	public void verifyGridSearchFunctionality() {
		test = extent.createTest("Verify Grid Table Search Filrd Working Functionality");
		VR.checkNavigationPage()
		  .checkGridSearch();
		
		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[4]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "LOHARA";

		try {
			Assert.assertEquals(Actualtext, Expectedtext, "Enter Search Related Data No Load Into grid Table");
			test.log(Status.PASS, "Enter Search Details Related Data Load Into Grid Table");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Enter Search Related Data No Load Into grid Table");
			throw e;
		}
	}
	
	@Test(enabled = false)
	public void verifyBlockRouteDetailsFuctionality() throws InterruptedException {
		test = extent.createTest("Verify Village Route Detals Block Working Functionality");
		
		VR.checkNavigationPage()
		  .checkBlockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Village Route has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Village Route Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Village Route Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Village Route Block Success Message Not Display Correct :" + ActualMSG + "Display");
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
	
	@Test(enabled = false)
	public void verifyUnblockRouteFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Vehicle Route Dtails Unblock Working Functionality");
		
		VR.checkNavigationPage()
		.checkUnblockFunctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "Village Route has been Activated.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Village Route Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Village Route Details Unblocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Village Route Unblock Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[13]"));
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
