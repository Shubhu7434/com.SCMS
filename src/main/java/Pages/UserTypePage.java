package Pages;

import java.time.Duration;

import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.type.CollectionLikeType;

import Base.BasePage;

public class UserTypePage extends BasePage {
	
	WebDriverWait wait ;
	
	public UserTypePage (WebDriver driver) {
		super(driver);
		PageFactory.initElements( driver,this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//====Locators=======
	
	@FindBy(xpath = "//a[text()=' Administration']")
	private WebElement Administrtionmenu;
	
	@FindBy(xpath = "//a[@href='/UserManagement/index']")
	private WebElement Authenticationmenu;
	
	@FindBy(xpath = "//a[@href='/UserType/index']")
	private WebElement Usertypelink;
	
	@FindBy(id = "btncreatenew")
	private WebElement Createnewbutton;
	
	@FindBy(name =  "UserType")
	private WebElement UserTypeField;
	
	@FindBy(name =  "UserTypeMar")
	private WebElement UsertypeMarathiField;
	
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
	
	
	//===Actions=====
	public UserTypePage clickAdministrationmenu() {
		click(Administrtionmenu);
		return this;
	}
	
	public UserTypePage clickAuthenticationmenu() {
		click(Authenticationmenu);
		return this;
	}
	
	public UserTypePage clickUsertypelink() {
		click(Usertypelink);
		return this ;
	}
	
	public UserTypePage clickCreateNewButton() {
		click(Createnewbutton);
		return this;
	}
	
	public UserTypePage enterUsertype(String text) {
		type(UserTypeField, text);
		return this;
	}
	
	public UserTypePage enterUserTypeMarathi(String text) {
		type(UsertypeMarathiField, text);
		return this;
	}
	
	public UserTypePage clickSavebutton() {
		click(Savebutton);
		return this;
	}
	
	public UserTypePage clickClearButton() {
		click(Clearbutton);
		return this;
	}
	

	public UserTypePage SearchInGrid(String search) {
		type(Gridsearch, search);
		return this;
	}

	public UserTypePage clickEditicon() {
		click(Editicon);
		return this;
	}

	public UserTypePage clickBlock() {
		click(Blockicon);
		return this;
	}

	public UserTypePage clickUnblock() {
		click(Unblockicon);
		return this;
	}
	
	//=== Workflow Method===
	
	public UserTypePage checkNavigatePage() {
		return clickAdministrationmenu()
				.clickAuthenticationmenu()
				.clickUsertypelink();
	}
	
	public UserTypePage checkvalidatorMSG() {
		return  clickCreateNewButton()
				.clickSavebutton();
	}
	
	public UserTypePage checkCreateNewUserType() {
		clickCreateNewButton()
		.enterUsertype("Sales & Executive")
		.enterUserTypeMarathi("विक्री आणि कार्यकारी")
		.clickSavebutton();
		handelAlert(true);
         return this;
        
	}
	
	public UserTypePage checkGridSearchFunctionality() {
		return SearchInGrid("Sales");
	}
	
	
	public UserTypePage checkBlockFunctionality() {
	        clickBlock();
	        handelAlert(true);
	        return this;
	}
	
	public UserTypePage checkUnblockFunctionality() {
		clickUnblock();
		handelAlert(true);
		return this;
	}
	
	
	
	
	
	

}
