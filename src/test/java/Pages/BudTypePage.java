package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class BudTypePage extends BasePage<BudTypePage>{
	
	
	public BudTypePage (WebDriver driver) {
		super(driver);
	}

	//====Locators ======
	
	@FindBy(xpath = "//a[contains(normalize-space(),'Bud Type')]")
	private WebElement BudTypeLink;
	
	@FindBy(name = "BudName")
	private WebElement BudNameField;
	
	@FindBy(name = "BudMarathi")
	private WebElement MarathiBudNameField;
	
	@FindBy(id = "validatorBudName")
	private WebElement ValidatorBudName;
	
	@FindBy(id = "validatorBudMarathi")
	private WebElement ValidatorBudMarathi;
	
	@FindBy(xpath = "//h4[text()='Bud Type']")
	private WebElement PageHeaderName;

	@FindBy(xpath = "//table[@id='tblData']//tr[1]//td[2]")
	private WebElement GridTableID;

	@FindBy(id = "BudCodeView")
	private WebElement ViewPageID;

	@FindBy(xpath = "//table[@id='tblData']//tr[1]//td[5]")
	private WebElement GridTableStatus;

	@FindBy(id = "BudStatusView")
	private WebElement ViewPageStatus;

	@FindBy(xpath = "//table[@id='tblData']//tr[1]//td[3]")
	private WebElement GridSeedTreatment;

	@FindBy(id = "IsDeleted")
	private WebElement BlockCB;
	
	//====== Actions =======
	
	public BudTypePage clickBudTypeLink() {
		click(BudTypeLink);
		return this;
	}
	
	public BudTypePage enterBudTypeName(String budtype) {
		type(BudNameField, budtype);
		return this;
	}
	
	public BudTypePage enterMarathiBudType(String marathibudtype) {
		type(MarathiBudNameField, marathibudtype);
		return this;
	}
	
	public WebElement getBudTypeValidator() {
		return ValidatorBudName;
	}
	
	//===  getter methods =====
	public WebElement getMarathiBudTypeValidator() {
		return ValidatorBudMarathi;
	}
	
	public String getGridTableID() {
		return GridTableID.getText().trim();
	}
	
	public String getViewPageID() {
		return ViewPageID.getText().trim();
	}
	
	public String getGridTableStatus() {
		return GridTableStatus.getText().trim();
	}
	
	public String getViewPageStatus() {
		return ViewPageStatus.getText().trim();
	}
	
	public WebElement getEditBlockCheckbox() {
		return BlockCB;
	}
	
	public WebElement getPageHeaderName() {
		return PageHeaderName;
	}
	
	
	// === Workflow Methods =====
	
	public BudTypePage checkNavigationBudTypePage() {
		return clickCanePlantationMenu()
				.clickBudTypeLink();
	}
	
	public BudTypePage checkValidatorMSG() {
		return clickCreateNewButton().clickSavebutton();
	}
}
