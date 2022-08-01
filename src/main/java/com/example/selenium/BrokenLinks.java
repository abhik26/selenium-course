package com.example.selenium;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class BrokenLinks {

	public static void main(String[] args) throws MalformedURLException, IOException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
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
		
		driver.quit();
		failedLinks.assertAll();
	}

}
