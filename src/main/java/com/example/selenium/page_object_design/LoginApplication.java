package com.example.selenium.page_object_design;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class LoginApplication {

	public static void main(String[] args) {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.get("https://rahulshettyacademy.com/locatorspractice/");
			
//			LoginPage loginPage = new LoginPage(driver);
			LoginPagePF loginPage = new LoginPagePF(driver);
			loginPage.getUsernameInput().sendKeys("rahul");
			loginPage.getPasswordInput().sendKeys("rahulshettyacademy");
			loginPage.getSignInButton().click();
			loginPage.getSignOutButton().click();
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
