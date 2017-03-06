package com.it.maven.SWAT;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.spidernet.autotest.util.ConfigFile;



public class WebDriverDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setProperty(ConfigFile.getConfigProperties("driver"),ConfigFile.getConfigProperties("driverPath"));
		
		WebDriver driver = new FirefoxDriver();

	    //Puts a Implicit wait, Will wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	    //Launch website 
		driver.navigate().to("http://www.calculator.net/");
		
		//Maximize the browser
		driver.manage().window().maximize();

	    // Click on Math Calculators
		driver.findElement(By.xpath(".//*[@id='hl3']/li[3]/a")).click();
	  

		// Enter value 10 in the first number of the percent Calculator
	    driver.findElement(By.xpath(".//*[@id='cpar1']")).sendKeys("10");

	    // Enter value 50 in the second number of the percent Calculator
	    driver.findElement(By.xpath(".//*[@id='cpar2']")).sendKeys("50");
	    
	    // Click Calculate Button
	    driver.findElement(By.xpath(".//*[@id='content']/table[1]/tbody/tr[2]/td/input[2]")).click();

	    // Get the Result Text based on its xpath
	    String result = driver.findElement(By.xpath(".//*[@id='content']/p[2]/font/b")).getText();
	    
	    
	    
		//Print a Log In message to the screen
	    Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    ConfigFile.appendContentToLogFile(sdf.format(currentDate)+" : The Result is " + result);
	    //System.out.println(" The Result is " + result);
	    
		//Close the Browser.
	    driver.close();    
	}

}
