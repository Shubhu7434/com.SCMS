package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class SeedTreatmentPage extends BasePage<SeedTreatmentPage> {

	public SeedTreatmentPage(WebDriver driver) {
		super(driver);
	}

	// ====Locators========

	@FindBy(xpath = "(//a[contains(normalize-space(),'Seed Treatment')])[2]")
	private WebElement SeedTreatmentLink;

	@FindBy(name = "Treatment")
	private WebElement SeedTreatmentField;

	@FindBy(name = "TreatmentMarathi")
	private WebElement SeedTreatmentMarathiField;

	@FindBy(id = "validatorTreatment")
	private WebElement ValidatorTreatment;

	@FindBy(id = "validatorTreatmentMarathi")
	private WebElement ValidatorTreatmentMarathi;

	@FindBy(xpath = "//h4[text()='Seed Treatment']")
	private WebElement PageHeaderName;

	@FindBy(xpath = "//table[@id='tblData']//tr[1]//td[3]")
	private WebElement GridTableID;

	@FindBy(id = "TreatmentCodeView")
	private WebElement ViewPageID;

	@FindBy(xpath = "//table[@id='tblData']//tr[1]//td[5]")
	private WebElement GridTableStatus;

	@FindBy(id = "SeedTreatmentStatusView")
	private WebElement ViewPageStatus;

	@FindBy(xpath = "//table[@id='tblData']//tr[1]//td[3]")
	private WebElement GridSeedTreatment;

	@FindBy(id = "IsDeleted")
	private WebElement BlockCB;

	// ===Actions======

	public SeedTreatmentPage clickSeedTreatmentLink() {
		return click(SeedTreatmentLink);
	}

	public SeedTreatmentPage enterSeedTreatment(String treatment) {
		return type(SeedTreatmentField, treatment);
	}

	public SeedTreatmentPage enterMarathiSeedTreatment(String arathitreatement) {
		return type(SeedTreatmentMarathiField, arathitreatement);
	}

	public WebElement getPageHeaderName() {
		return PageHeaderName;
	}

	public WebElement getTreatmentValidatorMSG() {
		return ValidatorTreatment;
	}

	public WebElement getMarathiTreatmentValidatorMSG() {
		return ValidatorTreatmentMarathi;
	}

	public String getGridTableID() {
		return GridTableID.getText().trim();
	}

	public String getViewPageID() {
		return ViewPageID.getText().trim();
	}

	public String getGridPageStatus() {
		return GridTableStatus.getText().trim();
	}

	public String getViewPageStatus() {
		return ViewPageStatus.getText().trim();
	}

	public String getGridSeedtreatment() {
		return GridSeedTreatment.getText().trim();
	}

	public WebElement getEditBlockCheckbox() {
		return BlockCB;
	}
	
	public String getEditPageSeedValue() {
		return SeedTreatmentField.getText().trim();
	}
	
	//===== Workflow Methods =====
	
	public SeedTreatmentPage checkNavigatSeedTreatmentPage() {
		return clickCanePlantationMenu()
				.clickSeedTreatmentLink();
	}
	
	public SeedTreatmentPage checkValdatorMSG() {
		return clickCreateNewButton().clickSavebutton();
	}
	
	public SeedTreatmentPage checkCreateNewTreatmentFunctionality() {
		clickCreateNewButton()
		.enterSeedTreatment("Biological")
		.enterMarathiSeedTreatment("जैविक उपचार")
		.clickSavebutton();
		handelAlert(true);
		return this;
	}
	
	public SeedTreatmentPage checkGridSearch() {
		return SearchInGrid("Biological");
	}
	
	public SeedTreatmentPage checkEditPageDataBind() {
		return SearchInGrid("Biological")
				.clickEditicon();
	}
	
	public SeedTreatmentPage checkViewPageDataBind() {
		return SearchInGrid("Biological")
				.clickViewicon();
	}
	
	public SeedTreatmentPage checkBlockFunctionality() {
		clickBlock();
		handelAlert(true);
		return this;
		
	}
	
	
	public SeedTreatmentPage checkViewPageStatus() {
		return clickViewicon();
	}
	
	
	public SeedTreatmentPage checkEditPageCheckBoxSelected() {
		return clickEditicon();
	}
	
	public SeedTreatmentPage checkUnblockFunctionality() {
		clickUnblock();
		handelAlert(true);
		return this;
	}
	
	

}
