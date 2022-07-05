package com.example.selenium_learning.assignment;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment4 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.cssSelector("a[href='/windows']")).click();
		driver.findElement(By.cssSelector("a[href='/windows/new']")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> windowHandleIterator = windowHandles.iterator();
		String parentWindowHandle = windowHandleIterator.next();
		String childWindowHandle = windowHandleIterator.next();
		
		driver.switchTo().window(childWindowHandle);
		System.out.println(driver.findElement(By.tagName("h3")).getText());
		
		driver.switchTo().window(parentWindowHandle);
		System.out.println(driver.findElement(By.tagName("h3")).getText());
		
		Thread.sleep(2000);
		driver.quit();
	}

}
