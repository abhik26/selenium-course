package com.example.selenium.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class SeleniumLocators3 {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			// sibling traversal using xpath
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
			System.out.println(
					driver.findElement(By.xpath(
							"//header/div/button[1]/following-sibling::button[1]")).getText());
			
			// child to parent traversal using xpath
			System.out.println(
					driver.findElement(By.xpath(
							"//header/div/button[1]/parent::div/button[3]")).getText());
			
			
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
