package Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FuelTypePage {
	
	
	private WebDriver driver ;
	private WebDriverWait wait ;
	
	public FuelTypePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver , this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//===Locators============
	@FindBy(xpath = "//a[contains(text(),' Fuel Management')]")
	private WebElement Fuelmanagementmenu;
	
	@FindBy(xpath = "//a[contains(text(),' Fuel Pump')]")
	private WebElement FuelPumpmenu;
	
	@FindBy(xpath = "//a[@href='/FuelType/index']")
	private WebElement FuelTypemenu;
	
	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;
	
	@FindBy(xpath = "//input[@name='FuelType']")
	private WebElement Fueltypefield;
	
	@FindBy(xpath = "//input[@name='FuelTypeMarathi']")
	private WebElement MarathiFuelTypefield;
	
	@FindBy(xpath = "//input[@name='ReferenceFuelTypeId']")
	private WebElement FuelTypeIDField;
	
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
	
	@FindBy(xpath = "//button[@title='Show rows']")
	private WebElement Column;
	
	@FindBy(xpath = "//button[.//span[text()='500']]")
	private WebElement Rowsize;
	
	@FindBy(xpath = "(//input[@class='search-input'])[7]")
	private WebElement Statussearch;
	
	@FindBy(xpath = "(//i[@title='View'])[1]")
	private  WebElement Viewicon;
	
	//========utility Methods ========
	
	private void Click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	private void type(WebElement element , String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text);
	}
	
	private String handelAlert(boolean accept) {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		String text = alt.getText().trim();
		if (accept) alt.accept();
		else alt.dismiss();
		return text;
	}
	
	//========== Actions =============
	
	public FuelTypePage ClickFuelmanagementmenu() {
		Click(Fuelmanagementmenu);
		return this;
	}
	
	public FuelTypePage ClickFuelpumpmenu() {
		Click(FuelPumpmenu);
		return this;
	}
	
	public FuelTypePage ClickFueltypelink() {
		Click(FuelTypemenu);
		return this;
	}
	
	public FuelTypePage Clickcreatenewbutton() {
		Click(Createnewbutton);
		return this;
	}
	
	public FuelTypePage enterfueltype(String text) {
		type(Fueltypefield , text);
		return this;
	}
	
	public FuelTypePage enterMarathifueltype(String text) {
		type(MarathiFuelTypefield , text);
		return this;
	}
	
	public FuelTypePage enterfueltypeID(String text) {
		type(FuelTypeIDField , text);
		return this;
	} 
	
	public FuelTypePage clicksavebutton() {
		Click(Savebutton);
		return this;
	}

	public FuelTypePage clickclearbutton() {
		Click(Clearbutton);
		return this;
	}

	public FuelTypePage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public FuelTypePage clickEditicon() {
		Click(Editicon);
		return this;
	}

	public FuelTypePage clickBlock() {
		Click(Blockicon);
		return this;
	}

	public FuelTypePage clickUnblock() {
		Click(Unblockicon);
		return this;
	}

	public FuelTypePage clickExcelExport() {
		Click(ExcelExport);
		return this;
	}

	public FuelTypePage clickPDFExport() {
		Click(PDFExport);
		return this;
	}
	
	public FuelTypePage enterstatussearch(String search) {
		type(Statussearch , search);
		return this;
	}
	
	public FuelTypePage Clickviewicon() {
		Click(Viewicon);
		return this;
	}
	
	
	//===== Workflow Methods =========
	
	public FuelTypePage NavigatePage() {
		return ClickFuelmanagementmenu()
				.ClickFuelpumpmenu()
				.ClickFueltypelink();
	}
	
	public FuelTypePage CheckValidatorMSG() {
		return Clickcreatenewbutton()
				.clicksavebutton();                                                                                              
	}
	
	public FuelTypePage CheckvalidatorMSGClear() {
		return Clickcreatenewbutton()
				.enterMarathifueltype("test")
		         .enterMarathifueltype("टेस्ट ");
	}
	
	public FuelTypePage CheckCreateNewFuelType() {
		
		Clickcreatenewbutton()
		.enterfueltype("Test Fuel")
		.enterMarathifueltype("टेस्ट फ्युल ")
		.enterfueltypeID("FT23")
		.clicksavebutton();
		handelAlert(true);
		return this;
	}
	
	public FuelTypePage CheckMaxLengthInputDetailsSave() {
		Clickcreatenewbutton()
		.enterfueltype("Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test Fuel23Test ")
		.enterMarathifueltype("टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्युल123टेस्ट फ्यु ")
		.enterfueltypeID("FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235FT235")
		.clicksavebutton();
		handelAlert(true);
		return this;
	}
	
	public FuelTypePage CHeckGridSearchFunctionality() {
		return SearchInGrid("petrol");
	}
	
	
	public FuelTypePage CheckBlockFunctionality() {
		clickBlock();
		handelAlert(true);
		return this;
	}
	
	public FuelTypePage Checkunblockfunctionality() {
		clickUnblock();
		handelAlert(true);
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
