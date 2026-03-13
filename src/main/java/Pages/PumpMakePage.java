package Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PumpMakePage {
	
	private WebDriver driver ;
	private WebDriverWait wait ;
	
	public PumpMakePage(WebDriver driver) {
		this.driver = driver ;
		PageFactory.initElements( driver , this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//===Locators======
	
	@FindBy(xpath = "//a[contains(text(),' Fuel Management')]")
	private WebElement Fuelmanagementmenu;
	
	@FindBy(xpath = "//a[contains(text(),' Fuel Pump')]")
	private WebElement FuelPumpmenu;
	
	@FindBy(xpath = "//a[@href='/PumpMake/index']")
	private WebElement Pumpmakelink;
	
	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;
	
	@FindBy(xpath = "//input[@name='PumpCompany']")
	private WebElement PumpMakeField;
	
	@FindBy(xpath = "//input[@name='PumpCompanyMarathi']")
	private WebElement MarathiPumpmakeField;
	
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
	
	//=====Utility Methods ====
	
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
		if(accept) alt.accept();
		else alt.dismiss();
		return text;
	}
	
	//=====Actions ======
	
	public PumpMakePage ClickFuelmanagementmenu() {
		wait.until(ExpectedConditions.elementToBeClickable(Fuelmanagementmenu)).click();
		return this;
	}
	
	public PumpMakePage ClickFuelpumpmenu() {
		wait.until(ExpectedConditions.elementToBeClickable(FuelPumpmenu)).click();
		return this;
	}
	
	public PumpMakePage ClickPumpmakelink() {
		Click(Pumpmakelink);
		return this;
	}
	
	public PumpMakePage Clickcreatenewbutton() {
		Click(Createnewbutton);
		return this;
	}
	
	public PumpMakePage enterpumpmake(String text) {
		type(PumpMakeField , text);
		return this;
	}
	
	public PumpMakePage enterMarathipumpmake(String text) {
		type(MarathiPumpmakeField , text);
		return this;
		
	}
	
	public PumpMakePage clicksavebutton() {
		Click(Savebutton);
		return this;
	}

	public PumpMakePage clickclearbutton() {
		Click(Clearbutton);
		return this;
	}

	public PumpMakePage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public PumpMakePage clickEditicon() {
		Click(Editicon);
		return this;
	}

	public PumpMakePage clickBlock() {
		Click(Blockicon);
		return this;
	}

	public PumpMakePage clickUnblock() {
		Click(Unblockicon);
		return this;
	}

	public PumpMakePage clickExcelExport() {
		Click(ExcelExport);
		return this;
	}

	public PumpMakePage clickPDFExport() {
		Click(PDFExport);
		return this;
	}
	
	public PumpMakePage enterstatussearch(String search) {
		type(Statussearch , search);
		return this;
	}
	
	public PumpMakePage Clickviewicon() {
		Click(Viewicon);
		return this;
	}
	
	
	//===Methods=====
	
	public PumpMakePage NavigatePage() {
		return ClickFuelmanagementmenu()
				.ClickFuelpumpmenu()
				.ClickPumpmakelink();
	}
	
	public PumpMakePage CheckValidatorMSG() {
		return Clickcreatenewbutton()
				.clicksavebutton();
	}
	
     public PumpMakePage checkCreatenewPumpMake(String text , String Mtext) {
    	  Clickcreatenewbutton()
    			 .enterpumpmake(text)
    			 .enterMarathipumpmake(Mtext)
    			 .clicksavebutton();
    	         handelAlert(true);
    	         return this;
     }
     
     
     public PumpMakePage checkgridTableSearch(String text) {
    	 return SearchInGrid(text);
     }
     
     public PumpMakePage checkBlockFunctionlaity() {
    	  clickBlock();
    	  handelAlert(true);
    	  return this;
     }
     
     public PumpMakePage checkUnblockFunctionality() {
    	 clickUnblock();
    	 handelAlert(true);
    	 return this;
     }
	
	
	

}
