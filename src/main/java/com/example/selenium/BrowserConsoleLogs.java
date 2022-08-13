package com.example.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class BrowserConsoleLogs {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.get("http://rahulshettyacademy.com/angularAppdemo/");
			driver.findElement(By.linkText("Browse Products")).click();
			driver.findElement(By.linkText("Selenium")).click();
			driver.findElement(By.cssSelector(".add-to-cart")).click();
			driver.findElement(By.linkText("Cart")).click();
			driver.findElement(By.id("exampleInputEmail1")).clear();
			driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");
			
			LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
			
			for (LogEntry entry : logs.getAll()) {
				System.out.println(entry.getMessage());
			}
			
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
