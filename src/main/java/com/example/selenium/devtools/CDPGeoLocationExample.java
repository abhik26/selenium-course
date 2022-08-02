package com.example.selenium.devtools;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class CDPGeoLocationExample {

	public static void main(String[] args) {
		ChromeDriver driver = (ChromeDriver) DriverUtility.getDriver(BrowserName.CHROME);
		DevTools devTools = driver.getDevTools();
		
		try {
			devTools.createSession();
			
			Map<String, Object> coordinates = new HashMap<String, Object>();
			coordinates.put("latitude", -14);
			coordinates.put("longitude", -51);
			coordinates.put("accuracy", 1);
			
			driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
			driver.get("https://google.com");
			driver.findElement(By.name("q")).sendKeys("amazon", Keys.ENTER);
			driver.findElement(By.cssSelector(".LC20lb")).click();
			String pageTitle = driver.getTitle();
			System.out.println(pageTitle);
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
