package Testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.BaseTest;
import Pages.SeedTreatmentPage;
import Utils.ScreenshotUtil;

public class SeedtreatmentTest extends BaseTest {

	ExtentTest test;
	SeedTreatmentPage ST;

	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		ST = new SeedTreatmentPage(driver);
		ST.checkNavigatSeedTreatmentPage();

	}

	@Test(priority = 1)
	public void verifyNavigationSeedTreatmentPage() {
		test = extent.createTest("Verify Seed tratement Page OpenSuccessfully");

		try {
			Assert.assertTrue(ST.getPageHeaderName().isDisplayed(), "Seed Treatment Page Open Succesfully");
			test.log(Status.PASS, "Seed Treatment Page Should Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Seed Treatment Page Not Open Sucessfully");
			throw e;
		}
	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("Verify Seed Treatment Page Title Display Correct");

		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Seed Treatment | CMSERP";

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
		test = extent.createTest("Verify that Crete seed treatment page all mandatory field validation message bind ");
		ST.checkValdatorMSG();

		try {
			Assert.assertTrue(ST.getTreatmentValidatorMSG().isDisplayed(),
					"Seed Tratement Field validatoer Message Not Bind");
			Assert.assertTrue(ST.getMarathiTreatmentValidatorMSG().isDisplayed(),
					"Seed Treatment Marathi Field validator Message NOt Bind");
			test.log(Status.PASS, "Create seed tratement page all mandatory filed validator message bind");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Create seed tratement page all mandatory filed validator message not bind");
		}
	}

	@Test(priority = 4)
	public void verifyCreateNewTreatmentFunctionality() {
		test = extent.createTest("Verify Create New Seed Treatment Working Functionality");
		ST.checkCreateNewTreatmentFunctionality();

		String actualMSG = waitForAlertAndGetText();

		try {
			Assert.assertTrue(
					actualMSG.equals("Seed Treatment has been Created.")
							|| actualMSG.equals("Seed Treatment already exists."),
					"Seed Treatment Details Not Created Successfully");
			test.log(Status.PASS, "Seed Treatment Details  Created Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Seed Treatment  Details Not Created Successfully");
			throw e;
		}
		acceptAlert();

	}

	@Test(priority = 5)
	public void verifyGridSearchFunctionality() {
		test = extent.createTest("Verify Grid Table Seach Field Working Functionality");
		ST.checkGridSearch();

		try {
			Assert.assertTrue(ST.getGridSeedtreatment().contains("Biological"), "Grid table Search Field NOt Working");
			test.pass("Enter Search Related Data Should be Load Into Grid Table");
		} catch (AssertionError e) {
			test.fail("Enter Details Related Data Not Load Into Grid Table");
			throw e;
		}
	}

	@Test(priority = 6)
	public void verifyEditPageDataBind() throws InterruptedException {
		test = extent.createTest("Verify that Seed Treatement Data Should Biond Into perticular Filed On Edit Page");
		ST.checkEditPageDataBind();

		Thread.sleep(1000);
		String path = ScreenshotUtil.captureScreenshot(driver, "EditPageEmpty");

		try {
			Assert.assertTrue(ST.getEditPageSeedValue().contains("Biological"), "Seed Treatment Filed Data Not Boind");
			test.log(Status.PASS, "Seed Treatment Data Should Bind Into Particulrar Field On Edit cane Plant Page");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Seed Treatment Data Not  Bind Into Particulrar Field On Edit cane Plant Page")
					.addScreenCaptureFromPath(path);
			throw e;
		}
	}
	
	@Test(priority = 7)
	public void verifyViewPageData() throws InterruptedException {
		test = extent.createTest("Verify that Seed Treatment Details Page All Data Bind Correctly");
		ST.checkViewPageDataBind();
		
		Thread.sleep(1000);
		String path = ScreenshotUtil.captureScreenshot(driver, "ViewPageEmpty");
		
		try {
			Assert.assertEquals(ST.getGridTableID(), ST.getViewPageID() , "View Page Data Not Bind Correctly");
			test.log(Status.PASS, "Seed Treatment data should bind correctly into cane plant details page");
		} catch (AssertionError e) {
		 test.log(Status.FAIL, "Seed Treatment data not bind correctly into cane plant details page").addScreenCaptureFromPath(path);
		 throw e;
		}
		
	}
	
	@Test(priority = 8)
   public void verifyBlockFunctonality() throws InterruptedException {
		test = extent.createTest("Verify that Seed Treatment Details Block Working Functionality");
		ST.checkBlockFunctionality();
		
		String ActualMSG = waitForAlertAndGetText();
		String ExpectedMSG = "Seed Treatment  has been Blocked.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Seed Treatment Details  Block Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Seed Treatment Details Blocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Seed Treatment Details Block Success Message Not Display Correct :" + ActualMSG + "Display");
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
	public void verifyViewPageStats() throws InterruptedException {
		test = extent.createTest("Verify Seed Tratement Details Page Status Display Correct");
		ST.checkViewPageStatus();
		
		Thread.sleep(1000);
		String path = ScreenshotUtil.captureScreenshot(driver, "ViewPageStatus");
		
		try {
			Assert.assertEquals(ST.getGridPageStatus() , ST.getViewPageStatus() , "View Page Status Not Dispalay Correct");
			test.log(Status.PASS, "Seed Tratement View Page Status Should Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Seed Tratement View Page Status Not Display Correct").addScreenCaptureFromPath(path);
			throw e;
		}
	}
	
	@Test(priority = 10)
	public void verifyEditPageBlockCheckboxSelected() throws InterruptedException {
		test = extent.createTest("Verify that  Block Seed Treament Request Edit Page Block Checkbox Selected");
		ST.checkEditPageCheckBoxSelected();
		
		Thread.sleep(1000);
		String path = ScreenshotUtil.captureScreenshot(driver, "EditPageBlockCheckboxUnselect");
		
		try {
			Assert.assertTrue(ST.getEditBlockCheckbox().isSelected() , "Block Request Details Edit Page Block Checkbox Not Seleted");
			test.log(Status.PASS, "Block Request Details Edit Page Block Checkbox Seleted");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Block Request Details Edit Page Block Checkbox Not Seleted").addScreenCaptureFromPath(path);
			throw e;
		}

	}
	
	@Test(priority = 11)
	public void verifySeedUnblockFunctionality() throws InterruptedException {
		test = extent.createTest("Verify Seed Treatment Unblock Working Functionality");
		ST.checkUnblockFunctionality();
		
		String ActualMSG = waitForAlertAndGetText();
		String ExpectedMSG = "Seed Treatment  has been Activated.";

		try {
			Assert.assertEquals(ActualMSG, ExpectedMSG,
					"Seed Treatment Details  Unblock Success Message Not Display Correct :" + ActualMSG);
			test.log(Status.PASS, "Seed Treatment Details Unblocked Successfully And Success Message Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Seed Treatment Details Unblock Success Message Not Display Correct :" + ActualMSG + "Display");
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
