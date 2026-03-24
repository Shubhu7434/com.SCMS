package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BasePage;

public class VillageRoutePage extends BasePage<VillageRoutePage> {

	WebDriverWait wait;

	public VillageRoutePage(WebDriver driver) {
		super(driver);
	}

	// ==Locators=====

	@FindBy(xpath = " (//a[contains(text(),'Agriculture')])[1]")
	private WebElement Agriculturemenu;

	@FindBy(xpath = "//a[@href='/CanePlantation/index']")
	private WebElement Caneplantationmenu;

	@FindBy(xpath = "(//a[contains(normalize-space(),'Village Route')])[2]")
	private WebElement VillageRoutelink;

	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;

	@FindBy(id = "select2-PlantCode-container")
	private WebElement PlantDropdown;

	@FindBy(xpath = "//input[@class='select2-search__field']")
	private WebElement SearchDropdownOption;

	@FindBy(xpath = "//span[@class='select2-selection select2-selection--multiple']")
	private WebElement VehicleTypeDropdown;

	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[2]")
	private WebElement GatDropdown;

	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[3]")
	private WebElement VillageDropdown;

	@FindBy(name = "Route")
	private WebElement RouteNameField;

	@FindBy(name = "RouteMarathi")
	private WebElement RouteMarathiField;

	@FindBy(name = "Distance")
	private WebElement DistanceField;

	@FindBy(id = "Diesel")
	private WebElement DiselField;

	@FindBy(id = "RefRouteCode")
	private WebElement RefRouteCodeField;

	@FindBy(id = "IsFixedRoute")
	private WebElement IsFixedRouteCheckbox;

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
	
	@FindBy(id = "validatorPlant")
	private WebElement PlantValidatorMSG;
	
	@FindBy(id = "validatorRoute")
	private WebElement RouteValidatorMSG;
	
	@FindBy(xpath = "//h4[text()='Village Route']")
	private WebElement PageHeaderName;

	// ====ACtions=====

	public VillageRoutePage clickAgricultureMenu() {
		click(Agriculturemenu);
		return this;
	}

	public VillageRoutePage clickCanePlantationMenu() {
		click(Caneplantationmenu);
		return this;
	}

	public VillageRoutePage clickVillageRoutelink() {
		click(VillageRoutelink);
		return this;
	}

	public VillageRoutePage clickCreateNewButton() {
		click(Createnewbutton);
		return this;
	}

	public VillageRoutePage clickPlantDropdown() {
		click(PlantDropdown);
		return this;
	}

	public VillageRoutePage selectplant(String plant) {
		typeAndEnter(SearchDropdownOption, plant);
		return this;
	}

	public VillageRoutePage clickVehicleTypeDropdown() {
		click(VehicleTypeDropdown);
		return this;
	}

	public VillageRoutePage selectVehicleType(String vehicletype) {
		typeAndEnter(VehicleTypeDropdown, vehicletype);
		return this;
	}

	public VillageRoutePage clickGatDropdown() {
		click(GatDropdown);
		return this;
	}

	public VillageRoutePage selectGat(String gat) {
		typeAndEnter(Agriculturemenu, gat);
		return this;
	}

	public VillageRoutePage clickVillageDropdown() {
		click(VillageDropdown);
		return this;
	}

	public VillageRoutePage selectVillage(String village) {
		typeAndEnter(SearchDropdownOption, village);
		return this;
	}

	public VillageRoutePage enterRoutname(String route) {
		type(RouteNameField, route);
		return this;
	}

	public VillageRoutePage enterMarathiRoute(String routemarathi) {
		type(RouteMarathiField, routemarathi);
		return this;
	}

	public VillageRoutePage enterDistance(String distance) {
		type(DistanceField, distance);
		return this;
	}

	public VillageRoutePage enterDisel(String disel) {
		type(DiselField, disel);
		return this;
	}

	public VillageRoutePage enterRefRouteCode(String refcode) {
		type(RefRouteCodeField, refcode);
		return this;
	}

	public VillageRoutePage clickFixedRouteCheckbox() {
		click(IsFixedRouteCheckbox);
		return this;
	}

	public VillageRoutePage clickSavebutton() {
		click(Savebutton);
		return this;
	}

	public VillageRoutePage clickClearButton() {
		click(Clearbutton);
		return this;
	}

	public VillageRoutePage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public VillageRoutePage clickEditicon() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Editicon);
		return this;
	}

	public VillageRoutePage clickViewicon() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Viewicon);
		return this;
	}

	public VillageRoutePage clickBlock() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Blockicon);
		return this;
	}

	public VillageRoutePage clickUnblock() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Unblockicon);
		return this;
	}

	// ==== Workflow Methods ===

	public VillageRoutePage checkNavigationPage() {
		return clickAgricultureMenu().clickCanePlantationMenu().clickVillageRoutelink();
	}
	
	public WebElement getPlantValidatorMSG() {
		return PlantValidatorMSG;
	}
	
	public WebElement getVillageRouteValidatorMSG() {
		return RouteValidatorMSG;
	}
	
	public WebElement getPageHeaderName() {
		return PageHeaderName;
	}
	

	public VillageRoutePage chekckValidatorMSG() {
		return clickCreateNewButton().clickSavebutton();
	}

	public VillageRoutePage checkGatDropdownData() {
		return clickCreateNewButton().clickGatDropdown();
	}

	public VillageRoutePage checkCreateVillageRouteFunctionaity() {
		clickCreateNewButton().clickPlantDropdown().selectplant("Mauli").clickVehicleTypeDropdown()
				.selectVehicleType("Truck").clickGatDropdown().selectGat("kolhapur").clickVillageDropdown()
				.selectVillage("Mangale").enterRoutname("Route Test").enterMarathiRoute("रूट टेस्ट").enterDistance("52")
				.enterDisel("200");
		return this;
	}

	public VillageRoutePage checkGridSearch() {
		return SearchInGrid("LOHARA");
	}

	public VillageRoutePage checkBlockFunctionality() {
		clickBlock();
		handelAlert(true);
		return this;
	}

	public VillageRoutePage checkUnblockFunctionality() {
		clickUnblock();
		handelAlert(true);
		return this;
	}

}
