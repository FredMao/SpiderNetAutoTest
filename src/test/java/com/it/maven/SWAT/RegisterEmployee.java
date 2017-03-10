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
import org.openqa.selenium.support.ui.Select;

import com.spidernet.autotest.util.ConfigFile;


public class RegisterEmployee {
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
	public void Register() throws IOException {
		
		if (Login()){
			//Click on the register menu 
			driver.findElement(By.xpath(ConfigFile.getElementProperties("registerMenu"))).click();
			
			
			//Put a Implicit wait, will wait for 5 seconds before verification
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			
		    String currentURL2 = driver.getCurrentUrl();
		    ConfigFile.appendContentToLogFile("Opening register employee page: ");
			ConfigFile.appendContentToLogFile(currentURL2);
		    
		    
		    //Verify if register page is opened successfully
			if (isElementPresent(By.xpath(ConfigFile.getElementProperties("registerHeader"))))
			{
				if (driver.findElement(By.xpath(ConfigFile.getElementProperties("registerHeader"))).getText().equals("新员工注册"))
				{
					ConfigFile.appendContentToLogFile("Setp3: Register employee page is opened successfully");					
					
					//Find the element of ER number and input number
					driver.findElement(By.xpath(ConfigFile.getElementProperties("erNumber"))).sendKeys(ConfigFile.getValueProperties("erNumber"));
					
					ConfigFile.appendContentToLogFile("ER Number:");
					ConfigFile.appendContentToLogFile(ConfigFile.getValueProperties("erNumber"));
					
					//Find the element of HR number and input number
					driver.findElement(By.xpath(ConfigFile.getElementProperties("hrNumber"))).sendKeys(ConfigFile.getValueProperties("hrNumber"));
					
					ConfigFile.appendContentToLogFile("HR Number:");
					ConfigFile.appendContentToLogFile(ConfigFile.getValueProperties("hrNumber"));
					
					//Find the element of Chinese name and input name
					driver.findElement(By.xpath(ConfigFile.getElementProperties("chineseName"))).sendKeys(ConfigFile.getValueProperties("chineseName"));
					
					ConfigFile.appendContentToLogFile("Chinese name:");
					ConfigFile.appendContentToLogFile(ConfigFile.getValueProperties("chineseName"));
					
					//Find the element of English Name and input name
					driver.findElement(By.xpath(ConfigFile.getElementProperties("englishName"))).sendKeys(ConfigFile.getValueProperties("englishName"));
					
					ConfigFile.appendContentToLogFile("English Name:");
					ConfigFile.appendContentToLogFile(ConfigFile.getValueProperties("englishName"));
					
					//Find the element of Employee type and input 
					Select empType = new Select(driver.findElement(By.xpath(ConfigFile.getElementProperties("empType"))));
					empType.selectByVisibleText(ConfigFile.getValueProperties("empType"));
					
					ConfigFile.appendContentToLogFile("Employee type:");
					ConfigFile.appendContentToLogFile(ConfigFile.getValueProperties("empType"));
					
					//Find the element of Employee level and input 
					Select empLevel = new Select(driver.findElement(By.xpath(ConfigFile.getElementProperties("empLevel"))));
					empLevel.selectByVisibleText(ConfigFile.getValueProperties("empLevel"));
					
					ConfigFile.appendContentToLogFile("Employee level:");
					ConfigFile.appendContentToLogFile(ConfigFile.getValueProperties("empLevel"));
					
					//Find the element of Project name and input 
					Select projectName = new Select(driver.findElement(By.xpath(ConfigFile.getElementProperties("projectName"))));
					projectName.selectByVisibleText(ConfigFile.getValueProperties("projectName"));
					
					ConfigFile.appendContentToLogFile("Project name:");
					ConfigFile.appendContentToLogFile(ConfigFile.getValueProperties("projectName"));
					
					//Put a Implicit wait, will wait for 5 seconds before verification
					driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
					
					if (driver.findElement(By.xpath(ConfigFile.getElementProperties("map"))).isDisplayed())
					{
						ConfigFile.appendContentToLogFile("Setp4: Capability map is activated successfully.");
						
						//Put a Implicit wait, will wait for 5 seconds before verification
						driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
						
						//Click the submit button of capability map 
						driver.findElement(By.xpath(ConfigFile.getElementProperties("mapSubmit"))).click();
						ConfigFile.appendContentToLogFile("Setp5: Submit capability map.");
															
						//Click the Register button 
						driver.findElement(By.xpath(ConfigFile.getElementProperties("registerSubmitButton"))).submit();
						
						ConfigFile.appendContentToLogFile("Step6: Register the new employee.");
						ConfigFile.appendContentToLogFile(driver.findElement(By.xpath(ConfigFile.getElementProperties("registerAlert"))).getText());
						
					}
					else
					{
						ConfigFile.appendContentToLogFile("Setp4: Capability map is not activated.");	
					}
				}
				else
				{
					ConfigFile.appendContentToLogFile("Setp3: Openning Register employee page failed. Current page is :");	
					ConfigFile.appendContentToLogFile(driver.findElement(By.xpath(ConfigFile.getElementProperties("registerHeader"))).getText());
				}
				
			}
			else
			{
				ConfigFile.appendContentToLogFile("Setp3: Openning Register employee page failed");
			}
			
		}

	}
	
	public boolean  Login() throws IOException {
				
		driver.navigate().to(ConfigFile.getConfigProperties("URL"));
		
		Date currentDate = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    ConfigFile.appendContentToLogFile("");
	    ConfigFile.appendContentToLogFile("********************************************");
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
			
			//Put a Implicit wait, will wait for 5 seconds before verification
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			
			//Verify if logon successfully
	 
			
		    if (isElementPresent(By.xpath(ConfigFile.getElementProperties("userinfo")))){
		    	
		    	WebElement logoelement2 = driver.findElement(By.xpath(ConfigFile.getElementProperties("userinfo")));
		    	
		    	if(logoelement2.getText().equals(ConfigFile.getValueProperties("userinfo")))
				{
				    ConfigFile.appendContentToLogFile("Step2: Logged on the SpiderNet successfully");
				    return true; 
				}
		    	else
				{
				    ConfigFile.appendContentToLogFile("Step2: Logged on the SpiderNet but with incorrect user:");
				    ConfigFile.appendContentToLogFile(logoelement2.getText());
				    return false;
				}
		    	
		    }
		    else
		    {
		    	ConfigFile.appendContentToLogFile("Step2: Logining the SpiderNet Failed");
		    	return false;
		    } 
		}
		else
		{
			ConfigFile.appendContentToLogFile("Setp1:Opening the logon page Failed");
			return false;
		}
		
	}
	
	private boolean isElementPresent(By by) { 
    	try 
    	{ 
    		driver.findElement(by); 
    		return true; 
    	} 
    	catch (Exception e) 
    	{ 
    		e.printStackTrace(); 
    		return false; 
    	} 
    }
	
}
