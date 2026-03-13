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

public class FuelPumpPage {
	
	WebDriver driver ;
	WebDriverWait wait ;
	
	public FuelPumpPage ( WebDriver driver) {
		this.driver = driver ;
		PageFactory.initElements(driver , this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	
	
	//===Locators============
		@FindBy(xpath = "//a[contains(text(),' Fuel Management')]")
		private WebElement Fuelmanagementmenu;
		
		@FindBy(xpath = "//a[contains(text(),' Fuel Pump')]")
		private WebElement FuelPumpmenu;
		
		@FindBy(xpath = "//a[@href='/FuelPumpMaster/index']")
		private WebElement Fuelpumplink;
		
		@FindBy(id = "btncreatenew")
		private WebElement Createnewbutton;
		
		@FindBy(name = "PumpName")
		private WebElement Pumpnamefield;
		
		@FindBy(name = "PumpNameMarathi")
		private WebElement Marathipumpnamefield;
		
		@FindBy(id = "select2-PumpMake-container")
		private WebElement Dropdownpumpmake;
		
		@FindBy(xpath = "//input[@class='select2-search__field']")
		private  WebElement Selectpumpmake;
		
		@FindBy(name = "PumpAddress")
		private WebElement Addressfield;
		
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
		
		
		//=== Utility Methods ====
		
		private void Click(WebElement element) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}
		
		private void type( WebElement element , String text) {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(text);
		}
		
		private void typeAndEnter(WebElement element , String text) {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(text , Keys.ENTER);
		}
		
		private String  handelAlert(boolean accept) {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alt = driver.switchTo().alert();
			 String text = alt.getText().trim();
			 if(accept) alt.accept();
			 else alt.dismiss();
			 return text;
		}
		
		//=======Actions Method ======
		
		public FuelPumpPage clickFuelmanagementmenu() {
			Click(Fuelmanagementmenu);
			return this;
		}
		
		public FuelPumpPage clickFuelpumpmenu() {
			Click(FuelPumpmenu);
			return this;
		}
		
		
		
		
		public FuelPumpPage clickFuelpumplink() {
			Click(Fuelpumplink);
			return this;
			
		}
		
		public FuelPumpPage clickCreatenewbutton() {
			Click(Createnewbutton);
			return this;
		}
		
		public FuelPumpPage enterPumpName(String text) {
			type(Pumpnamefield, text);
			return this;
		}
		
		public FuelPumpPage entermarathiPumpName(String text) {
			type(Marathipumpnamefield, text);
			return this;
		}
		
		public FuelPumpPage clickPumpmakedropdown() {
			Click(Dropdownpumpmake);
			return this;
		}
		
		public FuelPumpPage selectPumpMake(String text) {
			typeAndEnter(Selectpumpmake, text);
			return this;
		}
		
		public FuelPumpPage enterAddress(String text) {
			type(Addressfield, text);
			return this;
		}
		
		public FuelPumpPage clickSaveButton() {
			Click(Savebutton);
			return this;
		}
		
		public FuelPumpPage clickClearButton() {
			Click(Clearbutton);
			return this;
		}
		
		public FuelPumpPage SearchInGrid(String search) {
			type(Gridsearch, search);
			return this;
		}

		public FuelPumpPage clickEditicon() {
			Click(Editicon);
			return this;
		}

		public FuelPumpPage clickBlock() {
			Click(Blockicon);
			return this;
		}

		public FuelPumpPage clickUnblock() {
			Click(Unblockicon);
			return this;
		}
		
		//===WorkFlow Methods =====
		
		public FuelPumpPage NavigatePage() {
			return clickFuelmanagementmenu()
					.clickFuelpumpmenu()
					.clickFuelpumplink();
		}
		
		public FuelPumpPage checkvalidatorMessage() {
			return clickCreatenewbutton()
					.clickSaveButton();
		}
		
		public FuelPumpPage checkCreateFuelPump() {
			clickCreatenewbutton();
			enterPumpName("Shakari Pump");
			entermarathiPumpName("सहकारी पम्प");
			clickPumpmakedropdown();
			selectPumpMake("Cell");
			enterAddress("Baramati");
			clickSaveButton();
			handelAlert(true);
			return this;
		}
		
		public FuelPumpPage checkGridSearch() {
			return SearchInGrid("Shakari Pump");
		}
		
		public FuelPumpPage checkBlockFunctionality() {
		        clickBlock();
		        handelAlert(true);
		        return this;
		}
		
		public FuelPumpPage checkunblockFunctionlity() {
			 clickUnblock();
			 handelAlert(true);
			 return this;
		}

		
		
		
		
	
	
	

}
