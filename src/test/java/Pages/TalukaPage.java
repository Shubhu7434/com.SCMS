package Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TalukaPage {
	
	private WebDriver driver ;
	private WebDriverWait wait ;
	
	public TalukaPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver , this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//======Locators======
	
	@FindBy(xpath = "(//a[contains(text(),' Enterprise')])[1]")
	private WebElement Enterprisemenu;
	
	@FindBy(xpath = "(//a[contains(text(),' Enterprise')])[2]")
	private WebElement EnterpriseSubmenu;
	
	@FindBy(xpath = "//a[@href='/Taluka/index']")
	private WebElement Talukalink;
	
	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;
	
	@FindBy(xpath = "//input[@name='TalName']")
	private WebElement TalukaField;
	
	@FindBy(xpath = "//input[@name='TalNameMar']")
	private WebElement TalukaMarathiField;
	
	@FindBy(xpath = "//input[@name='lattitude']")
	private WebElement lattitudeField;
	
	@FindBy(xpath = "//input[@name='langitude']")
	private WebElement LongitudeField;
	
	@FindBy(id = "select2-DistName-container")
	private WebElement DistrictDropdown;
	
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
	
	//======Utility Methods ===========
	
	private void Click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	private void type(WebElement elemnt , String text) {
		wait.until(ExpectedConditions.visibilityOf(elemnt)).clear();
		elemnt.sendKeys(text);
	}
	
	private String handelAlert(boolean accept) {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		String text = alt.getText().trim();
		if(accept) alt.accept();
		else alt.dismiss();
		return text;
	}
	
	//=========Action ============
	
     public TalukaPage Clickenterprisemenu() {
		Click(Enterprisemenu);
		return this;
	}
     
    public TalukaPage Clickenetrprisesubmenu() {
    	Click(EnterpriseSubmenu);
    	return this;
    }
    
    public TalukaPage ClickTalukalink() {
    	Click(Talukalink);
    	return this;
    }
    
  public TalukaPage Clickcreatenewbutton() {
	  Click(Createnewbutton);
	  return this;
  }
  
  public TalukaPage entertalukaname(String taluka) {
	  type(TalukaField , taluka);
	  return this;
  }
  
  public TalukaPage entermarathitaluka(String marathitaluka) {
	  type(TalukaMarathiField , marathitaluka);
	  return this;
  }
  
  public TalukaPage ClickDistrictdropdown() {
	  Click(DistrictDropdown);
	  return this;
  }
  
  public TalukaPage enterlattitude(String lattitude) {
	  type(lattitudeField , lattitude);
	  return this;
  }
  
  public TalukaPage enterlongitude(String longitude) {
	  type(LongitudeField , longitude);
	  return this;
  }
  
  public TalukaPage clicksavebutton() {
		Click(Savebutton);
		return this;
	}

	public TalukaPage clickclearbutton() {
		Click(Clearbutton);
		return this;
	}

	public TalukaPage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public TalukaPage clickEditicon() {
		Click(Editicon);
		return this;
	}

	public TalukaPage clickBlock() {
		Click(Blockicon);
		return this;
	}

	public TalukaPage clickUnblock() {
		Click(Unblockicon);
		return this;
	}

	public TalukaPage clickExcelExport() {
		Click(ExcelExport);
		return this;
	}

	public TalukaPage clickPDFExport() {
		Click(PDFExport);
		return this;
	}
	
	public TalukaPage enterstatussearch(String search) {
		type(Statussearch , search);
		return this;
	}
	
	public TalukaPage Clickviewicon() {
		Click(Viewicon);
		return this;
	}
	
	//======Workflow Methods =========
	
	public TalukaPage NavigatePage() {
		return Clickenterprisemenu()
				.Clickenetrprisesubmenu()
				.ClickTalukalink();
	}
	
	
	public TalukaPage CheckValidationMSG() {
		return Clickcreatenewbutton()
				.clicksavebutton();
	}
	
	public TalukaPage CheckDistrictDropdownExpand() {
		return Clickcreatenewbutton()
				.ClickDistrictdropdown();
	}
	
	public TalukaPage CheckClearFunctionality() {
		Clickcreatenewbutton()
		.entertalukaname("Test Taluka")
		.entermarathitaluka("abc123")
		.clickclearbutton();
		handelAlert(true);
		return this;
	}
	
	public TalukaPage CheckEditFunctionality() {
		clickEditicon()
		.entermarathitaluka("1234")
		.clicksavebutton()
		.handelAlert(true);
		return this;
	}
	
	public TalukaPage CheckBlockTaluka() {
		clickBlock()
		.handelAlert(true);
		return this;
	}
	
	public TalukaPage CheckViewPageStatus() {
		clickBlock()
		.handelAlert(true);
		handelAlert(true);
		return Clickviewicon();
	
	}
	
	public TalukaPage CheckGridsearch() {
		return SearchInGrid("Baramati");
	}
	
	

}
