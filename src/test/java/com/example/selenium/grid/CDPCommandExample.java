package com.example.selenium.grid;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class CDPCommandExample {

	public static void main(String[] args) {
		ChromeDriver driver = (ChromeDriver) DriverUtility.getDriver(BrowserName.CHROME);
		DevTools devTools = driver.getDevTools();
		
		try {
			devTools.createSession();
			
			Map<String, Object> deviceMetrics = new HashMap<String, Object>();
			deviceMetrics.put("width", 600);
			deviceMetrics.put("height", 1000);
			deviceMetrics.put("deviceScaleFactor", 50);
			deviceMetrics.put("mobile", true);
			
			driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
			
			driver.get("https://rahulshettyacademy.com/angularAppdemo/");
			
			driver.findElement(By.cssSelector(".navbar-toggler")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.linkText("Library")).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
