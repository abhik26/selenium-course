package com.example.selenium.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class UpdatedDropdown {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.get("https://www.rahulshettyacademy.com/dropdownsPractise");
			driver.findElement(By.id("divpaxinfo")).click();
			
			Thread.sleep(1000);
			
			for (int i = 1; i < 5; i++) {
				driver.findElement(By.id("hrefIncAdt")).click();
			}
			
			System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
			driver.findElement(By.id("btnclosepaxoption")).click();
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
