package Testcase;

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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BaseTest;
import Pages.DistrictPage;
import Utils.ScreenshotUtil;

public class District extends BaseTest {

	DistrictPage Dp;
	ExtentReports extent;
	ExtentTest test;
	WebDriverWait wait;

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter("C:\\Reports\\SCMS\\DistrictPage.html");
		extent = new ExtentReports();
		extent.attachReporter(report);
	}

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		Dp = new DistrictPage(driver);
	}

	@Test(priority = 1)
	public void NavigatePage() {
		test = extent.createTest("Verify District Page Open Successfully Or NOt");
		Dp.NavigateDistrictPage();

		WebElement pagetitle = driver.findElement(By.cssSelector(".page-title"));

		String screesnshotpath = ScreenshotUtil.captureScreenshot(driver, "OPenPage");

		try {
			Assert.assertTrue(pagetitle.isDisplayed(), "District Page Not Open Successfully");
			test.pass("District Page Should be Open Successfullly").addScreenCaptureFromPath(screesnshotpath);
		} catch (AssertionError e) {
			test.fail("District Page Not open Successfully");
			throw e;
		}
	}

	@Test(priority = 2)
	public void CheckPageTitle() {
		test = extent.createTest("Verify District Page Title Display Correct Or Not");
		Dp.NavigateDistrictPage();

		String ActualTitle = getDriver().getTitle();
		String ExpectedTitle = "District-SCMS";

		try {
			Assert.assertEquals(ActualTitle, ExpectedTitle, "Page Title Not Display Correct");
			test.pass("Page Title Display Correct");
		} catch (AssertionError e) {
			test.fail("Page Title NOt Display Correct Expected '" + ExpectedTitle + "' But Display '" + ActualTitle);
			throw e;
		}
	}

	@Test(priority = 3)
	public void CheckDistrctLableName() {
		test = extent.createTest("Verify District Label Name Display Correct Or Not");
		Dp.NavigateDistrictPage();
		Dp.CheckDistrctLableName();

		WebElement label = driver.findElement(By.xpath("//label[text()='Distrinct']"));

		String Actuallabel = label.getText().trim();
		String Expectedlable = "District";

		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "District Label");

		try {
			Assert.assertEquals(Actuallabel, Expectedlable, "Lable Name Should Not be Display Correct");
			test.pass("Label Name Should Be Display Correct");
		} catch (AssertionError e) {
			test.fail("District Label Name SHould Not be Display Correct :" + Actuallabel)
					.addScreenCaptureFromPath(screenshotpath);
			throw e;
		}
	}

	@Test(priority = 4)
	public void CheckValidationMSG() {
		test = extent.createTest("Verify All Mandatory Field Valiation Message Bind Or Not");
		Dp.NavigateDistrictPage();
		Dp.CheckValidatorMSG();

		WebElement DvalidatorMSG = driver.findElement(By.id("validatorDistName"));
		WebElement DMValidatorMSG = driver.findElement(By.id("validatorDistNameMar"));
		WebElement SValidatorMSG = driver.findElement(By.id("validatorStateName"));

		String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "ValidatorMSG");

		try {
			Assert.assertTrue(DvalidatorMSG.isDisplayed(), "District Field Validation Message Not Display");
			Assert.assertTrue(DMValidatorMSG.isDisplayed(), "District Marathi Field Validation Message Not Display");
			Assert.assertTrue(SValidatorMSG.isDisplayed(), "State Dropdown Validation Message Not Display");
			test.pass("All Mandatory Field Validation Message Should be Bind").addScreenCaptureFromPath(screenshotpath);
		} catch (AssertionError e) {
			test.pass("All Mandatory Field Validation Message Not Bind");
			throw e;
		}
	}

	@Test(priority = 5)
	public void Checkclearfunctionality() {
		test = extent.createTest("Verify Clear Button Working Functionalit");
		Dp.NavigateDistrictPage();
		Dp.CheckClearFunctionalility();

		WebElement Districtfield = driver.findElement(By.xpath("//input[@name='DistName']"));
		String districtdetails = Districtfield.getAttribute("value");
		WebElement DistrictMarathiField = driver.findElement(By.xpath("//input[@name='DistNameMar']"));
		String Dmdetails = DistrictMarathiField.getAttribute("value");
		WebElement Statedropdown = driver.findElement(By.id("select2-StateName-container"));
		String statevalue = Statedropdown.getAttribute("value");
		WebElement DistrictCode = driver.findElement(By.xpath("//input[@name='Code']"));
		String Dcode = DistrictCode.getAttribute("value");
		WebElement Lattitudefield = driver.findElement(By.xpath("//input[@name='Lattitude']"));
		String lattitude = Lattitudefield.getAttribute("value");
		WebElement Longitudefield = driver.findElement(By.xpath("//input[@name='Langitude']"));
		String longitude = Longitudefield.getAttribute("value");

		try {
			Assert.assertEquals(districtdetails, "", "District Field Details Not Clear");
			Assert.assertEquals(Dmdetails, "", "DistrictMarathi  Field Details Not Clear");
			Assert.assertEquals(statevalue, "Select", "State Dropdown Details Not Clear");
			Assert.assertEquals(Dcode, "", "District Code Field Details Not Clear");
			Assert.assertEquals(lattitude, "", "Lattitude Field Details Not Clear");
			Assert.assertEquals(longitude, "", "Longitude Field Details Not Clear");
			test.pass("After Click On Clear Select & Enter All Details Has Been CLear");

		} catch (AssertionError e) {
			test.fail("After Click On Clear Select & Enter All Details Has Been Not CLear");
			throw e;
		}
	}

	@Test(priority = 6)
	public void CheckcreatenewDistrict() {
		test = extent.createTest("Verify Create New District Details Working Functionality");
		Dp.NavigateDistrictPage();
		  String expectedDistrictName = Dp.CheckCreateNewDistrict();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		String ActualMSG = alt.getText().trim();

		try {

			Assert.assertTrue(
					ActualMSG.equals("District has been Created.") || ActualMSG.equals("District already exists."),
					"District Save Successfully Popup Message Not Display Correct");
			test.pass("District Save Successfully Popup Message Display Correct");
		} catch (AssertionError e) {
			test.fail("District Save Successfully Popup Message Not Display Correct");
			throw e;
		}

		alt.accept();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tblData")));
		
		//Get 4th Column Cell From Table 
		List<WebElement> DistrictNames = driver.findElements(By.xpath("//table[@id='tblData']//tr//td[4]"));
		
		boolean districtFound = false;
		for(WebElement cell :DistrictNames) {
			if(cell.getText().trim().equalsIgnoreCase(expectedDistrictName)) {
				districtFound = true ;
				break;
			}
		}
		
		// Assertion
	    try {
	        Assert.assertTrue(districtFound, "Newly created district name not found in the grid table.");
	        test.pass("Newly created district '" + expectedDistrictName + "' displayed successfully in the grid table.");
	    } catch (AssertionError e) {
	        test.fail("District name not displayed correctly in grid table.");
	        throw e;
	    }

	}
	
	@Test(priority = 7)
	public void CheckgridSearchField() {
		test = extent.createTest("Verify Grid Table Search Field Working Functionality");
		Dp.NavigateDistrictPage();
		Dp.CheckgridSearchfield();
		
	WebElement searchvalue = driver.findElement(By.xpath("//table[@id='tblData']//tr//td[4]"));
	String actualvalue = searchvalue.getText().trim();
	String expectedvalue = "Pune";
	
	String screenshotpath = ScreenshotUtil.captureScreenshot(driver, "Search-Field");
	
	try {
		Assert.assertEquals(actualvalue, expectedvalue , "Search Related Data Not Load Into Grid Table");
		test.pass("Search Related Data Should Be Load Into Grid Table").addScreenCaptureFromPath(screenshotpath);
	} catch (AssertionError e) {
		test.fail("Search Related Data Not Load Into Grid Table");
		throw e;
	}
		
	}
	
	@Test(priority = 8)
	public void CheckGridStatussearch() {
		test = extent.createTest("Verify Grid Table Status Column Search Field Working  Functionality");
		Dp.NavigateDistrictPage();
		Dp.CheckStatussearch();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		alt.accept();
		
		
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		  "(//input[@class='search-input'])[7]"))).click();
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		  "(//input[@class='search-input'])[7]"))).sendKeys("Active");
		  
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "(//input[@class='search-input'])[7]"))).click(); 
		  
		 WebElement Status = driver.findElement(By.xpath("//table[@id='tblData']//tr//td[7]"));
		 String actualvalue = Status.getText().trim();
			String expectedvalue = "Active";
			
			try {
				Assert.assertEquals(actualvalue, expectedvalue , "Status Search Field Not Working Correctly");
				test.pass("Status Search Field Working Correctly");
			} catch (AssertionError e) {
				test.fail("Status Search Field Not Working Correctlty");
				throw e;
			}
	}
	
	@Test(priority = 9)
	public void Checkblockfunctionality() throws InterruptedException {
		test = extent.createTest("Verify District Details Block Working Functionality");
		Dp.NavigateDistrictPage();
		Dp.BlockDistrict();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();
		String ExpectedMSG = "District has been Blocked.";
		alt.accept();
		
		try {
			Assert.assertEquals(actualMSG, ExpectedMSG , "District  Details Not Block Successfuuly");
			test.pass("District Details Block Successfully");
		} catch (AssertionError e) {
			test.fail("District Details Not Block Successfuuly");
			throw e;
		}
		
		
	}
	
	@Test(priority = 10)
	public void CheckUnblockfunctionality() throws InterruptedException {
		test = extent.createTest("Verify District Details Unblock Working Functionality");
		Dp.NavigateDistrictPage();
		Dp.UnblockDistrict();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		String actualMSG = alt.getText().trim();
		String ExpectedMSG = "District has been Activated.";
		alt.accept();
		
		try {
			Assert.assertEquals(actualMSG, ExpectedMSG , "District Details Not Unblock Successfuuly");
			test.pass("District  Details Unblock Successfully");
		} catch (AssertionError e) {
			test.fail("District Details Not Unblock Successfuuly");
			throw e;
		}
		
		
	}
	
	

		  
		 
	
	

	@AfterSuite
	public void teardownReport() {
		extent.flush();
	}
}
