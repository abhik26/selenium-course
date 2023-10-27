package com.example.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Calendar {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			
			driver.get("https://www.path2usa.com/travel-companions");
			
			WebElement dateOfTravelElement = driver.findElement(By.id("form-field-travel_comp_date"));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true)", dateOfTravelElement);
			
			Thread.sleep(3000);
			
			driver.findElement(By.id("form-field-travel_comp_date")).click();
			
			
//			Point calendarSelectorPoint = driver.findElement(By.cssSelector(".flatpickr-next-month svg")).getLocation();
//			jsExecutor.executeScript("arguments[0].scrollIntoView(true)", calendarSelectorPoint);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".flatpickr-next-month svg")));
			
			while (!driver.findElement(By.cssSelector(".flatpickr-current-month .cur-month")).getText().contains("August")) {
				driver.findElement(By.cssSelector(".flatpickr-next-month svg")).click();
			}
			
			List<WebElement> days = driver.findElements(By.cssSelector(".flatpickr-days .flatpickr-day"));
			
			for (WebElement day : days) {
				if (Integer.valueOf(day.getText()).equals(19)) {
					day.click();
					break;
				}
			}
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			driver.quit();
		}
	}

}
