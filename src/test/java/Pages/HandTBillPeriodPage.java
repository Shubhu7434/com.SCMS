package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.BasePage;

public class HandTBillPeriodPage extends BasePage<HandTBillPeriodPage> {
	
	public HandTBillPeriodPage (WebDriver driver) {
		super(driver);
	}
    
	//===== Locators ===== 
	
	@FindBy(xpath = "//a[contains(normalize-space(),'H&T Bill Period')]")
	private WebElement HandTBillPeriodMenu;
	
	
}
