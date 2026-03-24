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
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BaseTest;
import Pages.VillageWiseHarvestingTopLimitPage;
import Utils.ScreenshotUtil;

//@Listeners(Utils.TestListener.class)
public class VillageWiseHarvestTopLimitTest extends BaseTest {

	WebDriverWait wait;
	VillageWiseHarvestingTopLimitPage VHL;

	ExtentTest test;
	ExtentReports extent;
	ExtentSparkReporter spark;
	String Reportpath = "C:\\Reports\\SCMS\\VillageWiseHarvestTopLimitPage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "Village Wise Harvest TOp Limit Page");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");

	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		VHL = new VillageWiseHarvestingTopLimitPage(driver);
	}

	@Test(priority = 1)
	public void verifyNavigationPage() {
		test = extent.createTest("Verify Village Wise Harvest Top Limit Page Open Successfully");

		VHL.checkNavigationPage();

		WebElement PageHeaderName = driver.findElement(By.xpath("//h4[text()='Village Wise Harvesting Top Limit']"));

		try {
			Assert.assertTrue(PageHeaderName.isDisplayed(),
					"Village Wise Harvesting Top Limit Page Not Open Successfully");
			test.log(Status.PASS, "Village Wise Harvesting Top Limit Page Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Village Wise Harvesting Top Limit Page Not Open Successfully");
			throw e;
		}

	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		 test = extent.createTest("Verify Vilage Wise Harvest Top Limit Page Title Display Correct");

		VHL.checkNavigationPage();

		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Vilage Wise Harvest Top Limit | CMSERP";

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
	public void verifyGatDropdowwnDataAvailable() {
		test = extent.createTest("Verify Gat Dropdown Data Avaiable For Selection");
		VHL.checkNavigationPage().checkGatDropdownOption();

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

	@Test(priority = 4)
	public void verifyValidatorMSgAlignment() {
		test = extent.createTest("Verify Gat Dropdown Validation Message Aligned properly with dropdown ");

		VHL.checkNavigationPage().checkGatDropdownValidatorMSgAlignment();

		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "GatDropdownMSgAlignment");

		WebElement dropdown = driver
				.findElement(By.xpath("(//span[@class='select2-selection select2-selection--single'])[1]"));
		WebElement errorMsg = driver.findElement(By.id("validatorGat"));

		int dropdownX = dropdown.getLocation().getX();
		int errorX = errorMsg.getLocation().getX();

		try {
			Assert.assertTrue(Math.abs(dropdownX - errorX) <= 5,
					"Validation message is NOT aligned properly with dropdown");
			test.log(Status.PASS, "Validation message is NOT aligned properly with dropdown");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Validation message is NOT aligned properly with gat dropdown")
					.addScreenCaptureFromPath(screenshotpath);
		}

	}

	@Test(priority = 5)
	public void verifyAppyAllCheckboxselect() {
		test = extent.createTest("Verify Create Harvest Top Limit Page Apply To All Checkbox Select After Click");

		VHL.checkNavigationPage().checkApplyAllcheckboxselect();

		WebElement Applyallcheckbox = driver.findElement(By.id("chkApplyAllVillages"));

		try {
			Assert.assertTrue(Applyallcheckbox.isSelected(), "Apply To All Checkbox Not Select After Click");
			test.log(Status.PASS, "Apply To All checkbox Should Be Selected Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Apply To All Checkbox Not Select After Click");
			throw e;
		}
	}

	@Test(priority = 6)
	public void verifyCreatePageSearchFieldFunctionality() {
		test = extent.createTest("Verify Create Hervest Top Limit Page grid Table Search Field Working Funcionality");

		VHL.checkNavigationPage().checkCreatePageSearchField();

		WebElement duplicateSearch = driver.findElement(By.xpath("//input[@id='dt-search-1']"));
		WebElement gridCheckbox = driver.findElement(By.id("selectAllVillageCheckbox"));

		String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "Duplicatesearchfield");

		SoftAssert soft = new SoftAssert();
		// Assertion 1
		soft.assertFalse(duplicateSearch.isDisplayed(), "After entering details, duplicate search field is displayed");

		if (duplicateSearch.isDisplayed()) {
			test.log(Status.PASS, "Duplicate search field is NOT displayed");
		} else {
			test.log(Status.FAIL, "After entering details, duplicate search field is displayed")
					.addScreenCaptureFromPath(screenshotPath);
		}

		// Assertion 2
		soft.assertFalse(gridCheckbox.isSelected(), "After entering details, checkbox is auto-selected");

		if (!gridCheckbox.isSelected()) {
			test.log(Status.PASS, "Checkbox is NOT auto-selected");
		} else {
			test.log(Status.FAIL, "After entering details, checkbox is auto-selected")
					.addScreenCaptureFromPath(screenshotPath);
		}

		// VERY IMPORTANT
		soft.assertAll();

	}

	@Test(priority = 7)
	public void verifyGridSearchfunctionality() {
		test = extent
				.createTest("Verify Village Wise Harvest Top Limit Page Grid Table Search Field Working Functionality");

		VHL.checkNavigationPage().checkGridSearchFunctionality();

		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[3]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "BIDAR";

		try {
			Assert.assertEquals(Actualtext, Expectedtext, "Enter Search Related Data No Load Into grid Table");
			test.log(Status.PASS, "Enter Search Details Related Data Load Into Grid Table");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Enter Search Related Data No Load Into grid Table");
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
