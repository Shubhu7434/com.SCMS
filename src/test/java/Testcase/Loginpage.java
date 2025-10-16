package Testcase;

import java.time.Duration;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BaseTest;
import Pages.LoginPage;
import Utils.ScreenshotUtil;

public class Loginpage extends BaseTest {

	LoginPage LP;
	WebDriver driver;
	WebDriverWait wait;

	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter("C:\\Reports\\SCMS\\LoginPage.html");
		extent = new ExtentReports();
		extent.attachReporter(report);
	}

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://192.168.18.33:8019/");
		LP = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void VerifyValidLogin() {
		test = extent.createTest("Verify Login With Valid creadentails");
		LP.Login("rcplb", "Rcpl@cmserp#1");

		WebElement Userlogin = driver.findElement(By.id("page-header-user-dropdown"));

		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "ValidLogin");

		try {
			Assert.assertTrue(Userlogin.isDisplayed(), " User Not Successfully Logedin");
			test.pass("User SHould Be Successfully Login").addScreenCaptureFromPath(screenshotpath);
		} catch (AssertionError e) {
			test.fail("User NOt Successfuly Loggedin ");
			throw e;
		}

	}

	@Test(priority = 2)
	public void VerifyLoginWithEmptyUsername() {
		test = extent.createTest("Verify Login With Empty Username Working Functonality");
		LP.Login(" ", "Rcpl@cmserp#1");

		WebElement ValidatorMSg = driver.findElement(By.id("validatorUsername"));

		String Screenshotpath = ScreenshotUtil.captureScreenshot(driver, "EmptyUsername");

		try {
			Assert.assertTrue(ValidatorMSg.isDisplayed(), "Username Field Validation Message Not Display");
			Reporter.log("User Not Able To Login With Empty Username", true);
			test.pass("At The Time Login With Empty Username , Username Field Validation Message Display")
					.addScreenCaptureFromPath(Screenshotpath);
		} catch (AssertionError e) {
			test.fail("At The Time Login With Empty Username , Username Field Validation Message Not Display");
			throw e;
		}
	}

	@Test(priority = 3)
	public void VerifyLoginWithEMptyPassword() {
		test = extent.createTest("Verify Login With Empty Password Working Functionality");
		LP.Login("rcplb", "");

		WebElement ValidatorMSg = driver.findElement(By.id("validatorPassword"));

		String Screenshotpath = ScreenshotUtil.captureScreenshot(driver, "EmptyPassword");

		try {
			Assert.assertTrue(ValidatorMSg.isDisplayed(), "Password Field Validation Message Not Display");
			Reporter.log("User Not Able To Login With Empty Password", true);
			test.pass("At The Time Login With Empty PAssword , Password Field Validation Message Display")
					.addScreenCaptureFromPath(Screenshotpath);
		} catch (AssertionError e) {
			test.fail("At The Time Login With Empty Password , Password Field Validation Message Not Display");
			throw e;
		}

	}

	@Test(priority = 4)
	public void VerifyLoginWithInvalidUsername() {
		test = extent.createTest("Verify Login WIth Invalid Username & Valid Password");
		LP.Login("rcplb232", "Rcpl@cmserp#1");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alt = driver.switchTo().alert();
		String ActualMSg = alt.getText().trim();
		String ExpectedMSG = "Invalid  Username";
		
		try {
			Assert.assertEquals(ActualMSg, ExpectedMSG , "Validation Popup Message Not Display Correct");
			test.pass("Validation Popoup Message  Display Correct");
		} catch (AssertionError e) {
			test.fail("Validation Popup Message Not Display Correct");
			throw e;
		}
		    
		     
	}
	
	@Test(priority = 5)
	public void VerifyLoginWithInvalidUsernameAndPassword() {
		test = extent.createTest("Verify Login With Invalid Username And Password");
		LP.Login("rcp", "ewrerewrewr534534");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alt = driver.switchTo().alert();
		String ActualMSg = alt.getText().trim();
		String ExpectedMSG = "Invalid Username And Password";
		
		try {
			Assert.assertEquals(ActualMSg, ExpectedMSG , "Validation Popup Message Not Display Correct");
			test.pass("Validation Popoup Message  Display Correct");
		} catch (AssertionError e) {
			test.fail("Validation Popup Message Not Display Correct");
			throw e;
		}
	}
	
	@Test(priority = 6)
	public void VerifyLoginWithInvalidPaswword() {
		test = extent.createTest("Verify Logon With Valid Username And Invalid Password Working Functionaity");
		LP.Login("rcplb", "ewrerewrewr534534");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alt = driver.switchTo().alert();
		String ActualMSg = alt.getText().trim();
		String ExpectedMSG = "Invalid  Password";
		
		try {
			Assert.assertEquals(ActualMSg, ExpectedMSG , "Validation Popup Message Not Display Correct");
			test.pass("Validation Popoup Message  Display Correct");
		} catch (AssertionError e) {
			test.fail("Validation Popup Message Not Display Correct");
			throw e;
		}
	}
	
	
	

	@AfterSuite
	public void teardownReport() {
		extent.flush();
	}

}
