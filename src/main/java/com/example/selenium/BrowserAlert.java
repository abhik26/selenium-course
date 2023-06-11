package com.example.selenium;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserAlert {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		try {
			driver.get("https://www.rahulshettyacademy.com/AutomationPractice");
			driver.findElement(By.id("name")).sendKeys("Abhishek");
			driver.findElement(By.id("alertbtn")).click();
			System.out.println(driver.switchTo().alert().getText());
//			Thread.sleep(1000);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			
			driver.findElement(By.id("name")).sendKeys("Abhishek");
			driver.findElement(By.id("confirmbtn")).click();
			Alert confirm = driver.switchTo().alert();
			System.out.println(confirm.getText());
//			Thread.sleep(1000);
			wait.until(ExpectedConditions.alertIsPresent());
			confirm.dismiss();
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
        
	}

}
