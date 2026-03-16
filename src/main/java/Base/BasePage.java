package Base;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// Common Click Method

	protected void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	protected void type(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(text);
	}

	protected void typeAndEnter(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		element.sendKeys(text, Keys.ENTER);

	}

	protected String handelAlert(boolean accept) {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt = driver.switchTo().alert();
		String text = alt.getText().trim();
		if (accept)
			alt.accept();
		else
			alt.dismiss();
		return text;
	}

}
