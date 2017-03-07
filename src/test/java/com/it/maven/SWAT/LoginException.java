package com.it.maven.SWAT;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.spidernet.autotest.util.ConfigFile;


public class LoginException {
	static WebDriver driver = null;
	
	@BeforeClass
	public static void setup() throws IOException {
		System.setProperty(ConfigFile.getConfigProperties("driver"),ConfigFile.getConfigProperties("driverPath"));
		driver = new FirefoxDriver();
		//Put a Implicit wait, will wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	@AfterClass
	public static void tearDown() {
		//Launch website
	    driver.close(); 		
	}
	
	
	@Test()
	public void openLoginPage() throws IOException {
				
		driver.navigate().to(ConfigFile.getConfigProperties("URL"));
		
		Date currentDate = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
	    ConfigFile.appendContentToLogFile(sdf.format(currentDate));
		
		String currentURL1 = driver.getCurrentUrl();
		ConfigFile.appendContentToLogFile("Opening logon page with URL: ");
		ConfigFile.appendContentToLogFile(currentURL1);
		
		if (isElementPresent(By.xpath(ConfigFile.getElementProperties("logonButton"))))
		{
			WebElement logoelement1 = driver.findElement(By.xpath(ConfigFile.getElementProperties("logonButton")));
			
			if(logoelement1.getText().equals("登录"))
			{
				ConfigFile.appendContentToLogFile("Setp1: The logon page is opened successfully");
			}
			
			//Maximize the browser
			driver.manage().window().maximize();
			
			//Find the element of name and put into it
			driver.findElement(By.xpath(ConfigFile.getElementProperties("username"))).sendKeys(ConfigFile.getValueProperties("username"));
			
			ConfigFile.appendContentToLogFile("Testing with username:");
			ConfigFile.appendContentToLogFile(ConfigFile.getValueProperties("username"));
			
			//Find the element of password and put into it.
			driver.findElement(By.xpath(ConfigFile.getElementProperties("password"))).sendKeys(ConfigFile.getValueProperties("password"));
			
			ConfigFile.appendContentToLogFile("Testing with password:");
			ConfigFile.appendContentToLogFile(ConfigFile.getValueProperties("password"));
			
			//Click on the logon button
			driver.findElement(By.xpath(ConfigFile.getElementProperties("logonButton"))).click();
			
			//Put a Implicit wait, will wait for 10 seconds before throwing verification
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			
			//Verify if logon successfully
		    String currentURL2 = driver.getCurrentUrl();
		 
			
		    if (isElementPresent(By.xpath(ConfigFile.getElementProperties("userinfo")))){
		    	
		    	WebElement logoelement2 = driver.findElement(By.xpath(ConfigFile.getElementProperties("userinfo")));
		    	
		    	if(logoelement2.getText().equals(ConfigFile.getValueProperties("userinfo")))
				{
				    ConfigFile.appendContentToLogFile("Step2: Logged on the SpiderNet successfully");
				}
		    	else
				{
				    ConfigFile.appendContentToLogFile("Step2: Logged on the SpiderNet but with incorrect user:");
				    ConfigFile.appendContentToLogFile(logoelement2.getText());
				}
		    	
		    }
		    else
		    {
		    	ConfigFile.appendContentToLogFile("Step2: Logining the SpiderNet Failed");
			   
		    } 
		}
		else
		{
			ConfigFile.appendContentToLogFile("Setp1:Opening the logon page Failed");
		}
		
	}
	
	private boolean isElementPresent(By by) { 
    	try { 
    		driver.findElement(by); 
    		return true; 
    	} catch (Exception e) { 
    		e.printStackTrace(); 
    		return false; 
    	} 
    }
	
}
