package com.example.selenium.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class SeleniumGridExample {

	@Test
	public void testGoogle() {
		WebDriver driver = null;
		
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setBrowserName("chrome");
			
			URL url = new URL("http://192.168.1.11:4444");
			driver = new RemoteWebDriver(url, caps);
			driver.get("https://google.com");
			System.out.println(driver.getTitle());
			driver.findElement(By.name("q")).sendKeys("Abhishek");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}
}
