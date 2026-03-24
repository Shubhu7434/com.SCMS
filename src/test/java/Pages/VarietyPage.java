package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BasePage;

public class VarietyPage extends BasePage {
	
	WebDriverWait wait ;
	
	public VarietyPage(WebDriver driver) {
		super(driver);
	}

	//====== Locators ==========
	
	@FindBy(xpath = " (//a[contains(text(),'Agriculture')])[1]")
	private WebElement Agriculturemenu;

	@FindBy(xpath = "//a[@href='/CanePlantation/index']")
	private WebElement Caneplantationmenu;
	
	@FindBy(xpath = "(//a[contains(normalize-space(),'Variety')])[2]")
	private WebElement VarietyLink;
	
	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;
	
	@FindBy(name = "VarietyName")
	private WebElement VarietyNameField;
	
	@FindBy(name = "VarietyMarathi")
	private WebElement VarietyMarathiField;
	
	@FindBy(name = "Acer")
	private WebElement AcerField;
	
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
	
	@FindBy(id = "validatorVarietyName")
	private WebElement ValidatorVarietyName;
	
	@FindBy(id = "validatorVarietyMarathi")
	private WebElement ValidatorVarietyMarathi;
	
	@FindBy(id = "validatorAcer")
	private WebElement ValidatorAcer;
	
	@FindBy(xpath = "//h4[text()='Variety']")
	private WebElement PageHeaderName;
	
	//====== Actions =======
	
	  
	public VarietyPage clickAgricultureMenu() {
		click(Agriculturemenu);
		return this;
	}

	public VarietyPage clickCanePlantationMenu() {
		click(Caneplantationmenu);
		return this;
	}

	public VarietyPage clickVarietylink() {
		click(VarietyLink);
		return this;
	}

	public VarietyPage clickCreateNewButton() {
		click(Createnewbutton);
		return this;
	}
	
	public VarietyPage enterVarietyName(String varietyname) {
		type(VarietyNameField, varietyname);
		return this;
	}
	
	public VarietyPage enterMarathiVarietyName(String marathivarietyname) {
		type(VarietyMarathiField, marathivarietyname);
		return this;
		}
	
	public VarietyPage enterAcer(String acer) {
		type(AcerField, acer);
		return this;
	}
	
	public VarietyPage clickSavebutton() {
		click(Savebutton);
		return this;
	}

	public VarietyPage clickClearButton() {
		click(Clearbutton);
		return this;
	}

	public VarietyPage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public VarietyPage clickEditicon() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Editicon);
		return this;
	}

	public VarietyPage clickViewicon() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Viewicon);
		return this;
	}

	public VarietyPage clickBlock() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Blockicon);
		return this;
	}

	public VarietyPage clickUnblock() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Unblockicon);
		return this;
	}

	public WebElement getPageHeaderName() {
		return PageHeaderName;
	}

	public WebElement getVarietynameValidatorMSG() {
		return ValidatorVarietyName;
	}

	public WebElement getMarathiVarietyNameValidatorMSG() {
		return ValidatorVarietyMarathi;
	}
	
	public WebElement getAcerValidatorMSG() {
		return ValidatorAcer;
	}
	
	//==== WorkFlow Methods ======
	
	public VarietyPage checkNavigationPage() {
		return clickCanePlantationMenu()
				.clickVarietylink();
	}
	
	public VarietyPage checkValidatorMSG() {
		return clickCreateNewButton()
				.clickSavebutton();
	}
	
	public VarietyPage checkCreateNewVariety() {
		clickCreateNewButton()
		.enterVarietyName("2001")
		.enterMarathiVarietyName("2001")
		.enterAcer("25")
		.clickSavebutton();
		handelAlert(true);
		return this;
	}
	
	public VarietyPage checkGridSearchFunctionality() {
	       return SearchInGrid("2001");
	}
	
	public VarietyPage checkEditPageData() {
		return SearchInGrid("2001")
				.clickEditicon();
	}
	
	public VarietyPage checkDetailsPageData() {
		 return SearchInGrid("2001")
				.clickViewicon();
	}
	
	public VarietyPage checkBlockFunctionality() {
		clickBlock();
		handelAlert(true);
		return this;
	}

	public VarietyPage checkUnblockFunctionality() {
		clickUnblock();
		handelAlert(true);
		return this;
	}
	
	
	
	

}
