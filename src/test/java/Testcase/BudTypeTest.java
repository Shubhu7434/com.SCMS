package Testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.BaseTest;
import Pages.BudTypePage;

public class BudTypeTest extends BaseTest {
	
	BudTypePage BT;
	ExtentTest test ;
	
	@BeforeMethod
	public void setup() {
		Setup();
		Login();
		BT = new BudTypePage(driver);
		BT.checkNavigationBudTypePage();
	}
	
	@Test(enabled = false)
	public void verifyNavigationBudTypePage() {
		test = extent.createTest("Verify Bud Type Page Open Successfully");
		
		try {
			Assert.assertTrue(BT.getPageHeaderName().isDisplayed(),"Bud Type Page NOt Open Successfully");
			test.log(Status.PASS, "Bud Type Page Open Successfully");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Bud Type Page Not Open Successfully");
			throw e;
		}
	}
	
	@Test(enabled = false)
	public void verifyPageTitle() {
		test = extent.createTest("Verify Bud Type Page Title Display Correct");
		
		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Bud Type | CMSERP";

		try {
			Assert.assertEquals(actualTitle, expectedTitle, "Page Title Not Display Correct");
			test.log(Status.PASS, "Page Title Should be Display Correct");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Page Title Not Display Corrrect , Expected Title :" + expectedTitle
					+ " , But Actual Title Display : " + actualTitle);
			throw e;
		}
	}
	
	@Test()
	public void verifyValidatorMSGBind() {
		test = extent.createTest("Verify Crate Bud Type Page Validator Message Bind For All Mandatory Field");
		BT.checkValidatorMSG();
		
		try {
			Assert.assertTrue(BT.getBudTypeValidator().isDisplayed() , "Bud Type Field Validator Message NOt Bind");
			Assert.assertTrue(BT.getMarathiBudTypeValidator().isDisplayed() , "Bud Type Marathi Field Validator Message NOt Bind");
			test.log(Status.PASS, "All Mandatory Field Validator Message Bind");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "All Mandatory Field Validator Message Not Bind");
			throw e;
			
			
			
		}
	}

}
