package com.example.selenium.devtools;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v113.fetch.Fetch;
import org.openqa.selenium.devtools.v113.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v113.network.model.ErrorReason;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class NetworkFail {

	public static void main(String[] args) {
		ChromeDriver driver = (ChromeDriver) DriverUtility.getDriver(BrowserName.CHROME);
		DevTools devTools = driver.getDevTools();
		
		try {
			devTools.createSession();
			
			Optional<List<RequestPattern>> patterns = 
					Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));
			devTools.send(Fetch.enable(patterns, Optional.empty()));
			
			devTools.addListener(Fetch.requestPaused(), (req) -> {
				devTools.send(Fetch.failRequest(req.getRequestId(), ErrorReason.FAILED));
			});
			
			driver.get("https://rahulshettyacademy.com/angularAppdemo/");
			driver.findElement(By.cssSelector("[routerlink*='/library']")).click();
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
