package Pages;

import javax.swing.text.GapContent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BasePage;

public class GatPage extends BasePage<GatPage> {

	WebDriverWait wait;

	public GatPage(WebDriver driver) {
		super(driver);
	}

	// ===Locators =====

	@FindBy(xpath = " (//a[contains(text(),'Agriculture')])[1]")
	private WebElement Agriculturemenu;

	@FindBy(xpath = "//a[@href='/CanePlantation/index']")
	private WebElement Caneplantationmenu;

	@FindBy(xpath = "//a[@href='/Gat/index']")
	private WebElement Gatlink;

	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;

	@FindBy(id = "select2-CompanyCode-container")
	private WebElement CompanyDropdown;

	@FindBy(css = ".select2-search__field")
	private WebElement SearchDropdownUser;

	@FindBy(id = "select2-PlantCode-container")
	private WebElement PlantDropdown;

	@FindBy(id = "select2-AreaName-container")
	private WebElement AreaDropdown;

	@FindBy(name = "GatName")
	private WebElement GatNameField;

	@FindBy(name = "GatNameMar")
	private WebElement GatNameMarathiField;

	@FindBy(name = "Address")
	private WebElement AddressField;

	@FindBy(name = "AddressMar")
	private WebElement AddressMarathiField;

	@FindBy(name = "Lattitude")
	private WebElement LattitudeField;

	@FindBy(name = "Langitude")
	private WebElement LongtitudeField;

	@FindBy(name = "RefGatCode")
	private WebElement RefGatCodeField;

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

	@FindBy(xpath = "(//i[@title='View'])[1]")
	private WebElement Viewicon;

	@FindBy(xpath = "(//i[@title='Block'])[1]")
	private WebElement Blockicon;

	@FindBy(xpath = "(//i[@title='Unblock'])[1]")
	private WebElement Unblockicon;

	@FindBy(xpath = "//button[@title='Excel']")
	private WebElement ExcelExport;

	@FindBy(xpath = "//button[@title='PDF']")
	private WebElement PDFExport;

	@FindBy(id = "validatorCompanyCode")
	private WebElement CompantDropdownValidator;

	@FindBy(id = "validatorGatName")
	private WebElement GatValidator;

	@FindBy(xpath = "//h4[text()='Gat']")
	private WebElement PageHeaderName;

	// ====Actions=====

	public GatPage clickAgricultureMenu() {
		click(Agriculturemenu);
		return this;
	}

	public GatPage clickCanePlantationMenu() {
		click(Caneplantationmenu);
		return this;
	}

	public GatPage clickGatlink() {
		click(Gatlink);
		return this;
	}

	public GatPage clickCreateNewButton() {
		click(Createnewbutton);
		return this;
	}

	public GatPage clickCompanyDropdown() {
		click(CompanyDropdown);
		return this;
	}

	public GatPage Selectcompany(String company) {
		typeAndEnter(SearchDropdownUser, company);
		return this;
	}

	public GatPage clickPlantDropdown() {
		click(PlantDropdown);
		return this;
	}

	public GatPage selectplant(String plant) {
		typeAndEnter(SearchDropdownUser, plant);
		return this;
	}

	public GatPage clickAreaDropdown() {
		click(AreaDropdown);
		return this;
	}

	public GatPage selectArea(String area) {
		typeAndEnter(SearchDropdownUser, area);
		return this;
	}

	public GatPage enterGatName(String gatname) {
		type(GatNameField, gatname);
		return this;
	}

	public GatPage enterMarathiGatName(String marathigatname) {
		type(GatNameMarathiField, marathigatname);
		return this;
	}

	public GatPage enterAddress(String address) {
		type(AddressField, address);
		return this;
	}

	public GatPage enterMarathiAddress(String marathiaddress) {
		type(AddressMarathiField, marathiaddress);
		return this;
	}

	public GatPage enterLattitude(String latitude) {
		type(LattitudeField, latitude);
		return this;
	}

	public GatPage enterLongtitude(String longtitude) {
		type(LongtitudeField, longtitude);
		return this;
	}

	public GatPage enterRefGatCode(String refcode) {
		type(RefGatCodeField, refcode);
		return this;
	}

	public GatPage clickSavebutton() {
		click(Savebutton);
		return this;
	}

	public GatPage clickClearButton() {
		click(Clearbutton);
		return this;
	}

	public GatPage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public GatPage clickEditicon() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Editicon);
		return this;
	}

	public GatPage clickViewicon() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Viewicon);
		return this;
	}

	public GatPage clickBlock() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Blockicon);
		return this;
	}

	public GatPage clickUnblock() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Unblockicon);
		return this;
	}

	public WebElement getPageHeaderName() {
		return PageHeaderName;
	}

	public WebElement getCompanyDropdownValidatorMSG() {
		return CompantDropdownValidator;
	}

	public WebElement getGatValidatorMSG() {
		return GatValidator;
	}

	// =====WorkFlow Methods =======

	public GatPage checkNavigation() {
		return clickAgricultureMenu().clickCanePlantationMenu().clickGatlink();

	}

	public GatPage checkValidatorMSG() {
		return clickCreateNewButton().clickSavebutton();
	}

	public GatPage checkCreateNewGat() {
		clickCreateNewButton().clickCompanyDropdown().Selectcompany("Mauli").clickPlantDropdown().selectplant("Mauli")
				.clickAreaDropdown().selectArea("Gate Cane").enterGatName("Karad").enterMarathiGatName("कराड")
				.clickSavebutton();
		handelAlert(true);
		return this;
	}
	
	public GatPage checkGridsearchFunctionality() {
		return SearchInGrid("Karad");
	}
	
	public GatPage checkBlockFunctionality() {
		 clickBlock();
		 handelAlert(true);
		 return this;
	}
	
	public GatPage checkUnblockFunctionality() {
		 clickUnblock();
		 handelAlert(true);
		 return this;
	}
	
	

}
