package com.example.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WindowPopup {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		driver.get("http://admin:admin@the-internet.herokuapp.com/");
		driver.findElement(By.linkText("Basic Auth")).click();
		
		Thread.sleep(2000);
		driver.quit();
	}

}
