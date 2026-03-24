package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BasePage;

public class VillagePage extends BasePage<VillagePage> {

	WebDriverWait wait;

	public VillagePage(WebDriver driver) {
		super(driver);
	}

	// ==Locators=====

	@FindBy(xpath = " (//a[contains(text(),'Agriculture')])[1]")
	private WebElement Agriculturemenu;

	@FindBy(xpath = "//a[@href='/CanePlantation/index']")
	private WebElement Caneplantationmenu;

	@FindBy(xpath = "//a[@href='/Village/index']")
	private WebElement Villagelink;

	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;

	@FindBy(id = "select2-GatName-container")
	private WebElement GatDropdown;

	@FindBy(css = ".select2-search__field")
	private WebElement SearchDropdownOption;

	@FindBy(id = "select2-TalName-container")
	private WebElement TalukaDropdown;

	@FindBy(name = "VillageName")
	private WebElement VillageField;

	@FindBy(name = "VillageNameMar")
	private WebElement VillageNameMarathiField;

	@FindBy(name = "Dieselqty")
	private WebElement DieselqtyField;

	@FindBy(name = "DistATractor")
	private WebElement DistATractorField;

	@FindBy(id = "tractorCheckbox")
	private WebElement TractorApplyAllCheckbox;

	@FindBy(name = "DistATruck")
	private WebElement DistATruckField;

	@FindBy(id = "truckCheckbox")
	private WebElement TruckApplyAllCheckbox;

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

	// === Actions =====

	public VillagePage clickAgricultureMenu() {
		click(Agriculturemenu);
		return this;
	}

	public VillagePage clickCanePlantationMenu() {
		click(Caneplantationmenu);
		return this;
	}

	public VillagePage clickVillagelink() {
		click(Villagelink);
		return this;
	}

	public VillagePage clickCreateNewButton() {
		click(Createnewbutton);
		return this;
	}

	public VillagePage clickGateDropdown() {
		click(GatDropdown);
		return this;
	}

	public VillagePage selectGat(String gat) {
		typeAndEnter(SearchDropdownOption, gat);
		return this;
	}

	public VillagePage clickTalukaDropdown() {
		click(TalukaDropdown);
		return this;
	}

	public VillagePage selectTaluka(String talukaname) {
		typeAndEnter(SearchDropdownOption, talukaname);
		return this;
	}

	public VillagePage enterVillageName(String villagename) {
		type(VillageField, villagename);
		return this;
	}

	public VillagePage enterMarathiVillageName(String marathivillage) {
		type(VillageNameMarathiField, marathivillage);
		return this;
	}

	public VillagePage enterDiselqty(String diselqty) {
		type(DieselqtyField, diselqty);
		return this;
	}

	public VillagePage enterTractorADistance(String tractordistance) {
		type(DistATractorField, tractordistance);
		return this;
	}

	public VillagePage clickTractorCheckbox() {
		click(TractorApplyAllCheckbox);
		return this;
	}

	public VillagePage enterTruckADistance(String truckdistance) {
		type(DistATruckField, truckdistance);
		return this;
	}

	public VillagePage clickTruckCheckbox() {
		click(TruckApplyAllCheckbox);
		return this;
	}

	public VillagePage clickSavebutton() {
		click(Savebutton);
		return this;
	}

	public VillagePage clickClearButton() {
		click(Clearbutton);
		return this;
	}

	public VillagePage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public VillagePage clickEditicon() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Editicon);
		return this;
	}
	
	public VillagePage clickViewicon() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Viewicon);
		return this;
	}

	public VillagePage clickBlock() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Blockicon);
		return this;
	}

	public VillagePage clickUnblock() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Unblockicon);
		return this;
	}

	// ==Workflow Methods ====

	public VillagePage checkNaviagtionPage() {
		return clickAgricultureMenu().clickCanePlantationMenu().clickVillagelink();
	}

	public VillagePage checkaValidatorMSG() {
		return clickCreateNewButton().clickSavebutton();
	}

	public VillagePage checkCreateNewVillage() {
		clickCreateNewButton().clickGateDropdown().selectGat("Kolhapur").clickTalukaDropdown().selectTaluka("Shirala")
				.enterVillageName("Mangale").enterMarathiVillageName("मांगले").enterDiselqty("25")
				.enterTractorADistance("5").clickTractorCheckbox().enterTruckADistance("15").clickTruckCheckbox()
				.clickSavebutton();
		handelAlert(true);
		return this;
	}
	
	public VillagePage checkGridSearch() {
		return SearchInGrid("Mangale");
	}
	
	public VillagePage checkEditPageData() {
		return SearchInGrid("Mangale")
				.clickEditicon();
	}
	
	public VillagePage checkBlockFunctionality() {
		clickBlock();
		handelAlert(true);
		return this;
	}
	
	public VillagePage checkDetailsPageStatus() {
		return clickViewicon();
	}
	
	public VillagePage checkunblockFunctionality() {
		clickUnblock();
		handelAlert(true);
		return this;
	}

}
