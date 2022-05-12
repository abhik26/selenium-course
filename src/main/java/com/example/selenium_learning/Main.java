package com.example.selenium_learning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Main {

	public static void main(String[] args) {
		// chrome browser driver declaration
//		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		
		// edge browser driver declaration
//		System.setProperty("webdriver.gecko.driver", "");
//		WebDriver driver = new EdgeDriver();
		
		// edge browser driver declaration
		System.setProperty("webdriver.edge.driver", "D:/Softwares/msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		
		driver.get("https://www.facebook.com");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		driver.quit();
	}

}
