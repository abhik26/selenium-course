package com.example.selenium_learning.assignment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment7 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		WebElement leftTable = driver.findElement(By.cssSelector(".left-align #product"));
		System.out.println(leftTable.findElements(By.cssSelector("tr")).size());
		System.out.println(leftTable.findElements(By.cssSelector("tr:nth-child(1) th")).size());
		List<WebElement> row2Data = leftTable.findElements(By.cssSelector("tr:nth-child(3) td"));
		
		for (WebElement column : row2Data) {
			System.out.println(column.getText());
		}
		
		driver.quit();
	}

}
