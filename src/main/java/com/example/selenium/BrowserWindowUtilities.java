package com.example.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserWindowUtilities {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		// open browser window in maximize mode
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com");
		
		// navigate to a certain url, back and forward. 
		// Get waits for the page to load fully, navigate doesn't
		driver.navigate().to("https://www.rahulshettyacademy.com/");
		driver.navigate().back();
		driver.navigate().forward();
		
		Thread.sleep(1000);
		driver.quit();
		
	}

}
