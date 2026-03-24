package Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BitPage {
	
	WebDriver driver ;
	WebDriverWait wait ;
	
	public BitPage (WebDriver driver) {
		this.driver =driver ;
		PageFactory.initElements( driver , this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//=======Locators===========
	
	@FindBy(xpath = "//a[@href='/Bit/index']")
	private WebElement Bitmenu;
	
	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;
	
	@FindBy(id =  "select2-GatName-container")
	private WebElement GatDropdown;
	
	@FindBy(css = ".select2-search__field")
	private WebElement Gatsearch;
	
	@FindBy(id = "BitName")
	private WebElement Bitname;
	
	@FindBy(id = "BitNameMar")
	private WebElement MarathiBitname;
	
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
	
	//============Utility Methods =========
	
	private void Click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	private void type(WebElement element , String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text);
	}
	
	private void typeandenter(WebElement element , String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text , Keys.ENTER);
	}
	
	private String handelAlert(boolean accept) {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		String text = alt.getText().trim();
		if(accept) alt.accept();
		else alt.dismiss();
		return text;
	}
	
	//=======Actions========
	
	public BitPage Clickbitmenu() {
		Click(Bitmenu);
		return this;
	}
	
	public BitPage Clickcreatenewbutton() {
		Click(Createnewbutton);
		return this;
	}
	
	public BitPage enterBitname(String bitname) {
		type(Bitname , bitname);
		return this;
	}
	
	public BitPage enterMarathiBitname(String Mbitname) {
		type(MarathiBitname , Mbitname);
		return this;
	}
	
	public BitPage ClickGatdropdown() {
		Click(GatDropdown);
		return this;
	}
	
	public BitPage SearchAndSelectGat(String gat) {
		typeandenter(Gatsearch, gat);
		return this;
	}
	
	public BitPage clicksavebutton() {
		Click(Savebutton);
		return this;
	}

	public BitPage clickclearbutton() {
		Click(Clearbutton);
		return this;
	}

	public BitPage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public BitPage clickEditicon() {
		Click(Editicon);
		return this;
	}

	public BitPage clickBlock() {
		Click(Blockicon);
		return this;
	}

	public BitPage clickUnblock() {
		Click(Unblockicon);
		return this;
	}

	public BitPage clickExcelExport() {
		Click(ExcelExport);
		return this;
	}

	public BitPage clickPDFExport() {
		Click(PDFExport);
		return this;
	}
	
	public BitPage enterstatussearch(String search) {
		type(Statussearch , search);
		return this;
	}
	
	public BitPage Clickviewicon() {
		Click(Viewicon);
		return this;
	}
	
	//========Workflow Methods =========
	
	public BitPage NavigatePage() {
		return Clickbitmenu();
	}
	
	public BitPage CheckValidatorMSG() {
		return Clickcreatenewbutton()
				.clicksavebutton();
	}
	
	public BitPage Checkclearfunctionality() {
		 Clickcreatenewbutton()
				.enterBitname("test")
				.clickclearbutton();
		        handelAlert(true);
		        return this;
	}
	
	public BitPage CheckCreateNewBitDetails() {
		Clickcreatenewbutton()
		.ClickGatdropdown()
		.SearchAndSelectGat("Tuljapur")
		.enterBitname("Test Tuljapur")
		.enterMarathiBitname("टेस्ट तुळजापूर बिट")
		.clicksavebutton();
		handelAlert(true);
		return this;
	}
	
	
	public BitPage CheckEditFunctionality() {
		clickEditicon()
		.enterMarathiBitname("1203")
		.clicksavebutton();
		handelAlert(true);
		return this;
	}
	
	public BitPage CheckGridSearchFunctionality() {
		return SearchInGrid("Location");
	}
	
	public BitPage CheckViewDetailsPageOpen() {
		return Clickviewicon();
	}
	
	public BitPage CheckBlockdetails() {
		 clickBlock();
		 handelAlert(true);
		 return this;
		
	}
	
	public BitPage CheckUnblockdetails() {
	        clickUnblock();
	        handelAlert(true);
	        return this;
	}
	

}
