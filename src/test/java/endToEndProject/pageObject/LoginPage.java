package endToEndProject.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void login(String userEmail, String password) {
		driver.findElement(By.cssSelector("input[id='user_email']")).sendKeys(userEmail);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[value='Log In']")).click();
	}
}
