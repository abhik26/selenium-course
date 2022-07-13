package com.example.selenium_learning;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class JavaStreamWithSelenium {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.xpath("//tr[1]/th[1]")).click();
		List<WebElement> items = driver.findElements(By.xpath("//tr/td[1]"));
		
		List<String> itemNames = items.stream().map(element -> element.getText()).collect(Collectors.toList());
		List<String> itemNamesSorted = itemNames.stream().sorted().collect(Collectors.toList());
		
		Assert.assertTrue(itemNames.equals(itemNamesSorted));
		
		/*
		 * find the price of a specific item with deal
		 */
		List<WebElement> prices;
		WebElement nextButton = driver.findElement(By.cssSelector("a[aria-label='Next']"));
		boolean clickable = false;
		
		// 1st way
		do {
			items = driver.findElements(By.xpath("//tr/td[1]"));
			prices = items.stream().filter(element -> element.getText().contains("Rice"))
					.map(element -> {
						return element.findElement(By.xpath("following-sibling::td[1]"));
					}).collect(Collectors.toList());
			
			if (!prices.isEmpty()) {
				System.out.println(prices.get(0).getText());
			} else {
				clickable = !Boolean.parseBoolean(nextButton.getAttribute("aria-disabled"));
				
				if (clickable) {
					nextButton.click();
				}
			}
		} while (prices.isEmpty() && clickable);
		
		// 2nd way
//		do {
//			prices = driver.findElements(By.xpath("(//tr/td[1])[text() = 'fafaf']/following-sibling::td[1]"));
//			
//			if (!prices.isEmpty()) {
//				System.out.println(prices.get(0).getText());
//			} else {
//				clickable = !Boolean.parseBoolean(nextButton.getAttribute("aria-disabled"));
//				
//				if (clickable) {
//					nextButton.click();
//				}
//			}
//				 
//		} while (prices.isEmpty() && clickable);
		
		/*
		 * using search function on UI and matching with java stream filters
		 */
		String searchText = "rice";
		driver.findElement(By.id("search-field")).sendKeys(searchText);
		items = driver.findElements(By.xpath("//tr/td[1]"));
		List<WebElement> filteredItems = items.stream()
				.filter(element -> element.getText().toLowerCase().contains(searchText.toLowerCase()))
				.collect(Collectors.toList());
		Assert.assertEquals(items.size(), filteredItems.size());
		
		driver.quit();
	}

}
