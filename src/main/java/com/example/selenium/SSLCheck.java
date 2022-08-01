package com.example.selenium;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SSLCheck {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("ipaddress:port - example: 1.1.1.1:8080");
		
		// 1. one way of setting proxy
		options.setProxy(proxy);
		
		// 2. second way of setting proxy
		options.setCapability("proxy", proxy);
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", "D:/Downloads/Selenium_Automation_Downloads");
		options.setExperimentalOption("prefs", prefs);
		
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().deleteAllCookies();					// deleting all cookies
		driver.manage().deleteCookieNamed("cookieName");	// delete some specific cookie with name
		
		driver.get("https://expired.badssl.com");
		System.out.println(driver.getTitle());
		
		driver.quit();
	}

}
