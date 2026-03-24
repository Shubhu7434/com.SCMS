package Base;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class BasePage<T> {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ===== COMMON METHODS =====

    public T click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return (T) this;
    }

    public T type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        return (T) this;
    }
    
    public T typeAndEnter(WebElement element , String text) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    	element.click();
    	element.sendKeys(text,Keys.ENTER);
    	return (T) this;
    }

    public T jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        return (T) this;
    }

    public String handelAlert(boolean accept) {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alt = driver.switchTo().alert();
        String text = alt.getText().trim();
        if (accept) alt.accept();
        else alt.dismiss();
        return text;
    }

    // ===== COMMON LOCATORS =====

    @FindBy(xpath = "(//a[contains(text(),'Agriculture')])[1]")
    private WebElement Agriculturemenu;

    @FindBy(xpath = "//a[@href='/CanePlantation/index']")
    private WebElement Caneplantationmenu;

    @FindBy(id = "btncreatenew")
    private WebElement Createnewbutton;

    @FindBy(id = "btnSave")
    private WebElement Savebutton;

    @FindBy(id = "searchTableList")
    private WebElement Gridsearch;

    @FindBy(xpath = "(//i[@title='Edit'])[1]")
    private WebElement Editicon;

    @FindBy(xpath = "(//i[@title='View'])[1]")
    private WebElement Viewicon;

    @FindBy(xpath = "(//i[@title='Block'])[1]")
    private WebElement Blockicon;

    @FindBy(xpath = "(//i[@title='Unblock'])[1]")
    private WebElement Unblockicon;

    // ===== COMMON ACTIONS =====

    public T clickAgriculturemenu() {
        return click(Agriculturemenu);
    }

    public T clickCanePlantationMenu() {
        return click(Caneplantationmenu);
    }

    public T clickCreateNewButton() {
        return click(Createnewbutton);
    }

    public T clickSavebutton() {
        return click(Savebutton);
    }

    public T SearchInGrid(String search) {
        return type(Gridsearch, search);
    }

    public T clickEditicon() {
        return jsClick(Editicon);
    }

    public T clickViewicon() {
        return jsClick(Viewicon);
    }

    public T clickBlock() {
        return jsClick(Blockicon);
    }

    public T clickUnblock() {
        return jsClick(Unblockicon);
    }
}