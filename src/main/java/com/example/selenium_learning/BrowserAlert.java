package com.example.selenium_learning;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserAlert {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice");
		driver.findElement(By.id("name")).sendKeys("Abhishek");
		driver.findElement(By.id("alertbtn")).click();
		System.out.println(driver.switchTo().alert().getText());
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		
		driver.findElement(By.id("name")).sendKeys("Abhishek");
		driver.findElement(By.id("confirmbtn")).click();
		Alert confirm = driver.switchTo().alert();
		System.out.println(confirm.getText());
		Thread.sleep(1000);
		confirm.dismiss();
		
		Thread.sleep(2000);
		driver.quit();
        
	}

}
