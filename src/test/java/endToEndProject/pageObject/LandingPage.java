package endToEndProject.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	private WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getLogin() {
		return driver.findElement(By.cssSelector("a[href*='sign_in']"));
	}
	
	public WebElement getTitle() {
		return driver.findElement(By.cssSelector(".text-center > h2"));
	}
}
