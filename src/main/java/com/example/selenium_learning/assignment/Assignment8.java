package com.example.selenium_learning.assignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Assignment8 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		String textToSelect = "United States (USA)";
		WebElement autoCompleteInput = driver.findElement(By.id("autocomplete"));
		autoCompleteInput.sendKeys("united");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-1")));
		
		List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item']"));
		
		for (WebElement option : options) {
			WebElement optionToSelect = option.findElement(By.cssSelector(".ui-menu-item-wrapper"));
			
			if (textToSelect.equalsIgnoreCase(optionToSelect.getText())) {
				optionToSelect.click();
				break;
			}
		}
		
		Assert.assertEquals(autoCompleteInput.getAttribute("value"), textToSelect);
		
		Thread.sleep(1000);
		driver.quit();
	}

}
