package com.example.selenium;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScopeAndMultipleLinkClickTest {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
			
			System.out.println(driver.findElements(By.tagName("a")).size());
			
			WebElement footer = driver.findElement(By.id("gf-BIG"));
			
			System.out.println(footer.findElements(By.tagName("a")).size());
			
			WebElement column = footer.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
			
			List<WebElement> links = column.findElements(By.tagName("a"));
			
			String openLinkInNewTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
			
			Iterator<WebElement> linkIterator = links.iterator();
			linkIterator.next();
			
			while (linkIterator.hasNext()) {
				linkIterator.next().sendKeys(openLinkInNewTab);
			}
			
			Set<String> windowHandles = driver.getWindowHandles();
			Iterator<String> windowIterator = windowHandles.iterator();
			windowIterator.next();
			
			while (windowIterator.hasNext()) {
				System.out.println(driver.switchTo().window(windowIterator.next()).getTitle());
			}
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
		
	}

}
