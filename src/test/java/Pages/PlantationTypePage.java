package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BasePage;

public class PlantationTypePage extends BasePage<PlantationTypePage>{

	WebDriverWait wait;

	public PlantationTypePage(WebDriver driver) {
		super(driver);
	}

	// =====Locators =======


	@FindBy(xpath = "//a[contains(normalize-space(),'Plantation Types')]")
	private WebElement PlantationtypeLink;

	@FindBy(name = "CanePlantName")
	private WebElement CanePlantNameField;

	@FindBy(name = "CanePlantMarathi")
	private WebElement CanePlantMarathiField;

	@FindBy(id = "validatorCanePlantName")
	private WebElement CanePlantValidator;

	@FindBy(id = "validatorCanePlantMarathi")
	private WebElement CanePlantMarathiValidator;
	
	@FindBy(xpath = "//h4[text()='Plantation Types']")
	private WebElement PageHeaderName;
	
	@FindBy(xpath = "//table[@id='tblData']//tr[1]//td[3]")
	private WebElement GridTableID;
	
	@FindBy(id = "CanePlantCodeView")
	private WebElement ViewPageID;
	
	@FindBy(xpath = "//table[@id='tblData']//tr[1]//td[5]")
	private WebElement GridTableStatus;
	
	@FindBy(id = "CanePlantStatusView")
	private WebElement ViewPageStatus;
	
	@FindBy(xpath = "//table[@id='tblData']//tr[1]//td[3]")
	private WebElement GridCaneType;
	
	@FindBy(id = "IsDeleted")
	private WebElement BlockCB;
	
	//=====Actions======
	
	

	public PlantationTypePage clickPlantationTypelink() {
		click(PlantationtypeLink);
		return this;
	}

	
	public PlantationTypePage enterCanePlant(String caneplant) {
		type(CanePlantNameField, caneplant);
		return this;	
	}
	
	public PlantationTypePage enterMarathiCanePlant(String marathicaneplant) {
		type(CanePlantMarathiField, marathicaneplant);
		return this;
	}

	public WebElement getPageHeaderName() {
		return PageHeaderName;
	}
	
	public WebElement getCanePlantValidator() {
		return CanePlantValidator;
	}
	
	public WebElement getMarathiCanePlantValiator() {
		return CanePlantMarathiValidator;
	}
	
	public String getCanePlantValue() {
		return CanePlantNameField.getText().trim();
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
	
	public WebElement getBlockChekbox() {
		return BlockCB;
	}
	
	public String getGridCaneType() {
		return GridCaneType.getText().trim();
	}
	
	
	//===== WorkFlow Methods =====
	
	public PlantationTypePage checkNavigationPage() {
		return  clickCanePlantationMenu()
	    .clickPlantationTypelink();
		 
	}
	
	public PlantationTypePage checkvalidatoerMSG() {
		return clickCreateNewButton()
				.clickSavebutton();
	}
	
	public PlantationTypePage checkCreateNewplantationType() {
		clickCreateNewButton()
		.enterCanePlant("2 Feet Sarry")
		.enterMarathiCanePlant("2 फुटी सरी")
		.clickSavebutton();
		handelAlert(true);
		return this;
	}
	
	public PlantationTypePage checkGridSearchFunctionality() {
		return SearchInGrid("2 Feet Sarry");
	}
	
	public PlantationTypePage checkEditPageDataBindCorrect() {
		return SearchInGrid("2 Feet Sarry")
				.clickEditicon();
	}
	
	public PlantationTypePage checkViewPageData() {
		return SearchInGrid("2 Feet Sarry")
				.clickViewicon();
	}
	
	public PlantationTypePage checkBlockFunctionality() {
		clickBlock();
		handelAlert(true);
		return this;
		
	}
	
	
	public PlantationTypePage checkViewPageStatus() {
		return clickViewicon();
	}
	
	
	public PlantationTypePage checkEditPageCheckBoxSelected() {
		return clickEditicon();
	}
	
	public PlantationTypePage checkUnblockFunctionality() {
		clickUnblock();
		handelAlert(true);
		return this;
	}
	
	
	
	
	
	
}
