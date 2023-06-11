package com.example.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AutoSuggestiveInput {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.get("https://www.rahulshettyacademy.com/dropdownsPractise");
			driver.findElement(By.id("autosuggest")).sendKeys("ind");
			Thread.sleep(2000);
			List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
			
			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase("India")) {
					option.click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
