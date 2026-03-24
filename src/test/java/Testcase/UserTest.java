package Testcase;

import java.awt.Desktop;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import Pages.UserPage;
import Utils.ScreenshotUtil;

public class UserTest extends BaseTest {

	WebDriverWait wait;
	UserPage UP;
	ExtentTest test;
	ExtentReports extent;

	ExtentSparkReporter spark;
	String Reportpath = "C:\\Reports\\SCMS\\UserPage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(Reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "SCMS");
		extent.setSystemInfo("Module", "User Page");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");

	}

	@BeforeMethod
	public void setup() {

		Setup();
		Login();
		UP = new UserPage(driver);

	}

	@Test(priority = 1)
	public void verifyNavigationPage() {
		test = extent.createTest("Verify User Master Page Open Successfully");

		UP.checkNavigation();

		WebElement PageHeaderName = driver.findElement(By.xpath("//h4[text()='User']"));

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
		test = extent.createTest("Verify User Page Title Display Correct");
		UP.checkNavigation();

		String actualTitle = getDriver().getTitle();
		String expectedTitle = "User | CMSERP";

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
	public void verifyPageHeaderName() {
		test = extent.createTest("Verify User Page Header Name Display Correct");

		UP.checkNavigation();

		WebElement Headername = driver
				.findElement(By.xpath("//a[contains(normalize-space(),'Administration / UserManagement/Master')]"));
		String actuualHeaderName = Headername.getText().trim();
		String expectedHeaderName = "Administration / Authentication/Master";

		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "Headername");

		try {
			Assert.assertEquals(actuualHeaderName, expectedHeaderName, "User Page Header Not Display Correct");
			test.log(Status.PASS, "Userd Page Header Name Display Coorect");
		} catch (AssertionError e) {
			test.log(Status.FAIL,
					"User Page Header name Not Display Correct , Expected :" + expectedHeaderName
							+ " , but Acutal Page Header Name :" + actuualHeaderName)
					.addScreenCaptureFromPath(screenshotpath);
			throw e;
		}
	}

	@Test(priority = 4)
	public void verifyValidatorMSGClear() {
		test = extent.createTest("Verify After Select Valid Data Validator Message Clear");
		UP.checkNavigation().checkvalidatorMSGClear();

		WebElement Userttypedropdown = driver.findElement(By.id("validatorUserType"));
		WebElement Companydropdown = driver.findElement(By.id("validatorCompanyCode"));
		WebElement Plantdropdown = driver.findElement(By.id("validatorPlant"));
		WebElement Departmentdropdown = driver.findElement(By.id("validatorDepartment"));

		String Screenshotpat = ScreenshotUtil.captureScreenshot(driver, "ClearvalidatorMSg");

		try {
			Assert.assertFalse(Userttypedropdown.isDisplayed());
			Assert.assertFalse(Companydropdown.isDisplayed());
			Assert.assertFalse(Plantdropdown.isDisplayed());
			Assert.assertFalse(Departmentdropdown.isDisplayed());
			test.pass("After Select Valid Data Validator Message Clear");
		} catch (AssertionError e) {
			test.fail("After Select Valid Data Dropdown Validator Message Not Clear Still Appear")
					.addScreenCaptureFromPath(Screenshotpat);
			throw e;
		}

	}

	@Test(priority = 5)
	public void verifyDepartmentDropdownMultipleOption() {
		test = extent.createTest(
				"Verify that after searching for a particular department in the department dropdown, duplicate departments are displayed");

		UP.checkNavigation().checkDepartmentDropdownMultipleOption();
		
		String Screenshotpat = ScreenshotUtil.captureScreenshot(driver, "Duplicatedropdownoption");
		
		List<WebElement> results = driver.findElements(
			    By.xpath("//li[contains(@class,'select2-results__option')]")
			);
		
		List<String> resultText = new ArrayList<>();

		for (WebElement ele : results) {
		    String text = ele.getText().trim();
		    
		 // Ignore empty or loading text
		    if (!text.isEmpty() && !text.equalsIgnoreCase("Searching...")) {
		        resultText.add(text);
		}
	}
		
		Set<String> unique = new HashSet<>(resultText);
        try {
        	Assert.assertEquals(resultText.size(), unique.size(),
    		        "Duplicate values found AFTER SEARCH in Department dropdown!");
        	test.log(Status.PASS, "Duplicate values not found AFTER SEARCH in Department dropdown");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Duplicate values found AFTER SEARCH in Department dropdown").addScreenCaptureFromPath(Screenshotpat);
			throw e;
		}
		

	}
	
	@Test(priority = 6)
	public void verifyCreateNewUserFunctionality() {
		test = extent.createTest("Verify Create New User Details Working Functionality");
		UP.checkNavigation()
		   .checkCreateNewUser();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();

		try {
			Assert.assertTrue(
					actualMSG.equals("User (Kartik Jachak) has been created successfully.") || actualMSG.equals("User Name (Kartik) already exists."),
					"User Details Not Created Successfully");
			test.log(Status.PASS, "User Details  Created Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "User  Details Not Created Successfully");
			throw e;
		}
		//alt.accept();
	}
	
	@Test(priority = 7)
	public void verifyGridSearchFunctionality() {
		test = extent.createTest("Verify User Page Grid Table Search Field Working Functionality");
		UP.checkNavigation()
		  .checkGridSearchFunctionality();
		  
		  WebElement Searchtext = driver.findElement(By.xpath("//table[@id='tblData']//td[4]"));
			String Actualtext = Searchtext.getText().trim();
			String Expectedtext = "Kartik";

			try {
				Assert.assertEquals(Actualtext, Expectedtext, "Enter Search Related Data No Load Into grid Table");
				test.log(Status.PASS, "Enter Search Details Related Data Load Into Grid Table");
			} catch (AssertionError e) {
				test.log(Status.FAIL, "Enter Search Related Data No Load Into grid Table");
				throw e;
			}
		
	}
	
	@Test(priority = 8)
	public void verifyUserBlockFunctonality() throws InterruptedException {
		test = extent.createTest("Verify User Details Block Functionality");
		UP.checkNavigation()
		  .checkUserBlockFnctionality();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "User (Kartik Jachak) has been blocked successfully.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"User  Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "User  Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "User  Block Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[17]"));
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
	public void verifyUserUnblockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify User Details Unblock Working Functionality");
		UP.checkNavigation()
		  .checkUserUnblockFunctionality();
	
		
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();
		String ExpectedMSG = "User (Kartik Jachak) has been unblocked successfully.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"User  Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "User  Details Unblocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "User  Unblock Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		alt.accept();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[17]"));
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
