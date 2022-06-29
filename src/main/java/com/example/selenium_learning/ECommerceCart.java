package com.example.selenium_learning;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ECommerceCart {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String[] items = {"Brocolli", "Beetroot", "Carrot", "Tomato"};
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise");
		addItemsToCart(driver, items);
		
		Thread.sleep(5000);
		driver.quit();
	}
	
	public static void addItemsToCart(WebDriver driver, String[] items) throws InterruptedException {
		List<String> itemList = Arrays.asList(items);
		int itemsAdded = 0;
		
		Thread.sleep(3000);
		
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='product']"));
		
		for (WebElement product : products) {
			String productName = product.findElement(By.cssSelector("h4.product-name"))
					.getText().split("\\s*-\\s*")[0].trim();
			
			if (itemList.contains(productName)) {
				product.findElement(By.xpath("div[@class='product-action']/button")).click();
				itemsAdded++;
				
				if (itemsAdded == itemList.size()) {
					break;
				}
			}
		}
	}
}
