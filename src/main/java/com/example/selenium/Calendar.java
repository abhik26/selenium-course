package com.example.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Calendar {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		driver.get("https://www.path2usa.com/travel-companions");
		
		driver.findElement(By.id("travel_date")).click();
		
		while (!driver.findElement(By.cssSelector(".datepicker-days .datepicker-switch")).getText().contains("August")) {
			driver.findElement(By.cssSelector(".datepicker-days .next")).click();
		}
		
		List<WebElement> days = driver.findElements(By.cssSelector(".datepicker-days .day"));
		
		for (WebElement day : days) {
			if (Integer.valueOf(day.getText()).equals(19)) {
				day.click();
				break;
			}
		}
		
		Thread.sleep(2000);
		driver.quit();
	}

}
