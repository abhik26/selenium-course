package com.example.selenium.page_object_design;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getUsernameInput() {
		return driver.findElement(By.id("inputUsername"));
	}
	
	public WebElement getPasswordInput() {
		return driver.findElement(By.cssSelector("input[type='password'][name='inputPassword']"));
	}
	
	public WebElement getSignInButton() {
		return driver.findElement(By.cssSelector("button.signInBtn"));
	}

	public WebElement getSignOutButton() {
		return driver.findElement(By.cssSelector("button.logout-btn"));
	}
}
