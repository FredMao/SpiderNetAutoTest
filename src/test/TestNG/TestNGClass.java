package TestNG;


import static org.junit.Assert.assertEquals;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class TestNG {

	WebDriver driver;

	@DataProvider
	public Object[][] testData() {
		return new Object[][] { { "1", "1", "2" }, { "1", "2", "3" },
				{ "1", "3", "4" }, { "2", "3", "5" } };
	}

	@BeforeTest
	public void setUp() {
		driver = new FirefoxDriver();
	}

	@Test(dataProvider = "testData")
	public void testCalculator(String n1, String n2, String n3) {

		driver.get("C:\\cc.html");

		WebElement num1 = driver.findElement(By.id("num1"));

		num1.sendKeys(n1);

		WebElement num2 = driver.findElement(By.id("num2"));

		num2.sendKeys(n2);

		WebElement calculateButton = driver.findElement(By.id("b1"));

		calculateButton.click();

		WebElement num3 = driver.findElement(By.id("num3"));

		assertEquals(n3, num3.getAttribute("value"));

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
