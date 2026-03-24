package Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DistrictPage {
	
	private WebDriver driver ;
	private WebDriverWait wait ;
	
	public DistrictPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//==========Locators=========//
	@FindBy(xpath = "(//a[contains(text(),' Enterprise')])[1]")
	private WebElement Enterprisemenu;
	
	@FindBy(xpath = "(//a[contains(text(),' Enterprise')])[2]")
	private WebElement EnterpriseSubmenu;
	
	@FindBy(xpath = "//a[@href='/District/index']")
	private WebElement Districtmenu;
	
	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;
	
	@FindBy(xpath = "//input[@name='DistName']")
	private WebElement DistrictField;
	
	@FindBy(xpath = "//input[@name='DistNameMar']")
	private WebElement DistrictMarathiField;
	
	@FindBy(id = "select2-StateName-container")
	private WebElement Statedropdown;
	
	@FindBy(css = ".select2-search__field")
	private WebElement Searchstate;
	
	@FindBy(xpath = "//input[@name='Code']")
	private WebElement DistrictCodeField;
	
	@FindBy(xpath = "//input[@name='Lattitude']")
	private WebElement LattitudeField;
	
	@FindBy(xpath = "//input[@name='Langitude']")
	private WebElement LangitudeField;
	
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
	
	//====Utility Methods =======
	private void Click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	private void type(WebElement element , String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text);
	}
	
	private void typeandenter(WebElement element , String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text,Keys.ENTER);
	}
	
	private String handelalert(boolean accept) {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		String text = alt.getText().trim();
		if(accept) alt.accept();
		else alt.dismiss();
		return text;
		
		
	}
	
	//===== Actions==========
	
	public DistrictPage ClickEnterpriseMenu() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", Enterprisemenu);
		wait.until(ExpectedConditions.elementToBeClickable(Enterprisemenu)).click();
		return this;
	}
	
	public DistrictPage ClickEnterpriseSubMenu() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", EnterpriseSubmenu);
		wait.until(ExpectedConditions.elementToBeClickable(EnterpriseSubmenu)).click();
		return this;
	}
	
	public DistrictPage ClickDistrict() {
		wait.until(ExpectedConditions.elementToBeClickable(Districtmenu)).click();
		return this;
	}
	
	public DistrictPage Clickcreatenewbutton() {
		Click(Createnewbutton);
		return this;
	}
	
	public DistrictPage enterDistrictname(String Dname) {
		type(DistrictField , Dname);
		return this;
	}
	
	public DistrictPage enterDistrictMarname(String DMname) {
		type(DistrictMarathiField , DMname);
		return this;
	}
	
	public DistrictPage clickStateDropdown() {
		Click(Statedropdown);
		return this;
	}
	
	public DistrictPage selectState(String state) {
		typeandenter(Searchstate, state);
		return this;
	}
	
	public  DistrictPage enterDistrictcode(String Code) {
		type(DistrictCodeField , Code);
		return this;
	}
	
	public DistrictPage enterLatitude(String Latitude) {
		type(LattitudeField , Latitude);
		return this;
	}
	
	public DistrictPage enterLongitude(String Longitude) {
		type(LangitudeField , Longitude);
		return this;
	}
	
	public DistrictPage clicksavebutton() {
		Click(Savebutton);
		return this;
	}

	public DistrictPage clickclearbutton() {
		Click(Clearbutton);
		return this;
	}

	public DistrictPage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public DistrictPage clickEditicon() {
		Click(Editicon);
		return this;
	}

	public DistrictPage clickBlock() {
		Click(Blockicon);
		return this;
	}

	public DistrictPage clickUnblock() {
		Click(Unblockicon);
		return this;
	}

	public DistrictPage clickExcelExport() {
		Click(ExcelExport);
		return this;
	}

	public DistrictPage clickPDFExport() {
		Click(PDFExport);
		return this;
	}
	
	public DistrictPage enterstatussearch(String search) {
		type(Statussearch , search);
		return this;
	}
	
	//===WorkFlow Method ========
	
	public DistrictPage NavigateDistrictPage() {
		return ClickEnterpriseMenu()
				.ClickEnterpriseSubMenu()
				.ClickDistrict();
	}
	
	public DistrictPage CheckDistrctLableName() {
		return Clickcreatenewbutton();
	}
	
	public DistrictPage CheckValidatorMSG() {
		return Clickcreatenewbutton()
				.clicksavebutton();
	}
	
	public DistrictPage CheckClearFunctionalility() {
		 Clickcreatenewbutton()
				.enterDistrictname("Test@#123")
				.enterDistrictMarname("टेस्ट जिल्हा @#123")
				.clickStateDropdown()
				.selectState("Mahara")
				.enterDistrictcode("1234")
				.enterLatitude("12.456")
				.enterLongitude("23.543")
				.clickclearbutton()
				.handelalert(true);
		          return this;
	}
	
	public String CheckCreateNewDistrict() {
		   String districtName = "Test@#123";           // Same name you are entering
		    String districtMarathiName = "टेस्ट जिल्हा @#123";
		Clickcreatenewbutton()
		.enterDistrictname(districtName)
		.enterDistrictMarname(districtMarathiName)
		.clickStateDropdown()
		.selectState("Mahara")
		.enterDistrictcode("1234")
		.enterLatitude("12.456")
		.enterLongitude("23.543")
		.clicksavebutton()
		.handelalert(true);
          return districtName;
	}
	
	
	public DistrictPage CheckgridSearchfield() {
		return SearchInGrid("Pune");
	}
	
	public DistrictPage CheckStatussearch() {
		 clickBlock()
	     . handelalert(true);
		 return this;
	}
	
	public DistrictPage BlockDistrict() throws InterruptedException {
		Thread.sleep(500);
	 clickBlock()
	 .handelalert(true);
	 return this;
		 
		
		 
	}
	
	public DistrictPage UnblockDistrict() throws InterruptedException {
		Thread.sleep(500);
		driver.navigate().refresh();
		 clickUnblock()
		. handelalert(true);
		 return this;
	}
	
	
	

}
