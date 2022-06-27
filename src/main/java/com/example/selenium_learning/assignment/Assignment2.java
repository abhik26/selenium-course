package com.example.selenium_learning.assignment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.rahulshettyacademy.com/angularpractice");
		driver.findElement(By.name("name")).sendKeys("Abhishek Anand");
		driver.findElement(By.name("email")).sendKeys("abhishek.anand@email.com");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("1234");
		driver.findElement(By.id("exampleCheck1")).click();
		Select genderSelect = new Select(driver.findElement(By.id("exampleFormControlSelect1")));
		genderSelect.selectByVisibleText("Male");
		driver.findElement(By.id("inlineRadio1")).click();
		driver.findElement(By.name("bday")).sendKeys("08/03/1995");
		driver.findElement(By.cssSelector("input[type='submit'][value='Submit']")).click();
		Thread.sleep(2000);
		String text = driver.findElement(
				By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText();
		List<WebElement> childs = driver.findElements(
				By.xpath("//div[@class='alert alert-success alert-dismissible']/a/following-sibling::strong"));
		for (WebElement child : childs) {
			text = text.substring(text.indexOf(child.getText()));
		}
		System.out.println(text);
				
		Thread.sleep(2000);
		driver.quit();
	}

}
