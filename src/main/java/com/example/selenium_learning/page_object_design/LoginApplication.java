package com.example.selenium_learning.page_object_design;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.example.selenium_learning.BrowserName;
import com.example.selenium_learning.DriverUtility;

public class LoginApplication {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		
//		LoginPage loginPage = new LoginPage(driver);
		LoginPagePF loginPage = new LoginPagePF(driver);
		loginPage.getUsernameInput().sendKeys("rahul");
		loginPage.getPasswordInput().sendKeys("rahulshettyacademy");
		loginPage.getSignInButton().click();
		loginPage.getSignOutButton().click();
		
		Thread.sleep(2000);
		driver.quit();
	}
}
