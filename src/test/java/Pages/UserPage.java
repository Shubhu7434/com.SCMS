package Pages;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BasePage;

public class UserPage extends BasePage<UserPage> {


	WebDriverWait wait;

	public UserPage(WebDriver driver) {
		super(driver);
	}

	// ===Locators=========

	@FindBy(xpath = "//a[text()=' Administration']")
	private WebElement Administrtionmenu;

	@FindBy(xpath = "//a[@href='/UserManagement/index']")
	private WebElement Authenticationmenu;

	@FindBy(xpath = "//a[@href='/User/index']")
	private WebElement Userlink;

	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;

	@FindBy(id = "FirstName")
	private WebElement FirstNameField;

	@FindBy(id = "LastName")
	private WebElement LastNameField;

	@FindBy(id = "UserName")
	private WebElement UserNameField;

	@FindBy(id = "Password")
	private WebElement PasswordField;

	@FindBy(id = "EmailAdd")
	private WebElement EmailField;

	@FindBy(id = "ContactNo")
	private WebElement ContactNoField;

	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[1]")
	private WebElement UserTypeDropdown;

	@FindBy(xpath = "//input[@class='select2-search__field']")
	private WebElement SearchDropdownOption;

	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[2]")
	private WebElement CompanyDropdown;

	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[3]")
	private WebElement PlantDropdown;

	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[4]")
	private WebElement DepartmentDropdown;

	@FindBy(id = "EmpCode")
	private WebElement EmpCodeField;

	@FindBy(id = "ALLOW_MBQ_NOTIFICATION")
	private WebElement AllowMBQCheckbox;

	@FindBy(id = "ALLOW_STOCK_VALUATION_VISIBILITY")
	private WebElement AllowStockValuationCheckbox;

	@FindBy(xpath =  "//input[@type='file']")
	private WebElement UploadUserImage;

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

	@FindBy(xpath = "(//i[@title='Edit'])[1]")
	private WebElement Editicon;

	@FindBy(xpath = "(//i[@title='Block'])[1]")
	private WebElement Blockicon;

	@FindBy(xpath = "(//i[@title='Unblock'])[1]")
	private WebElement Unblockicon;

	@FindBy(xpath = "//button[@title='Excel']")
	private WebElement ExcelExport;

	@FindBy(xpath = "//button[@title='PDF']")
	private WebElement PDFExport;

	// ==Actions=====

	// ===Actions=====
	public UserPage clickAdministrationmenu() {
		click(Administrtionmenu);
		return this;
	}

	public UserPage clickAuthenticationmenu() {
		click(Authenticationmenu);
		return this;
	}

	public UserPage clickUserLink() {
		click(Userlink);
		return this;
	}

	public UserPage clickCreatenewButton() {
		click(Createnewbutton);
		return this;
	}

	public UserPage enterFirstName(String firstname) {
		type(FirstNameField, firstname);
		return this;
	}

	public UserPage enterLastName(String lastname) {
		type(LastNameField, lastname);
		return this;
	}

	public UserPage enterUserName(String username) {
		type(UserNameField, username);
		return this;
	}

	public UserPage enterPassword(String password) {
		type(PasswordField, password);
		return this;
	}

	public UserPage enterEmailID(String emailid) {
		type(EmailField, emailid);
		return this;
	}

	public UserPage enterContactNo(String contactno) {
		type(ContactNoField, contactno);
		return this;
	}

	public UserPage clickUserTypeDropdown() {
		click(UserTypeDropdown);
		return this;
	}

	public UserPage selectUserType(String usertype) {
		typeAndEnter(SearchDropdownOption, usertype);
		return this;
	}

	public UserPage clickCompanyDropdown() {
		click(CompanyDropdown);
		return this;
	}

	public UserPage selectCompany(String company) {
		typeAndEnter(SearchDropdownOption, company);
		return this;
	}

	public UserPage clickPlantDropdown() {
		click(PlantDropdown);
		return this;
	}

	public UserPage selectplant(String plant) {
		typeAndEnter(SearchDropdownOption, plant);
		return this;
	}

	public UserPage clickDepartmentropdown() {
		click(DepartmentDropdown);
		return this;
	}

	public UserPage selectDepartment(String department) {
		typeAndEnter(SearchDropdownOption, department);
		return this;
	}
	
	public UserPage typeDepartment(String department) {
		type(SearchDropdownOption, department);
		return this;
	}

	public UserPage enterEmpCode(String empcode) {
		type(EmpCodeField, empcode);
		return this;
	}

	public UserPage selectMBQCheckbox() {
		click(AllowMBQCheckbox);
		return this;
	}

	public UserPage selectStockCheckbox() {
		click(AllowStockValuationCheckbox);
		return this;
	}

	public UserPage uploadUserImage(String path) {
		try {
	        // Convert to absolute path (VERY IMPORTANT)
	        File file = new File(path);
	        String absolutePath = file.getAbsolutePath();

	        // Upload file
	        UploadUserImage.sendKeys(absolutePath);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return this;
	}

	public UserPage clickSavebutton() {
		click(Savebutton);
		return this;
	}

	public UserPage clickClearButton() {
		click(Clearbutton);
		return this;
	}

	public UserPage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public UserPage clickEditicon() {
		click(Editicon);
		return this;
	}

	public UserPage clickBlock() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Blockicon);
		return this;
	}

	public UserPage clickUnblock() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Unblockicon);
		return this;
	}

	// === Workflow Methods =====

	public UserPage checkNavigation() {
		return clickAdministrationmenu().clickAuthenticationmenu().clickUserLink();
	}

	public UserPage checkvalidatorMSGClear() {
		return clickCreatenewButton().clickSavebutton().enterFirstName("Kartik").enterLastName("Jachak")
				.enterUserName("Kartik").enterPassword("98765432").enterEmailID("kartik@gmail.com")
				.enterContactNo("9874563215").clickUserTypeDropdown().selectUserType("Admin").clickCompanyDropdown()
				.selectCompany("Mauli").clickPlantDropdown().selectplant("Mauli").clickDepartmentropdown()
				.selectDepartment("Store").enterEmpCode("90063");

	}
	
	public UserPage checkDepartmentDropdownMultipleOption() {
		return clickCreatenewButton().clickDepartmentropdown().typeDepartment("Information");
	}
	
	public UserPage checkCreateNewUser() {
		return clickCreatenewButton().enterFirstName("Kartik").enterLastName("Jachak")
				.enterUserName("Kartik").enterPassword("98765432").enterEmailID("kartik@gmail.com")
				.enterContactNo("9874563215").clickUserTypeDropdown().selectUserType("Admin").clickCompanyDropdown()
				.selectCompany("Mauli").clickPlantDropdown().selectplant("Mauli").clickDepartmentropdown()
				.selectDepartment("Store").enterEmpCode("90063").uploadUserImage("src/test/resources/Employee Icon.png").clickSavebutton();
	}
	
	public UserPage checkGridSearchFunctionality() {
		return SearchInGrid("kartik");
	}
	
	public UserPage checkUserBlockFnctionality() {
		SearchInGrid("kartik")
		.clickBlock();
		handelAlert(true);
		return this;
	}
	
	public UserPage checkUserUnblockFunctionality() {
		SearchInGrid("kartik")
		.clickUnblock();
		handelAlert(true);
		return this;
	}

}
