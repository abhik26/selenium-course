package com.example.selenium_learning.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Assignment6 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		WebElement checkboxLabel = driver.findElement(By.xpath("//*[@id='checkbox-example']/fieldset/label"));
		checkboxLabel.findElement(By.tagName("input")).click();
		String checkboxText = checkboxLabel.getText().trim();
		Select select = new Select(driver.findElement(By.id("dropdown-class-example")));
		select.selectByVisibleText(checkboxText);
		driver.findElement(By.id("name")).sendKeys(checkboxText);
		driver.findElement(By.id("alertbtn")).click();
		Assert.assertTrue(driver.switchTo().alert().getText().contains(checkboxText));
		driver.switchTo().alert().accept();
		
		Thread.sleep(2000);
		driver.quit();
		
	}
	
}
