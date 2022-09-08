package endToEndProject.pageObject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtility {

	public static WebDriver getDriver(BrowserName browserName) {
		WebDriver driver = null;
		
		if (browserName == null) {
			throw new NullPointerException("browser name is not provided");
		}
		
		try {
			switch (browserName) {
				case CHROME:
					System.setProperty("webdriver.chrome.driver",
							ClassLoader.getSystemResource("localdata/chromedriver.exe").toURI().getPath());
					driver = new ChromeDriver();
					break;
				case EDGE:
					System.setProperty("webdriver.edge.driver",
							ClassLoader.getSystemResource("localdata/msedgedriver.exe").toURI().getPath());
					driver = new EdgeDriver();
					break;
				case FIREFOX:
					System.setProperty("webdriver.gecko.driver",
							ClassLoader.getSystemResource("localdata/geckodriver.exe").toURI().getPath());
	                driver = new FirefoxDriver();
					break;
				default:
					System.setProperty("webdriver.chrome.driver",
							ClassLoader.getSystemResource("localdata/chromedriver.exe").toURI().getPath());
					driver = new ChromeDriver();
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return driver;
	}

	public static void getScreenshotPath(WebDriver driver, String testMethodName) throws IOException {
		TakesScreenshot tss = (TakesScreenshot) driver;
		File source = tss.getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir") + "/reports/" + testMethodName + ".png";
		FileUtils.copyFile(source, new File(destinationFilePath));
	}
}
