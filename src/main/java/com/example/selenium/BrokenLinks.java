package com.example.selenium;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class BrokenLinks {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
			
			List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
			
			SoftAssert failedLinks = new SoftAssert();
			
			for (WebElement link : links) {
				System.out.println(link.getText());
				String url = link.getAttribute("href");
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setRequestMethod("HEAD");
				connection.connect();
				int responseCode = connection.getResponseCode();
				
				failedLinks.assertTrue(responseCode < 400,
						String.format("The link with text: %s, is broken. Getting response code: %d.",
								link.getText(), responseCode));
			}
			
			failedLinks.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
