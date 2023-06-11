package com.example.selenium;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ECommerceCart {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		String[] items = {"Brocolli", "Beetroot", "Carrot", "Tomato"};
		
		// Implicit wait, this wait method is applied globally
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		try {
			driver.get("https://rahulshettyacademy.com/seleniumPractise");
			ECommerceCart.addItemsToCart(driver, items);
			ECommerceCart.proceedToCheckout(driver);
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
	
	public static void addItemsToCart(WebDriver driver, String[] items) throws InterruptedException {
		List<String> itemList = Arrays.asList(items);
		int itemsAdded = 0;
		
//		Thread.sleep(3000);
		
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
	
	public static void proceedToCheckout(WebDriver driver) throws InterruptedException {
		driver.findElement(By.cssSelector("a[class='cart-icon'")).click();
		driver.findElement(By.xpath(
				"//div[contains(@class, 'cart-preview')]/div[@class='action-block']/button"))
						.click();
//		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@class='promoCode']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.xpath("//button[@class='promoBtn']")).click();
		
		// Explicit wait, this wait method executes on certain condition only
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class='promoInfo']")));
		
		System.out.println(driver.findElement(By.xpath("//span[@class='promoInfo']")).getText());
	}
}
