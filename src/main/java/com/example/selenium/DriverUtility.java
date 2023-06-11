package com.example.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtility {

	public static WebDriver getDriver(BrowserName browserName) {
		WebDriver driver = null;
		
		if (browserName == null) {
			throw new NullPointerException("browser name is not provided");
		}
		
		try {
			switch (browserName) {
				case CHROME:
					System.setProperty("webdriver.chrome.driver",
							ClassLoader.getSystemResource("localdata/chromedriver.exe").toURI().getPath());
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--remote-allow-origins=*");
					driver = new ChromeDriver(options);
					break;
				case EDGE:
					System.setProperty("webdriver.edge.driver",
							ClassLoader.getSystemResource("localdata/msedgedriver.exe").toURI().getPath());
					driver = new EdgeDriver();
					break;
				case FIREFOX:
					System.setProperty("webdriver.gecko.driver",
							ClassLoader.getSystemResource("localdata/geckodriver.exe").toURI().getPath());
	                driver = new FirefoxDriver();
					break;
				default:
					System.setProperty("webdriver.chrome.driver",
							ClassLoader.getSystemResource("localdata/chromedriver.exe").toURI().getPath());
					driver = new ChromeDriver();
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return driver;
	}
}
