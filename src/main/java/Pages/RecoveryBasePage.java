package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BasePage;

public class RecoveryBasePage extends BasePage {

	WebDriver driver;
	WebDriverWait wait;

	public RecoveryBasePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// ===Locators ====

	@FindBy(xpath = " (//a[contains(text(),'Agriculture')])[1]")
	private WebElement Agriculturemenu;

	@FindBy(xpath = "//a[@href='/CanePlantation/index']")
	private WebElement Caneplantationmenu;

	@FindBy(xpath = "//a[contains(normalize-space(),'Recover Base')]")
	private WebElement Recoverbaselink;

	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;

	@FindBy(id = "select2-PlantCode-container")
	private WebElement Plantdropdown;

	@FindBy(xpath = "//input[@class='select2-search__field']")
	private WebElement Searchdropdownoption;

	@FindBy(xpath =  "(//span[@class='select2-selection select2-selection--single'])[2]")
	private WebElement Gatdropdown;

	@FindBy(name = "RecoveryBase")
	private WebElement Recoverybasefield;

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

	// ====Actions=====

	public RecoveryBasePage clickAgricultureMenu() {
		click(Agriculturemenu);
		return this;
	}

	public RecoveryBasePage clickCanePlantationMenu() {
		click(Caneplantationmenu);
		return this;
	}

	public RecoveryBasePage clickRecoverBaseLink() {
		click(Recoverbaselink);
		return this;
	}

	public RecoveryBasePage clickCreateNewButton() {
		click(Createnewbutton);
		return this;
	}

	public RecoveryBasePage clickPlantDropdown() {
		click(Plantdropdown);
		return this;
	}

	public RecoveryBasePage selectplat(String text) {
		typeAndEnter(Searchdropdownoption, text);
		return this;
	}

	public RecoveryBasePage clickGatDropdown() {
		click(Gatdropdown);
		return this;
	}

	public RecoveryBasePage selectGat(String text) {
		typeAndEnter(Searchdropdownoption, text);
		return this;
	}

	public RecoveryBasePage enterRecoverbase(String text) {
		type(Recoverybasefield, text);
		return this;
	}

	public RecoveryBasePage clickSavebutton() {
		click(Savebutton);
		return this;
	}

	public RecoveryBasePage clickClearButton() {
		click(Clearbutton);
		return this;
	}

	public RecoveryBasePage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public RecoveryBasePage clickEditicon() {
		click(Editicon);
		return this;
	}

	public RecoveryBasePage clickBlock() {
		click(Blockicon);
		return this;
	}

	public RecoveryBasePage clickUnblock() {
		click(Unblockicon);
		return this;
	}

	// ===Workflow Methods ====

	public RecoveryBasePage NavigatePage() {
		return clickAgricultureMenu().clickCanePlantationMenu().clickRecoverBaseLink();
	}

	public RecoveryBasePage checkValidatorMSG() {
		return clickCreateNewButton().clickSavebutton();
	}

	public RecoveryBasePage checkRcoverBaseFieldValidation() {
		return clickCreateNewButton().enterRecoverbase("50");
	}

	public RecoveryBasePage checkGatDropdownOptionAvailable() {
        return clickCreateNewButton()
        		.clickPlantDropdown()
        		.selectplat("Mauli")
        		.clickGatDropdown();
	}

}
