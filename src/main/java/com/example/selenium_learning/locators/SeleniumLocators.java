package com.example.selenium_learning.locators;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.selenium_learning.BrowserName;
import com.example.selenium_learning.DriverUtility;

public class SeleniumLocators {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		// global timeout option for the driver
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.id("inputUsername")).sendKeys("rahul");
		driver.findElement(By.name("inputPassword")).sendKeys("hello123");
		driver.findElement(By.className("signInBtn")).click();
		WebElement errorElement = driver.findElement(By.cssSelector("p.error"));
		System.out.println(errorElement.getText());
		
		driver.findElement(By.linkText("Forgot your password?")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath(("//input[@type='text'][@placeholder='Name']"))).sendKeys("rahul");
		driver.findElement(By.cssSelector("input[type='text'][placeholder='Email'")).sendKeys("rahul@ail.com");
		driver.findElement(By.cssSelector("input[type='text'][placeholder='Email'")).clear();
		driver.findElement(By.cssSelector("input[type='text'][placeholder='Email'")).sendKeys("rahul@email.com");
		driver.findElement(By.xpath("//form/input[3]")).sendKeys("9876543210");
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
		System.out.println(driver.findElement(By.cssSelector("form p")).getText());
		
		driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");
		driver.findElement(By.cssSelector("input[type*='pass'")).sendKeys("rahulshettyacademy");
		driver.findElement(By.xpath("//button[contains(@class, 'submit')]")).click();
	}
}
