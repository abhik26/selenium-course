package com.example.selenium.devtools;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.fetch.Fetch;
import org.openqa.selenium.devtools.v103.network.model.Request;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class NetworkPause {

	public static void main(String[] args) {
		ChromeDriver driver = (ChromeDriver) DriverUtility.getDriver(BrowserName.CHROME);
		DevTools devTools = driver.getDevTools();
		
		try {
			devTools.createSession();
			devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
			
			devTools.addListener(Fetch.requestPaused(), (req) -> {
				Request request = req.getRequest();
				
				if (request.getUrl().contains("=shetty")) {
					String newUrl = request.getUrl().replace("=shetty", "=BadGuy");
					System.out.println(newUrl);
					
					devTools.send(Fetch.continueRequest(req.getRequestId(), Optional.of(newUrl),
							Optional.of(request.getMethod()), Optional.empty(),
							Optional.empty(), Optional.empty()));
				} else {
					devTools.send(Fetch.continueRequest(req.getRequestId(), Optional.of(request.getUrl()),
							Optional.of(request.getMethod()), Optional.empty(),
							Optional.empty(), Optional.empty()));
				}
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
