package com.example.selenium.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class DynamicDropdown {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.get("https://www.rahulshettyacademy.com/dropdownsPractise");
			driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
			driver.findElement(By.xpath("//a[@value='BLR']")).click();
			Thread.sleep(2000);
//			driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();
			driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
