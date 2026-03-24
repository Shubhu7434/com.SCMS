package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BasePage;

public class RolePage extends BasePage {
	
	
	WebDriverWait wait;
	
	public RolePage (WebDriver driver) {
		super(driver);
	}
	
	//===Locators =====
	
	@FindBy(xpath = "//a[text()=' Administration']")
	private WebElement Administrtionmenu;
	
	@FindBy(xpath = "//a[@href='/UserManagement/index']")
	private WebElement Authenticationmenu;
	
	@FindBy(xpath = "//a[contains(normalize-space(),'Roles')]")
	private WebElement Rolelink;
	
	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;
	
	@FindBy(name =  "RoleName")
	private WebElement RoleNameField;
	
	@FindBy(name =  "RoleNameMar")
	private WebElement RoleNameMarathiField;
	
	@FindBy(id = "btnSave")
	private WebElement Savebutton;
	
	@FindBy(id = "btnClear")
	private WebElement Clearbutton;
	
	@FindBy(id = "btncloseaddupdatepup")
	private WebElement Closebutton;
	
	@FindBy(id = "IsDeleted")
	private WebElement BlockCB;
	
	@FindBy(id = "searchTableList")
	private WebElement Gridsearch;
	
	@FindBy(xpath = "//i[@title='Edit']")
	private WebElement Editicon;
	
	@FindBy(xpath = "(//i[@title='Block'])[1]")
	private WebElement Blockicon;
	
	@FindBy(xpath = "(//i[@title='Unblock'])[1]")
	private WebElement Unblockicon;
	
	@FindBy(xpath = "//button[@title='Excel']")
	private WebElement ExcelExport;
	
	@FindBy(xpath = "//button[@title='PDF']")
	private WebElement PDFExport;
	
	//=== Actions =====
	public RolePage clickAdministrationmenu() {
		click(Administrtionmenu);
		return this;
	}
	
	public RolePage clickAuthenticationmenu() {
		click(Authenticationmenu);
		return this;
	}
	
	public RolePage clickRolelink() {
		click(Rolelink);
		return this ;
	}
	
	public RolePage clickCreateNewButton() {
		click(Createnewbutton);
		return this;
	}
	
	public RolePage enterRoleName(String rolename) {
		type(RoleNameField, rolename);
		return this;
	}
	
	public RolePage enterMarathiRoleName(String marathirolename) {
		type(RoleNameMarathiField, marathirolename);
		return this;
	}
	
	public RolePage clickSavebutton() {
		click(Savebutton);
		return this;
	}
	
	public RolePage clickClearButton() {
		click(Clearbutton);
		return this;
	}
	

	public RolePage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public RolePage clickEditicon() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", Editicon);
		//click(Editicon);
		return this;
	}

	public RolePage clickBlock() {
		click(Blockicon);
		return this;
	}

	public RolePage clickUnblock() {
		click(Unblockicon);
		return this;
	}
	
	//Workflow Methods ==== 
	
	public RolePage checkNavigationPage() {
		return clickAdministrationmenu()
				.clickAuthenticationmenu()
				.clickRolelink();
	}
	
	public RolePage checkValidatorMSG() {
		return clickCreateNewButton()
				.clickSavebutton();
	}
	
	public RolePage checkCreateNewRoleFunctionality() {
		clickCreateNewButton()
		.enterRoleName("Test Role")
		.enterMarathiRoleName("टेस्ट रोल ")
		.clickSavebutton();
		handelAlert(true);
		return this;
		
		
	}
	
	public RolePage checkBlockCheckboxEnableCreateRolePage() {
		clickCreateNewButton()
		.enterRoleName("Test Role")
		.enterMarathiRoleName("टेस्ट रोल ")
		.clickSavebutton();
		handelAlert(true);
		return this;
	}
	
	
	public RolePage checkGridSearchFunctionality() {
		return SearchInGrid("Office");
	}
	
	public RolePage checkEditPageData() {
		return SearchInGrid("Office Boy")
				.clickEditicon();
	}
	
	public RolePage checkRoleBlockFunctionality() {
		 clickBlock();
		 handelAlert(true);
		 return this;
	}
	
	public RolePage checkRoleUnblockFunctionality() {
		clickUnblock();
		handelAlert(true);
		return this;
	}
	
	
	
	
	
	
	

}
