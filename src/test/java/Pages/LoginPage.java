package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver ;
	private WebDriverWait wait ;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver , this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//======Locators ==========
	
	@FindBy(id = "Username")
	private WebElement Username;
	
	@FindBy(id = "Password")
	private WebElement Password;
	
	@FindBy(xpath = "//button[contains(text(),'Log In')]")
	private WebElement Loginbutton;
	
	//====Actions========
	
	public LoginPage enterUsername(String username) {
		Username.sendKeys(username);
		return this;
	}
	
	public LoginPage enterPAssword(String password) {
		Password.sendKeys(password);
		return this;
	}
	
	public LoginPage clickLoginButton() {
		Loginbutton.click();
		return this;
	}
	
	
	//======WorkFlow Methods ========
	
	public LoginPage Login(String username , String password) {
		return enterUsername(username)
				.enterPAssword(password)
				.clickLoginButton();
				
	}
        

}
