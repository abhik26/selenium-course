package com.example.selenium_learning;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowHandles {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		driver.findElement(By.cssSelector("a.blinkingText")).click();
		
		Set<String> windowIds = driver.getWindowHandles();
		Iterator<String> windowIdIterator = windowIds.iterator();
		String parentWindowId = windowIdIterator.next();
		String childWindowId = windowIdIterator.next();
		driver.switchTo().window(childWindowId);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.im-para.red strong a")));
		
		String email = driver.findElement(By.cssSelector("p.im-para.red strong a")).getText();
		driver.switchTo().window(parentWindowId);
		driver.findElement(By.id("username")).sendKeys(email);
		
		Thread.sleep(2000);
		driver.quit();
	}

}
