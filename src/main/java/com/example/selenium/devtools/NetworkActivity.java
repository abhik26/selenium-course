package com.example.selenium.devtools;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.network.Network;
import org.openqa.selenium.devtools.v103.network.model.Request;
import org.openqa.selenium.devtools.v103.network.model.Response;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class NetworkActivity {

	public static void main(String[] args) {
		ChromeDriver driver = (ChromeDriver) DriverUtility.getDriver(BrowserName.CHROME);
		DevTools devTools = driver.getDevTools();
		
		try {
			devTools.createSession();
			devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
			
			devTools.addListener(Network.requestWillBeSent(), (req) -> {
				Request request = req.getRequest();
				System.out.println("request: " + request.getUrl());
			});
			
			devTools.addListener(Network.responseReceived(), (res) -> {
				Response response = res.getResponse();
				System.out.println("response: " + response.getUrl() + "\t" + response.getStatusText());
			});
			
			driver.get("https://rahulshettyacademy.com/angularAppdemo/");
			driver.findElement(By.cssSelector("[routerlink='/library']")).click();
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
		
	}

}
