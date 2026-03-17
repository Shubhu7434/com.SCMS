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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BaseTest;
import Pages.UserTypePage;

//@Listeners(Utils.TestListener.class)
public class UserTypeTest extends BaseTest {

	WebDriverWait wait;
	UserTypePage UT;
	ExtentTest test;
	ExtentReports extent;

	ExtentSparkReporter spark;
	String Reportpath = "C:\\Reports\\SCMS\\UserTypePage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "User Type Page");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");

	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		UT = new UserTypePage(driver);
	}

	@Test(priority = 1)
	public void verifyNavigatePage() {
		test = extent.createTest("Verify User Type Page Open Successfully");

		UT.checkNavigatePage();

		WebElement PageHeaderName = driver.findElement(By.xpath("//h4[text()='User Type']"));

		try {
			Assert.assertTrue(PageHeaderName.isDisplayed(), "User Type Page Not Open Successfully");
			test.log(Status.PASS, "User Type  Page Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "User Type Page Not Open Successfully");
			throw e;
		}
	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("Verify User type Page Title Display Correct");
		UT.checkNavigatePage();

		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Usert Type | CMSERP";

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
		test = extent.createTest("Verify Create User Type Page All Mandatory Field Validation Message Bind");

		UT.checkNavigatePage().checkvalidatorMSG();

		WebElement Userttype = driver.findElement(By.id("validatorUserType"));
		WebElement UsertypeMarathi = driver.findElement(By.id("validatorUserTypeMar"));

		try {
			Assert.assertTrue(Userttype.isDisplayed(), "User type Field Validation Mesage Not Bind");
			Assert.assertTrue(UsertypeMarathi.isDisplayed(), "User Type Marathi Field Validation Mesage Not Bind");
			test.pass("All Mandatory Field Validation Message Bind");
		} catch (AssertionError e) {
			test.fail("All Mandatory Field Valiation Message Not Bind");
		}
	}

	@Test(priority = 4)
	public void verifyCreateNewUserTypeFunctionality() {
		test = extent.createTest("Verify Create New User Type Working Functionality");

		UT.checkNavigatePage().checkCreateNewUserType();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();

		try {
			Assert.assertTrue(
					actualMSG.equals("User Type has been Created.") || actualMSG.equals("User Type already exists."),
					"Pump Make  Details Not Created Successfully");
			test.log(Status.PASS, "User Type Details  Created Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "User Type  Details Not Created Successfully");
			throw e;
		}
		alt.accept();
	}

	@Test(priority = 5)
	public void verifyGridSearchFunctionality() {
		test = extent.createTest("Verify User Type Page Grid Table Serch Field Working Functionality");

		UT.checkNavigatePage().checkGridSearchFunctionality();

		WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[3]"));
		String Actualtext = Searchtext.getText().trim();
		String Expectedtext = "Sales & Executive";

		try {
			Assert.assertEquals(Actualtext, Expectedtext, "Enter Search Related Data No Load Into grid Table");
			test.log(Status.PASS, "Enter Search Details Related Data Load Into Grid Table");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Enter Search Related Data No Load Into grid Table");
			throw e;
		}
	}

	@Test(priority = 6)
	public void verifyBlockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify User Type Details Block Working Functionality");

		UT.checkNavigatePage().checkBlockFunctionality();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "User Type has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"User Type Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "User type Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "User Type Block Success Message Not Display Correct :" + ActualMSG + "Display");
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

	@Test(priority = 7)
	public void verifyUnblockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify User Type Details Unblock Working Functionality");

		UT.checkNavigatePage().checkUnblockFunctionality();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "User Type has been Activated.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"User Type Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "User type Details Unblocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "User Type Unblock Success Message Not Display Correct :" + ActualMSG + "Display");
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
