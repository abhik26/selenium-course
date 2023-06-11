package com.example.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkbox {
	
	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.get("https://www.rahulshettyacademy.com/dropdownsPractise");
			System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount'")).isSelected());
			driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount'")).click();
			System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount'")).isSelected());
			
			System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
