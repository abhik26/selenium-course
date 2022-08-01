package com.example.selenium.selenium4;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class InvokingTabNWindow_ElementScreenshot_ElementDimension {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		driver.switchTo().newWindow(WindowType.TAB);
//		driver.switchTo().newWindow(WindowType.WINDOW);
		
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> windowHandleIterator = windowHandles.iterator();
		String mainWindow = windowHandleIterator.next();
		String childWindow = windowHandleIterator.next();
		
		driver.switchTo().window(childWindow);
		driver.get("https://rahulshettyacademy.com");
		String courseName = driver.findElement(
				By.xpath("(//div[@id='courses-block']/div[contains(@class, 'courses-block')])[1] //div[@class='lower-content'] //a[contains(@href, 'https://courses.rahulshettyacademy')]")).getText();
		driver.switchTo().window(mainWindow);
		WebElement nameInputElement = driver.findElement(By.cssSelector("[name='name']"));
		nameInputElement.sendKeys(courseName);
		
		// capturing only element screenshot
		File file = nameInputElement.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("D:\\Pictures\\Screenshots\\Selenium_Automation_Downloads/screenshot_" 
				+ System.currentTimeMillis() + ".png"));
		
		// getting element dimensions (height and width)
		System.out.println(nameInputElement.getRect().getDimension().getHeight());
		System.out.println(nameInputElement.getRect().getDimension().getWidth());
		
		
		Thread.sleep(2000);
		driver.quit();
	}

}
