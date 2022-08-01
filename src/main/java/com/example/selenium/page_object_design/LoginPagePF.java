package com.example.selenium.page_object_design;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPagePF {

	private WebDriver driver;
	
	@FindBy(id="inputUsername")
	private WebElement usernameInput;
	
	@FindBy(css="input[type='password'][name='inputPassword']")
	private WebElement passwordInput;
	
	@FindBy(css="button.signInBtn")
	private WebElement signInButton;
	
	@FindBy(css="button.logout-btn")
	private WebElement signOutButton;
	
	public LoginPagePF(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getUsernameInput() {
		return usernameInput;
	}
	
	public WebElement getPasswordInput() {
		return passwordInput;
	}
	
	public WebElement getSignInButton() {
		return signInButton;
	}

	public WebElement getSignOutButton() {
		return signOutButton;
	}
}
