package com.example.selenium_learning.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.example.selenium_learning.BrowserName;
import com.example.selenium_learning.DriverUtility;

public class StaticDropdown {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		driver.get("https://www.rahulshettyacademy.com/dropdownsPractise");
		WebElement webElement = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select select = new Select(webElement);
		select.selectByIndex(3);
		System.out.println(select.getFirstSelectedOption().getText());
		select.selectByVisibleText("AED");
		System.out.println(select.getFirstSelectedOption().getText());
		select.selectByValue("INR");
		System.out.println(select.getFirstSelectedOption().getText());
		
		Thread.sleep(2000);
		driver.quit();
	}

}
