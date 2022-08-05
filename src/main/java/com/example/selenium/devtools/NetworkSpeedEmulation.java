package com.example.selenium.devtools;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.network.Network;
import org.openqa.selenium.devtools.v103.network.model.ConnectionType;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class NetworkSpeedEmulation {

	public static void main(String[] args) {
		ChromeDriver driver = (ChromeDriver) DriverUtility.getDriver(BrowserName.CHROME);
		DevTools devTools = driver.getDevTools();
		
		try {
			devTools.createSession();
			
			devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
			devTools.send(Network.emulateNetworkConditions(false, 3000, 10000, 5000, Optional.of(ConnectionType.CELLULAR2G)));
			
			// set true for parameter 'offline' for 'Network.emulateNetworkConditions' to use this listener
			devTools.addListener(Network.loadingFailed(), loadingFailed -> {
				System.out.println(loadingFailed.getErrorText());
			});
			
			long startTime = System.currentTimeMillis();
			driver.get("https://rahulshettyacademy.com/angularAppdemo/");
			driver.findElement(By.cssSelector("[routerlink*='/library']")).click();
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
