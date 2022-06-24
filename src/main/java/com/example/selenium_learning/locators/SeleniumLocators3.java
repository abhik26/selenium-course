package com.example.selenium_learning.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumLocators3 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
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
		driver.quit();
	}

}
