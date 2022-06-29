package com.example.selenium_learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalendarSelector {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		driver.get("https://www.rahulshettyacademy.com/dropdownsPractise");
		driver.findElement(By.id("ctl00_mainContent_view_date1")).click();
		driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
		
		if (driver.findElement(By.id("Div1")).getAttribute("style").contains("1")) {
			System.out.println("Return Date enabled");
		} else {
			System.out.println("Return Date disabled");
		}
		
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		
		if (driver.findElement(By.id("Div1")).getAttribute("style").contains("1")) {
			System.out.println("Return Date enabled");
		} else {
			System.out.println("Return Date disabled");
		}
		
		Thread.sleep(2000);
		driver.quit();
	}

}
