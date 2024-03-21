package com.example.irctc_booking;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.selenium.BrowserName;
import com.example.selenium.DriverUtility;

public class IRCTCBooking {

	private static String irctcUrl = "https://www.irctc.co.in/nget/train-search";
	private static Properties bookingProperties = null;

	static {
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(
					ClassLoader.getSystemResource("booking.properties").getPath());
			bookingProperties = new Properties();
			bookingProperties.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		
		WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);
		
		boolean closeBrowser = true;
		
		try {
			driver.get(irctcUrl);
			
			// click search button to search train
			WebElement trainSearchButton = driver
					.findElement(By.cssSelector("button[type='submit'][class='search_btn train_Search'"));
			
			/*
			 * To be used at tatkal time window i.e. between 10:00 AM to 12:00 PM.
			 * Comment when using outside tatkal time window.
			 */
			trainSearchButton.click();
			signIn(driver);
			
			// From station
			WebElement fromStationInput = driver.findElement(By.cssSelector("input[aria-controls='pr_id_1_list']"));
			fromStationInput.sendKeys(bookingProperties.getProperty("fromStationCode"));
			WebElement fromStationOption = driver.findElement(By.cssSelector("#pr_id_1_list li:first-child"));
			wait.until(ExpectedConditions.elementToBeClickable(fromStationOption));
			fromStationOption.click();
			
			// To station
			WebElement toStationInput = driver.findElement(By.cssSelector("input[aria-controls='pr_id_2_list']"));
			toStationInput.sendKeys(bookingProperties.getProperty("toStationCode"));
			WebElement toStationOption = driver.findElement(By.cssSelector("#pr_id_2_list li:first-child"));
			wait.until(ExpectedConditions.elementToBeClickable(toStationOption));
			toStationOption.click();
			
			// Journey date selection
			WebElement datePickerInput = driver
					.findElement(By.cssSelector("span[class='ng-tns-c58-10 ui-calendar'] input"));
			datePickerInput.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
			datePickerInput.sendKeys(bookingProperties.getProperty("journeyDate"));
			
			// Journey Quota selection
			WebElement journeyQuota = driver.findElement(By.id("journeyQuota"));
			actions.moveToElement(journeyQuota).perform();;
			journeyQuota.click();
			
			WebElement quotaOption = driver.findElement(By.cssSelector("div[class='ui-dropdown-items-wrapper ng-tns-c65-12']"))
					.findElement(
							By.cssSelector(String.format("li[aria-label='%s'", bookingProperties.getProperty("quota"))));
			actions.moveToElement(quotaOption).perform();
			wait.until(ExpectedConditions.elementToBeClickable(quotaOption));
			quotaOption.click();
			
			// click search button to search train
			trainSearchButton.click();
			
			WebElement train = driver.findElement(By.xpath(
					String.format("//strong[contains(text(), '(%s)')]/ancestor::div[contains(@class, 'border-all')]",
							bookingProperties.getProperty("trainNumber"))));
			/*
			 * Can comment this piece of code at the time of tatkal window (be careful though).
			 */
//			try {
//				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
//				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='loading-bg']")));
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//			}
			
			actions.moveToElement(train).perform();
			
			// click train class
			WebElement trainClassLink = train.findElement(
					By.xpath(String.format(".//table//td//*[contains(text(), '%s')]/ancestor::div[@class='pre-avl']",
							bookingProperties.getProperty("trainClass"))));
			actions.click(trainClassLink).perform();
			
			// click first available date (specified date)
			WebElement seatAvailableLink = train.findElement(By.cssSelector("div[class*='AVAILABLE']"));
			actions.click(seatAvailableLink).perform();
			
			// click book now
			WebElement bookTrainButton = train.findElement(By.xpath(".//button[contains(text(), 'Book Now')]"));
			actions.click(bookTrainButton).perform();
			
			/*
			 * Can comment this when booking during tatkal window and exact stations are mentioned.
			 */
//			try {
//				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//				WebElement confirmButton = driver.findElement(
//						By.xpath("//span[@class='ui-button-text ui-clickable'][contains(text(), 'Yes')]"));
//				confirmButton.click();
//				wait.until(ExpectedConditions.invisibilityOf(confirmButton));
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//			}
			
			/*
			 * To be used outside the tatkal time window.
			 */
//			signIn(driver);
			
			/*
			 * Code block to handle previous pending transaction popup, uncomment below
			 * lines of code when booking outside tatkal time window and you have
			 * pending transaction.
			 */
//			try {
//				WebElement closeTransactionButton = driver.findElement(By.xpath(
//						"//div[@aria-labelledby='ui-dialog-2-label'] //button[contains(text(), 'Close')]"));
//				wait.until(ExpectedConditions.elementToBeClickable(closeTransactionButton));
//				closeTransactionButton.click();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
			int passengerCount = Integer.parseInt(bookingProperties.getProperty("passengerCount"));
			
			if (passengerCount > 0) {
				for (int i = 1; i <= passengerCount; i++) {
					String[] passengerDetails = bookingProperties.getProperty("passenger" + i).split("\\|");
					
					// add passenger details
					List<WebElement> appPassengers = driver.findElements(By.tagName("app-passenger"));
					
					actions.moveToElement(appPassengers.get(i - 1));
					
					WebElement passengerNameElement = appPassengers.get(i - 1)
							.findElement(By.cssSelector("input[placeholder='Passenger Name']"));
					int maxPassengerNameLength = Integer.parseInt(passengerNameElement.getDomAttribute("maxLength"));
					
					// limiting to maximum characters allowed in the passenger name field
					if (passengerDetails[0].length() > maxPassengerNameLength) {
						passengerDetails[0] = passengerDetails[0].substring(0, maxPassengerNameLength);
					}
					
					// fill passenger name
					passengerNameElement.sendKeys(passengerDetails[0]);
					
					// fill passenger age
					appPassengers.get(i - 1).findElement(By.cssSelector("input[placeholder='Age']"))
							.sendKeys(passengerDetails[1]);
					
					// select passenger gender
					appPassengers.get(i - 1).findElement(By.cssSelector("select[formcontrolname='passengerGender']"))
							.click();
					appPassengers.get(i - 1)
							.findElement(By.cssSelector(
									String.format("select[formcontrolname='passengerGender'] option[value='%s']",
											passengerDetails[2])))
							.click();
					
					if (i < passengerCount) {
						WebElement addPassengerLink = driver
								.findElement(By.xpath("//span[contains(text(), 'Add Passenger')]/parent::a"));
						wait.until(ExpectedConditions.elementToBeClickable(addPassengerLink));
						actions.click(addPassengerLink).perform();
					}
				}
				
				// select 'book only if confirm births are allotted' checkbox
				WebElement confirmBirthCheckbox = driver.findElement(By.cssSelector("[for='confirmberths']"));
				actions.click(confirmBirthCheckbox).perform();
				
				// select 'pay through bhim/upi' radio button
				WebElement paymentTypeRadio = driver
						.findElement(By.cssSelector("input[type='radio'][name='paymentType'][value='2']"));
				actions.moveToElement(paymentTypeRadio);
				jsExecutor.executeScript("arguments[0].click()", paymentTypeRadio);
				
				// click continue button
				WebElement continueButton = driver.findElement(
						By.xpath("//button[@class='train_Search btnDefault'][contains(text(), 'Continue')]"));
				actions.click(continueButton).perform();
				
				// final captcha
				WebElement finalCaptcha = driver.findElement(By.id("captcha"));
				
				// continue button for clicking after entering final captcha
				WebElement continueButtonOnReview = driver.findElement(By.xpath(
						"//button[@class='btnDefault train_Search'][contains(text(), 'Continue')]"));
				
				// increasing wait time for review the journey
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				
				WebElement irctcIPayOption = driver
						.findElement(By.xpath("//span[contains(text(), 'IRCTC iPay')]/parent::div"));
				
				// click on irctc ipay option if not selected
				if (!irctcIPayOption.getDomAttribute("class").contains("bank-type-active")) {
					wait.until(ExpectedConditions.elementToBeClickable(irctcIPayOption));
					irctcIPayOption.click();
				}
				
				// click on pay and book
				WebElement payAndBookButton = driver.findElement(
						By.xpath("//button[contains(text(), 'Pay & Book')][contains(@class, 'btn btn-primary')]"));
				wait.until(ExpectedConditions.visibilityOf(payAndBookButton));
//				payAndBookButton.click();
				jsExecutor.executeScript("arguments[0].click()", payAndBookButton);
				
				// fill upi id
				driver.findElement(By.id("vpaCheck")).sendKeys(bookingProperties.getProperty("upiVPA"));
				
				// click pay
				WebElement finalPayButton = driver.findElement(By.id("upi-sbmt"));
				wait.until(ExpectedConditions.elementToBeClickable(finalPayButton));
				finalPayButton.click();
				
				closeBrowser = false;
				
				jsExecutor.executeScript("window.alert('Tatkal booking process successful. Hooray!!')");
			}
			
//			TimeUnit.SECONDS.sleep(60);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (closeBrowser) {
				driver.quit();
			}
		}
	}

	private static void signIn(WebDriver driver) {
		driver.findElement(By.cssSelector("input[formcontrolname='userid']"))
				.sendKeys(String.valueOf(bookingProperties.getProperty("irctcUsername")));
		driver.findElement(By.cssSelector("input[formcontrolname='password']"))
				.sendKeys(String.valueOf(bookingProperties.getProperty("irctcPassword")));
		WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit'][contains(text(), 'SIGN IN')]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(signInButton));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click()", signInButton);
//		signInButton.click();
		wait.until(ExpectedConditions.invisibilityOf(signInButton));
	}
	
}
