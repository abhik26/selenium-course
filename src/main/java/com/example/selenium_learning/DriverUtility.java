package com.example.selenium_learning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtility {

	public static WebDriver getDriver(BrowserName browserName) {
		WebDriver driver = null;
		
		if (browserName == null) {
			throw new NullPointerException("browser name is not provided");
		}
		
		switch (browserName) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case EDGE:
				System.setProperty("webdriver.edge.driver", "D:/Softwares/msedgedriver.exe");
				driver = new EdgeDriver();
				break;
			case FIREFOX:
				System.setProperty("webdriver.gecko.driver", "D:/Softwares/geckodriver.exe");
                driver = new FirefoxDriver();
				break;
			default:
				System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
				driver = new ChromeDriver();
				break;
		}
		
		return driver;
	}
}
