package com.it.maven.SWAT;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.spidernet.autotest.*;
import com.spidernet.autotest.util.ConfigFile;
import org.testng.asserts.*;

public class Login {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty(ConfigFile.getConfigProperties("driver"),ConfigFile.getConfigProperties("driverPath"));
		
		WebDriver driver = new FirefoxDriver();
		
		//Put a Implicit wait, will wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//Launch website
		driver.navigate().to(ConfigFile.getConfigProperties("URL"));
		WebElement logoelement1 = driver.findElement(By.xpath(ConfigFile.getElementProperties("logonButton")));
		if(logoelement1.getText().equals("登录"))
		{
			ConfigFile.appendContentToLogFile("Setp1: Open the logo page successfully");
		}
		else
		{
			ConfigFile.appendContentToLogFile("Setp1:Open the logo page is Fail");
		}
		
		//Maximize the browser
		driver.manage().window().maximize();
		
		//Find the element of name and put into it
		driver.findElement(By.xpath(ConfigFile.getElementProperties("username"))).sendKeys(ConfigFile.getValueProperties("username"));
		
		//Find the element of password and put into it.
		driver.findElement(By.xpath(ConfigFile.getElementProperties("password"))).sendKeys(ConfigFile.getValueProperties("password"));
		
		//Click on the logon button
		driver.findElement(By.xpath(ConfigFile.getElementProperties("logonButton"))).click();
		
		
		//Verify logon successfully
	    WebElement logoelement2 = driver.findElement(By.xpath(ConfigFile.getElementProperties("userinfo")));
	    if(logoelement2.getText().equals(ConfigFile.getValueProperties("userinfo")))
	    {
	    	ConfigFile.appendContentToLogFile("Setp2: Logon the SpiderNet successfully");
	    }
	    else
	    {
	    	ConfigFile.appendContentToLogFile("Set2: Logon the SpiderNet Fail");
	    }
				
		//Close the Browser.
	    driver.close(); 		
	}

}
