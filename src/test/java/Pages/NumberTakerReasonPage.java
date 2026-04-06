package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class NumberTakerReasonPage extends BasePage<NumberTakerReasonPage> {

	public NumberTakerReasonPage(WebDriver driver) {
		super(driver);
	}

	// ===Locators =====

	@FindBy(xpath = "//a[contains(normalize-space(),'Number Taker Reason')]")
	private WebElement NumberTakerReasonMenu;

	@FindBy(name = "ReasonName")
	private WebElement ReasonField;

	@FindBy(name = "ReasonMarathi")
	private WebElement ReasonMarathiField;

	@FindBy(id = "validatorReasonName")
	private WebElement ValidatorReason;

	@FindBy(id = "validatorReasonMarathi")
	private WebElement ValidatorMarathiReason;

	@FindBy(xpath = "//h4[text()='Number Taker Reason']")
	private WebElement PageHeaderName;

	@FindBy(xpath = "//table[@id='tblData']//tr[1]//td[2]")
	private WebElement GridReason;

	@FindBy(id = "IsDeleted")
	private WebElement BlockCB;

	@FindBy(id = "modalpopupaddupdateLabel")
	private WebElement EditPageName;

	@FindBy(id = "modalpopupviewLabel")
	private WebElement ViewPageName;

	// === Actions ======

	public NumberTakerReasonPage clickNumberTakerReasonMenu() {
		click(NumberTakerReasonMenu);
		return this;
	}

	public NumberTakerReasonPage enterReason(String reason) {
		type(ReasonField, reason);
		return this;

	}

	public NumberTakerReasonPage enterMarathiReason(String marathireason) {
		type(ReasonMarathiField, marathireason);
		return this;
	}

	public WebElement getPageHeaderName() {
		return PageHeaderName;
	}

	public WebElement getReasonValidatorMSG() {
		return ValidatorReason;
	}

	public WebElement getMarathiReasonValidatorMSG() {
		return ValidatorMarathiReason;
	}

	public String getGridReasonValue() {
		return GridReason.getText().trim();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getEditPageName() {
		return EditPageName.getText().trim();
	}

	public String getViewPageName() {
		return ViewPageName.getText().trim();
	}

	// ===== WorkFlow Methods ========

	public NumberTakerReasonPage NavigatePage() {
		return clickCaneYardMenu().clickNumberTakerReasonMenu();
	}

	public NumberTakerReasonPage checkValidatorMSG() {
		return clickCreateNewButton().clickSavebutton();
	}

	public NumberTakerReasonPage checkCreateNewReasonFunctionality() {
		clickCreateNewButton().enterReason("Test Number Taker Reason").enterMarathiReason("कारण प्रविष्ट करा")
				.clickSavebutton();
		handelAlert(true);
		return this;
	}

	public NumberTakerReasonPage checkGridSearchFunctionality() {
         return SearchInGrid("SMS Problem");
	}
	
	public NumberTakerReasonPage checkEditPageName() {
		return clickEditicon();
	}
	
	public NumberTakerReasonPage checkViewDetailsPageName() {
		return clickViewicon();
	}
	
	public NumberTakerReasonPage checkBlockFunctionality() {
		clickBlock();
		handelAlert(true);
		return this;
	}
	
	public NumberTakerReasonPage checkUnblockFunctionality() {
		clickUnblock();
		handelAlert(true);
		return this;
	}

}
