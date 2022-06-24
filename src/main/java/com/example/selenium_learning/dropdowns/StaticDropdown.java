package com.example.selenium_learning.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class StaticDropdown {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
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
