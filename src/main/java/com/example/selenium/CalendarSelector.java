package com.example.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalendarSelector {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
