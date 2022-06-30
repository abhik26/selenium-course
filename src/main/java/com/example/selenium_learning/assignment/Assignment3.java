package com.example.selenium_learning.assignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment3 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		
		driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
		driver.findElement(By.id("password")).sendKeys("learning");
		driver.findElement(By.xpath("//input[@type='radio'][@name='radio'][@value='user']")).click();
		
		WebDriverWait okayButtonClickableWait = new WebDriverWait(driver, Duration.ofSeconds(2));
		okayButtonClickableWait.until(ExpectedConditions.elementToBeClickable(By.id("okayBtn")));
		driver.findElement(By.id("okayBtn")).click();
		
		Select select = new Select(driver.findElement(By.tagName("select")));
		select.selectByVisibleText("Consultant");
		
		driver.findElement(By.id("terms")).click();
		driver.findElement(By.id("signInBtn")).click();
		
		By addToCartButtonsLocatedBy = By.xpath("//div[@class='card-footer']/button");
		
		WebDriverWait pageLoadWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		pageLoadWait.until(
				ExpectedConditions.and(ExpectedConditions.urlToBe(
						"https://rahulshettyacademy.com/angularpractice/shop"),
						ExpectedConditions.visibilityOfAllElementsLocatedBy(addToCartButtonsLocatedBy)));
		
		List<WebElement> addToCartButtons = driver.findElements(addToCartButtonsLocatedBy);
		
		for (WebElement addToCartButton : addToCartButtons) {
			addToCartButton.click();
		}
		
		driver.findElement(By.cssSelector("a.nav-link.btn.btn-primary")).click();
		
		Thread.sleep(2000);
		driver.quit();
	}

}
