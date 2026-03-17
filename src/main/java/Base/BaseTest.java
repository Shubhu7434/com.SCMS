package Base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	public WebDriver driver;

	public void Setup() {

		driver = new ChromeDriver();
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

	/*
	 * @BeforeMethod public void setUp(ITestContext context) {
	 * 
	 * driver = new ChromeDriver();
	 * 
	 * // 🔥 VERY IMPORTANT LINE context.setAttribute("driver", driver); }
	 */

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
