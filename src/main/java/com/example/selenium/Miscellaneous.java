package com.example.selenium;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Miscellaneous {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("ipaddress:port - example: 1.1.1.1:8080");
		
		// 1. one way of setting proxy
		options.setProxy(proxy);
		
		// 2. second way of setting proxy
		options.setCapability("proxy", proxy);
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", "D:/Downloads/Selenium_Automation_Downloads");
		options.setExperimentalOption("prefs", prefs);
		
		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
		
		WebDriver driver = new ChromeDriver(options);
		
//		driver.manage().deleteAllCookies();					// deleting all cookies
//		driver.manage().deleteCookieNamed("cookieName");	// delete some specific cookie with name
		
		driver.get("https://google.com");
		
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D:/Downloads/Selenium_Automation_Downloads/temp.png"));
		
//		driver.quit();
	}

}
