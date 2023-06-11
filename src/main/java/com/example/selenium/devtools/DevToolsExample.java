package com.example.selenium.devtools;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v113.emulation.Emulation;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class DevToolsExample {

	public static void main(String[] args) {
		ChromeDriver driver = (ChromeDriver) DriverUtility.getDriver(BrowserName.CHROME);
		DevTools devTools = driver.getDevTools();
		
		try {
			devTools.createSession();
			
			devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, Optional.empty(), Optional.empty(),
					Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
					Optional.empty(), Optional.empty()));
			
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
