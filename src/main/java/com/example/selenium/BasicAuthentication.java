package com.example.selenium;

import java.net.URI;
import java.util.function.Predicate;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;

public class BasicAuthentication {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			Predicate<URI> uriPredicate = uri -> uri.getHost().contains("");
			((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
			
			driver.get("http://httpbin.org/basic-auth/foo/bar");
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
