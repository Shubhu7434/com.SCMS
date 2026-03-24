package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.type.CollectionLikeType;

import Base.BasePage;

public class VillageWiseHarvestingTopLimitPage extends BasePage<VillageWiseHarvestingTopLimitPage>{
	
	WebDriver driver ;
	 WebDriverWait wait ;
	 
	 public VillageWiseHarvestingTopLimitPage (WebDriver driver) {
		  super(driver);
		  PageFactory.initElements( driver , this);
		  wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	 }
	 
	 //===Locators======
	 
	 @FindBy(xpath = " (//a[contains(text(),'Agriculture')])[1]")
		private WebElement Agriculturemenu;
		
		@FindBy(xpath = "//a[@href='/CanePlantation/index']")
		private WebElement Caneplantationmenu;
		
		@FindBy(xpath = "//a[contains(normalize-space(),'Village Wise Harvesting Top Limit')]")
		private WebElement HarvestTopLimitLink;
		
		@FindBy(id = "btncreatenew")
		private WebElement Createnewbutton;
		
		@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[1]")
		private WebElement GatDropdown;
		
		@FindBy(xpath = "//input[@class='select2-search__field']")
		private WebElement SearchDropdownption;
		
		@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[2]")
		private WebElement VillageDropdown;
		
		@FindBy(id = "btnShow")
		private WebElement Showbutton;
		
		@FindBy(id = "searchTableList1")
		private WebElement CreatedSearchfield;
		
		@FindBy(id = "HarvestToLimit")
		private WebElement HarvestTopLimitField;
		
		@FindBy(id = "chkApplyAllVillages")
		private WebElement ApplyToAllcheckbox;
		
		@FindBy(id = "selectAllVillageCheckbox")
		private WebElement GridSelectAllCheckbox;
		
		@FindBy(id = "btnSave")
		private WebElement Savebutton;

		@FindBy(id = "btnClear")
		private WebElement Clearbutton;

		@FindBy(id = "btncloseaddupdatepup")
		private WebElement Closebutton;

		@FindBy(id = "searchTableList")
		private WebElement Gridsearch;

		@FindBy(xpath = "//button[@title='Excel']")
		private WebElement ExcelExport;

		@FindBy(xpath = "//button[@title='PDF']")
		private WebElement PDFExport;
		
		//===Action======
		
		public VillageWiseHarvestingTopLimitPage clickAgriculturemenu() {
			click(Agriculturemenu);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage clickCanemanagementmenu() {
			click(Caneplantationmenu);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage clickHarvesttopLimitLink() {
			click(HarvestTopLimitLink);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage clickCreatenewbutton() {
			click(Createnewbutton);
			return this;
		}
		
		
		public VillageWiseHarvestingTopLimitPage clickGatDropdown() {
			click(GatDropdown);
			return this;
		}
		
		
		public VillageWiseHarvestingTopLimitPage selectGat(String text) {
			typeAndEnter(SearchDropdownption, text);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage clickVillageDropdown() {
			click(VillageDropdown);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage selectVillage(String text) {
			typeAndEnter(SearchDropdownption, text);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage clickShowbutton() {
			click(Showbutton);
			return this;
		}

		public VillageWiseHarvestingTopLimitPage enterSearchcreatepage(String text) {
			type(CreatedSearchfield, text);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage enterHarvestTopLimit(String text) {
			type(HarvestTopLimitField, text);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage clickApplyAllCheckbox() {
			click(ApplyToAllcheckbox);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage clickGridSelectallCheckbox() {
			click(GridSelectAllCheckbox);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage clickAssignbutton() {
			click(Savebutton);
			return this;
		}

		public VillageWiseHarvestingTopLimitPage clickClearButton() {
			click(Clearbutton);
			return this;
		}

		public VillageWiseHarvestingTopLimitPage SearchInGrid(String search) {
			type(Gridsearch, search);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage clickExcelExport() {
			click(ExcelExport);
			return this;
		}
		
		public VillageWiseHarvestingTopLimitPage clickPDFExport() {
			click(PDFExport);
			return this;
		}
		
		
		//=====Workflow Methods =====
		
		public VillageWiseHarvestingTopLimitPage checkNavigationPage() {
		 return clickAgriculturemenu()
				 .clickCanemanagementmenu()
				 .clickHarvesttopLimitLink();
		}
		
		public VillageWiseHarvestingTopLimitPage checkGatDropdownOption() {
			return clickCreatenewbutton()
					.clickGatDropdown();
		}
		
		public VillageWiseHarvestingTopLimitPage checkGatDropdownValidatorMSgAlignment() {
			return clickCreatenewbutton()
					.clickAssignbutton();
		}
		
		public VillageWiseHarvestingTopLimitPage checkApplyAllcheckboxselect() {
			return clickCreatenewbutton()
					.clickApplyAllCheckbox();
		}
		
		public VillageWiseHarvestingTopLimitPage checkCreatePageSearchField() {
			return clickCreatenewbutton()
					.enterSearchcreatepage("test@123");
		}
		
		
		public VillageWiseHarvestingTopLimitPage checkGridSearchFunctionality() {
			return SearchInGrid("Bidar");
		}

}
