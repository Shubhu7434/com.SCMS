package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BasePage;

public class CaneMaturityPage extends BasePage {

	
	public CaneMaturityPage ( WebDriver driver) {
		super(driver);
		PageFactory.initElements( driver , this);
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	}
	
	//=====Locators======
	
	@FindBy(xpath = " (//a[contains(text(),'Agriculture')])[1]")
	private WebElement Agriculturemenu;
	
	@FindBy(xpath = "//a[@href='/CanePlantation/index']")
	private WebElement Caneplantationmenu;
	
	@FindBy(xpath = "//a[contains(normalize-space(),'Cane Maturity Period')]")
	private WebElement Canematuritylink;
	
	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;
	
	@FindBy(xpath = "//label[normalize-space()='Create']//input[@type='radio']")
	private WebElement CreateRadioButton;
	
	@FindBy(xpath = "//label[normalize-space()='Excel Upload']//input[@type='radio']")
	private WebElement ExcelUploadRadioButton;
	
	@FindBy(id = "select2-HangamName-container")
	private WebElement HangamNameDropdown;
	
	@FindBy(xpath = "//input[@class='select2-search__field']")
	private WebElement SearchHangamName;
	
	@FindBy(name = "Tonnage")
	private WebElement TonnageField;
	
	@FindBy(id =  "select2-VarietyName-container")
	private WebElement VarietyNameDropdown;
	
	@FindBy(xpath =  "//input[@class='select2-search__field']")
	private WebElement SearchvarietyName;
	
	@FindBy(name = "RecoveryBase")
	private WebElement RecoveryBaseField;
	
	@FindBy(name = "MatureDays")
	private WebElement MatureDaysField;
	
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
	
	//===Actions===
	
	public CaneMaturityPage clickAgricultureMenu() {
		click(Agriculturemenu);
		return this;
	 }
	
	public CaneMaturityPage clickCanePlantationMenu() {
		click(Caneplantationmenu);
		return this;
	}
	
	public CaneMaturityPage clickCanematuritylink() {
		click(Canematuritylink);
		return this;
	}
	
	public CaneMaturityPage clickCreateNewButton() {
		click(Createnewbutton);
		return this;
	}
	
	public CaneMaturityPage clickCreateRadioButton() {
		click(CreateRadioButton);
		return this ;
	}
	
	public CaneMaturityPage clickEacelRadioButton() {
		click(ExcelUploadRadioButton);
		return this;
	}
	
	public CaneMaturityPage clickHangamNameDropdown() {
		click(HangamNameDropdown);
		return this;
	}
	
	public CaneMaturityPage selectHangamName(String text) {
		typeAndEnter(SearchHangamName, text);
		return this;
	}
	
	public CaneMaturityPage enterTonnage(String text) {
		type(TonnageField , text);
		return this;
		
	}
	
	public CaneMaturityPage clickVarietyNameDropdown() {
		click(VarietyNameDropdown);
		return this;
	}
	
	public CaneMaturityPage selectVarietyName(String text) {
		typeAndEnter(SearchvarietyName, text);
		return this;
	}
	
	public CaneMaturityPage enterRecoveryBase(String text) {
		type(RecoveryBaseField, text);
		return this ;
	}
	
	public CaneMaturityPage enterMatureDays(String text) {
		type(MatureDaysField, text);
		return this;
	}
	
	public CaneMaturityPage clickSavebutton() {
		click(Savebutton);
		return this;
	}
	
	public CaneMaturityPage clickClearButton() {
		click(Clearbutton);
		return this;
	}
	

	public CaneMaturityPage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public CaneMaturityPage clickEditicon() {
		click(Editicon);
		return this;
	}

	public CaneMaturityPage clickBlock() {
		click(Blockicon);
		return this;
	}

	public CaneMaturityPage clickUnblock() {
		click(Unblockicon);
		return this;
	}
	
	//===Workflow Methods ====
	
	public CaneMaturityPage NavigatePage() {
		return  clickAgricultureMenu()
				.clickCanePlantationMenu()
				.clickCanematuritylink();
	}
	
	public CaneMaturityPage checkTonnageFieldValidation() {
		return clickCreateNewButton()
				.enterTonnage("250");
	}
	
	public CaneMaturityPage checkRecoveryBaseValidation() {
		return clickCreateNewButton()
				.enterRecoveryBase("50");
	}
	
	public CaneMaturityPage checkValidatorMSG() {
		return clickCreateNewButton()
				.clickSavebutton();
	}
	
	public CaneMaturityPage checkCreateNewCaneMaturityPeriod() {
		  clickCreateNewButton()
		  .clickHangamNameDropdown()
		  .selectHangamName("Set")
		  .enterTonnage("500")
		  .clickVarietyNameDropdown()
		  .selectVarietyName("86032")
		  .enterRecoveryBase("25")
		  .enterMatureDays("120")
		  .clickSavebutton();
		  handelAlert(true);
		  return this;
	}
	
	
	public CaneMaturityPage checkGridSearchFunctionality() {
		return SearchInGrid("Suru");
				
	}
	
	public CaneMaturityPage checkBlockFunctipnality() {
		clickBlock();
		handelAlert(true);
		return this;
	}
	
	public CaneMaturityPage checkUnblockfunctionality() {
		clickUnblock();
		handelAlert(true);
		return this;
	}
	
	
	
	
}
