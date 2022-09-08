package com.example.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLabTests {

	
	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("latest");
		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("build", "789456");
		sauceOptions.put("name", "Abhishek");
		browserOptions.setCapability("sauce:options", sauceOptions);

		URL url = new URL("https://Simran11:5a9a4a39-4085-481b-8c6a-066eb1f8205c@ondemand.apac-southeast-1.saucelabs.com:443/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, browserOptions);
		
//		WebDriver driver=new RemoteWebDriver(new URL(URL), caps);
		driver.get("http://google.com");
		System.out.println(driver.getTitle());
		
	}

}
