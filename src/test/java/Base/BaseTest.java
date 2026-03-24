package Base;

import java.awt.Desktop;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import Utils.ExtentReportManager;

public class BaseTest {

	public WebDriver driver;
	public WebDriverWait wait;
	public static ExtentReports extent;

	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportInstance();
	}

	public void Setup() {

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	public void Login() {
		driver.get("http://192.168.18.33:8019/");
		driver.findElement(By.id("Username")).sendKeys("rcplb");
		driver.findElement(By.id("Password")).sendKeys("Rcpl@cmserp#1");
		driver.findElement(By.xpath("//button[contains(text(),'Log In')]")).click();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String waitForAlertAndGetText() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		return driver.switchTo().alert().getText().trim();
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	@AfterSuite
	public void tearDownReport() {
		extent.flush();

		try {
			File reportfile = new File(ExtentReportManager.getReportPath());
			if (reportfile.exists()) {
				Desktop.getDesktop().browse(reportfile.toURI());
				System.out.println("Extent Reprot Open Sutomaticcaly Successfully");
			} else {
				System.out.println("Extent Reprot Not Open Sutomaticcaly Successfully");
			}
		} catch (Exception e) {
			System.out.println("Extent Reprot Not Open Sutomaticcaly Successfully");
		}
	}

}
