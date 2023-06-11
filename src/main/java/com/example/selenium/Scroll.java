package com.example.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scroll {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
			
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement postionDetailTable = driver.findElement(By.cssSelector(".tableFixHead"));
			
			// 1. scroll by getting x, y coordinates of the element
			Point tablePoint = postionDetailTable.getLocation();
			jsExecutor.executeScript(String.format("scroll(%d,%d)", tablePoint.getX(), tablePoint.getY()));
			
			// 2. scroll using web element
//			jsExecutor.executeScript("arguments[0].scrollIntoView(true)", postionDetailTable);
			
			// 3. scroll using actions and page down key
//			Actions actions = new Actions(driver);
//			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
			
			Thread.sleep(2000);
			jsExecutor.executeScript("document.querySelector('.tableFixHead').scrollTop+=100");
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
