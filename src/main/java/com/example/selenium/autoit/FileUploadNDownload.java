package com.example.selenium.autoit;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FileUploadNDownload {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		String downloadPath = System.getProperty("user.dir");
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", prefs);
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		
		System.setProperty("webdriver.chrome.driver",
				ClassLoader.getSystemResource("localdata/chromedriver.exe").toURI().getPath());
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		driver.get("https://convertio.co/png-jpg/");
		driver.findElement(By.cssSelector("label[for='pc-upload-add']")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec(ClassLoader.getSystemResource("fileupload.exe").toURI().getPath());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr.dt-row")));
		
		driver.findElement(By.cssSelector(".convert-button")).click();
		WebElement downloadConvertedImageButton = driver.findElement(By.xpath("//a[contains(text(), 'Download')]"));
		wait.until(ExpectedConditions.elementToBeClickable(downloadConvertedImageButton));
		downloadConvertedImageButton.click();
		
		File downloadedFile = new File(downloadPath + "/cat.jpg");
		Assert.assertTrue(downloadedFile.exists());
		
		if (downloadedFile.exists()) {
			Assert.assertTrue(downloadedFile.delete());
		}
		
		driver.quit();
	}

}
