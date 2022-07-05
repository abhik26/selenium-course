package com.example.selenium_learning.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.example.selenium_learning.BrowserName;
import com.example.selenium_learning.DriverUtility;

public class Frame {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		driver.get("https://jqueryui.com/droppable");
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame")));
		Actions action = new Actions(driver);
		WebElement sourceElement = driver.findElement(By.id("draggable"));
		WebElement targetElement = driver.findElement(By.id("droppable"));
		action.dragAndDrop(sourceElement, targetElement).build().perform();
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
		driver.quit();
	}

}
