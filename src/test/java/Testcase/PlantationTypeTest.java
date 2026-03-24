package Testcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.BaseTest;
import Pages.PlantationTypePage;
import Utils.ScreenshotUtil;

public class PlantationTypeTest extends BaseTest {
     
	
	WebDriverWait wait ;
	PlantationTypePage PT ;
	ExtentTest test ;
	
	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		PT = new PlantationTypePage(driver);
		PT.checkNavigationPage();
	}
	
	@Test(priority = 1)
	public void verifyNavigationTest() {
		test = extent.createTest("Verify Plantation Types Page Open Successfully");
		
		
		try {
			Assert.assertTrue(PT.getPageHeaderName().isDisplayed() , "Plantation Types Page Open Succesfully");
			test.log(Status.PASS, "Plantation Types Page Should Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Plantation Types Page Not Open Sucessfully");
			throw e;
		}
		
	}
	
	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("Verify Plantation Type Page Title Display Correct");
		
		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Plantation Types | CMSERP";

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
	public void verifyvalidatorMSG() {
		test = extent.createTest("Verify All Mandatory Field Validator Message Bind");
		PT.checkvalidatoerMSG();
		
		try {
			Assert.assertTrue(PT.getCanePlantValidator().isDisplayed());
			Assert.assertTrue(PT.getMarathiCanePlantValiator().isDisplayed());
			test.log(Status.PASS, "Create Cane Plant Page All Mandatory Filed Validator Message Bind");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Create Cane Plant Page All mandatory Field Validation Message Bind");
			throw e;
		}
	}
	
	@Test(priority = 4)
	public void verifyCreateNewPlantationType() {
		test = extent.createTest("Verify Create New plantation Type Details Working Functionality");
		PT.checkCreateNewplantationType();

		String actualMSG = waitForAlertAndGetText();

		try {
			Assert.assertTrue(
					actualMSG.equals("Cane Plant has been Created.") || actualMSG.equals("Cane Plant already exists."),
					"Plantation type Details Not Created Successfully");
			test.log(Status.PASS, "Plantation type Details  Created Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Plantation type  Details Not Created Successfully");
			throw e;
		}
		acceptAlert();
		
	}
	
	@Test(priority = 5)
	public void verifyGridSearchFunctionality() {
		test = extent.createTest("Verify Grid Table Search Fild Working Functionality");
		PT.checkGridSearchFunctionality();

		try {
			Assert.assertTrue(PT.getGridCaneType().contains("2 Feet Sarry"), "Grid table Search Field NOt Working");
			test.pass("Enter Search Related Data Should be Load Into Grid Table");
		} catch (AssertionError e) {
			test.fail("Enter Details Related Data Not Load Into Grid Table");
			throw e;
		}
	}
	
	@Test(priority = 6)
	public void verifyEditPageDataBindCorrectly() throws InterruptedException {
		test = extent.createTest("verify that Cane Plant Data Bind Into Particulrar Field On Edit cane Plant Page ");
		PT.checkEditPageDataBindCorrect();
		
		Thread.sleep(1000);
		
		String path = ScreenshotUtil.captureScreenshot(driver, "EditPageEmpty");
		
		try {
			Assert.assertTrue(PT.getCanePlantValue().contains("2 Feet Sarry"), "Cane Plant Filed Data Not Boind");
			test.log(Status.PASS, "Cane Plant Data Should Bind Into Particulrar Field On Edit cane Plant Page");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Cane Plant Data Not  Bind Into Particulrar Field On Edit cane Plant Page").addScreenCaptureFromPath(path);
			throw e;
		}
	}
	
	@Test(priority = 7)
	public void verifyViewPageData() throws InterruptedException {
		test = extent.createTest("Verify that cane plant data bind correctly into cane plant details page");
		PT.checkViewPageData();
		
		Thread.sleep(1000);
		String path = ScreenshotUtil.captureScreenshot(driver, "ViewPageEmpty");
		
		try {
			Assert.assertEquals(PT.getGridTableID(), PT.getViewPageID() , "View Page Data Not Bind Correctly");
			test.log(Status.PASS, "Cane plant data should bind correctly into cane plant details page");
		} catch (AssertionError e) {
		 test.log(Status.FAIL, "Cane plant data not bind correctly into cane plant details page").addScreenCaptureFromPath(path);
		 throw e;
		}
		
	}
	
	@Test(priority = 8)
	public void verifyBlockFunctinality() throws InterruptedException {
		test = extent.createTest("Verify Cane Plant Details Block Working Functionality");
		PT.checkBlockFunctionality();
		
		String ActualMSG = waitForAlertAndGetText();
		String ExpectedMSG = "Cane Plant has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Cane Plant Details  Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Cane Plant Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Cane Plant Details Block Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		acceptAlert();

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
	public void verifyViewPageStatus() throws InterruptedException {
		test = extent.createTest("Verify Cane plant Details Status Display Correct");
		PT.checkViewPageStatus();
		
		Thread.sleep(1000);
		String path = ScreenshotUtil.captureScreenshot(driver, "ViewPageStatus");
		
		try {
			Assert.assertEquals(PT.getGridTableStatus() , PT.getViewPageStatus() , "View Page Status Not Dispalay Correct");
			test.log(Status.PASS, "Cane plant View Page Status Should Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Cane Plant View Page Status Not Display Correct").addScreenCaptureFromPath(path);
			throw e;
		}
		
	}
	
	@Test(priority = 10)
	public void verifyEditPageBlockCheckboxSelected() throws InterruptedException {
		test = extent.createTest("Verify thet Block Request Details Edit Page Block Checkbox Seleted");
		PT.checkEditPageCheckBoxSelected();
		
		Thread.sleep(1000);
		String path = ScreenshotUtil.captureScreenshot(driver, "EditPageBlockCheckboxUnselect");
		
		try {
			Assert.assertTrue(PT.getBlockChekbox().isSelected() , "Block Request Details Edit Page Block Checkbox Not Seleted");
			test.log(Status.PASS, "Block Request Details Edit Page Block Checkbox Seleted");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Block Request Details Edit Page Block Checkbox Not Seleted").addScreenCaptureFromPath(path);
			throw e;
		}
	}
	
	@Test(priority = 11)
	public void verifyUnblockFunctionality() throws InterruptedException {
		test = extent.createTest("verify cane Plant Details Unblock Working Functionality");
		PT.checkUnblockFunctionality();
		
		String ActualMSG = waitForAlertAndGetText();
		String ExpectedMSG = "Cane Plant has been Activated.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Cane Plant Details Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Cane Plant Details Unblocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Cane Plant Details Unblock Success Message Not Display Correct :" + ActualMSG + "Display");
			throw e;
		}
		acceptAlert();

		Thread.sleep(500);

		WebElement status = driver.findElement(By.xpath("//table[@id='tblData']//tr[1]//td[5]"));
		String Actualstatus = status.getText().trim();
		String Expectedstatus = "Active";

		try {
			Assert.assertEquals(Actualstatus, Expectedstatus, "After Unblock Grid Table Status Not update Correctly");
			test.log(Status.PASS, "After Unblock Grid Table Status  update Correctly");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "After Unblock Grid Table Status Not update Correctly");
			throw e;
		}
		
	}
	
}
