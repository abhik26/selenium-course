package com.example.selenium_learning.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.example.selenium_learning.BrowserName;
import com.example.selenium_learning.DriverUtility;

public class ActionsDemo {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		driver.get("https://amazon.com");
		Actions actions = new Actions(driver);
		WebElement signInHoverElement = driver.findElement(By.id("nav-link-accountList"));
		actions.moveToElement(signInHoverElement).build().perform();

		actions.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT)
				.sendKeys("juicer").doubleClick().build().perform();
		
		
		Thread.sleep(2000);
		driver.quit();
	}

}
