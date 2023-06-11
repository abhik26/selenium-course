package com.example.selenium.devtools;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v113.network.Network;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;
import com.google.common.collect.ImmutableList;

public class NetworkBlock {

	public static void main(String[] args) {
		ChromeDriver driver = (ChromeDriver) DriverUtility.getDriver(BrowserName.CHROME);
		DevTools devTools = driver.getDevTools();
		
		try {
			devTools.createSession();
			
			devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
			devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg,", "*.css")));
			
			long startTime = System.currentTimeMillis();
			driver.get("https://rahulshettyacademy.com/angularAppdemo/");
			driver.findElement(By.linkText("Browse Products")).click();
			driver.findElement(By.linkText("Selenium")).click();
			driver.findElement(By.cssSelector(".add-to-cart")).click();
			System.out.println(driver.findElement(By.tagName("p")).getText());
			long endTime = System.currentTimeMillis();
			
			System.out.println("Time taken: " + (endTime - startTime));
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
