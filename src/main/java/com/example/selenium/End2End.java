package com.example.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class End2End {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.get("https://www.rahulshettyacademy.com/dropdownsPractise");
			driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();
			driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
			driver.findElement(By.xpath(
					"//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR'] //a[@value='DEL']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
			driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
			
			Assert.assertTrue(driver.findElement(By.id("Div1"))
					.getAttribute("style").contains(".5"));
			
			Thread.sleep(1000);
			driver.findElement(By.id("divpaxinfo")).click();
			Thread.sleep(1000);
			for (int i=1; i<=3; i++) {
				driver.findElement(By.id("hrefIncAdt")).click();
			}
			
			driver.findElement(By.id("btnclosepaxoption")).click();
			Select currencySelect = new Select(
					driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
			currencySelect.selectByVisibleText("USD");
			currencySelect.selectByVisibleText("INR");
			driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();
			driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
