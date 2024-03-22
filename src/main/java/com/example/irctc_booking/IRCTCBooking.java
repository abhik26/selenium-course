package com.example.irctc_booking;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

	private static final String irctcUrl = "https://www.irctc.co.in/nget/train-search";
	private static Properties bookingProperties = null;
	
	private static boolean tatkalWindow = false;
	private static String seatLinkDateSearch = null;
	
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM");
	private static final DateTimeFormatter journeyDateFormattter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final ZoneId indiaZoneId = ZoneId.of("Asia/Kolkata");
	
	private static enum BookingProperty {
		USERNAME("irctcUsername"), PASSWORD("irctcPassword"), FROM_STATION("fromStationCode"),
		TO_STATION("toStationCode"), JOURNEY_DATE("journeyDate"), QUOTA("quota"), TRAIN_NUMBER("trainNumber"),
		TRAIN_CLASS("trainClass"), PASSENGER_COUNT("passengerCount"), UPI_ID("upiId");
		
		private final String name;

		BookingProperty(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
	}

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
	
	public static void main(String[] args) throws Exception {
		LocalTime start = LocalTime.now();
		
		// start booking script
		IRCTCBooking.startBooking();
		
		LocalTime end = LocalTime.now();
		
		System.out.println("Script duration for successful booking: " + Duration.between(start, end).toMillis());
	}

	private static void startBooking() throws Exception {
		final int defaultImplicitWaitTime = 30;
		final int defaultExplicitWaitTime = 30;
		final int alternateImplicitWaitTime = 3;
		
		final WebDriver driver = DriverUtility.getDriver(BrowserName.CHROME);
		final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultExplicitWaitTime));
		final JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		final Actions actions = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultImplicitWaitTime));
		
		boolean closeBrowser = true;
		
		IRCTCBooking.preBookingChecks();
		
		try {
			driver.get(irctcUrl);
			
			// click search button to search train
			WebElement trainSearchButton = driver.findElement(By.cssSelector(
					"button[type='submit'][class='search_btn train_Search'"));
			
			/*
			 * To be used during tatkal window i.e. between 08:30 AM to 11:30 AM.
			 */
			if (tatkalWindow) {
				trainSearchButton.click();
				signIn(driver, wait);
			}
			
			// From station
			WebElement fromStationInput = driver.findElement(By.cssSelector("input[aria-controls='pr_id_1_list']"));
			fromStationInput.sendKeys(bookingProperties.getProperty(BookingProperty.FROM_STATION.toString()).trim());
			WebElement fromStationOption = driver.findElement(By.cssSelector("#pr_id_1_list li:first-child"));
			wait.until(ExpectedConditions.elementToBeClickable(fromStationOption));
			fromStationOption.click();
			
			// To station
			WebElement toStationInput = driver.findElement(By.cssSelector("input[aria-controls='pr_id_2_list']"));
			toStationInput.sendKeys(bookingProperties.getProperty(BookingProperty.TO_STATION.toString()).trim());
			WebElement toStationOption = driver.findElement(By.cssSelector("#pr_id_2_list li:first-child"));
			wait.until(ExpectedConditions.elementToBeClickable(toStationOption));
			toStationOption.click();
			
			// Journey date selection
			WebElement datePickerInput = driver
					.findElement(By.cssSelector("span[class='ng-tns-c58-10 ui-calendar'] input"));
			datePickerInput.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
			datePickerInput.sendKeys(bookingProperties.getProperty(BookingProperty.JOURNEY_DATE.toString()).trim());
			
			// Journey Quota selection
			WebElement journeyQuota = driver.findElement(By.id("journeyQuota"));
			actions.moveToElement(journeyQuota).perform();;
			journeyQuota.click();
			
			WebElement quotaOption = driver
					.findElement(By.cssSelector("div[class='ui-dropdown-items-wrapper ng-tns-c65-12']"))
					.findElement(By.cssSelector(String.format("li[aria-label='%s'",
							bookingProperties.getProperty(BookingProperty.QUOTA.toString()).trim())));
			actions.moveToElement(quotaOption).perform();
			wait.until(ExpectedConditions.elementToBeClickable(quotaOption));
			quotaOption.click();
			
			// click search button to search train
			trainSearchButton.click();
			
			WebElement train = driver.findElement(By.xpath(String.format(
					"//strong[contains(text(), '(%s)')]/ancestor::div[contains(@class, 'border-all')]",
							bookingProperties.getProperty(BookingProperty.TRAIN_NUMBER.toString()).trim())));
			
			// waiting for ad to load to prevent unnecessary error
			if (!tatkalWindow) {
				TimeUnit.SECONDS.sleep(alternateImplicitWaitTime);
			}
			
			// scroll to the train (if needed)
			actions.moveToElement(train).perform();
			
			// click train class
			WebElement trainClassLink = train.findElement(
					By.xpath(String.format(".//td//*[contains(text(), '%s')]/ancestor::td",
							bookingProperties.getProperty(BookingProperty.TRAIN_CLASS.toString()).trim())));
			actions.click(trainClassLink).perform();
			
			// click first available date (specified date)
			WebElement seatAvailableLink = null;
			
			if ("TATKAL".equalsIgnoreCase(bookingProperties.getProperty(BookingProperty.QUOTA.toString()).trim())) {
				seatAvailableLink = train.findElement(
						By.xpath(".//td//div[contains(@class, 'AVAILABLE')]/ancestor::td"));
			} else {
				seatAvailableLink = train.findElement(By.xpath(
						String.format(".//td//strong[contains(text(), '%s')]/ancestor::td",
								seatLinkDateSearch)));
				
				if (seatAvailableLink.findElements(By.cssSelector("div[class*='AVAILABLE']")).size() == 0) {
					String message = "Seat not available for the given class for the provided date.";
					jsExecutor.executeScript(String.format("window.alert('%s')", message));
					TimeUnit.SECONDS.sleep(alternateImplicitWaitTime);
					throw new RuntimeException(message);
				}
			}
			
			actions.click(seatAvailableLink).perform();
			
			// click book now
			WebElement bookTrainButton = train.findElement(By.xpath(".//button[contains(text(), 'Book Now')]"));
			actions.click(bookTrainButton).perform();
			
			/*
			 * To be used when exact stations are not mentioned.
			 * Please provide exact stations and comment this piece of code
			 * to save time (very crucial during tatkal window).
			 */
//			try {
//				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(alternateImplicitWaitTime));
//				WebElement confirmButton = driver.findElement(
//						By.xpath("//span[@class='ui-button-text ui-clickable'][contains(text(), 'Yes')]"));
//				confirmButton.click();
//				wait.until(ExpectedConditions.invisibilityOf(confirmButton));
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultImplicitWaitTime));
//			}
			
			/*
			 * To be used outside the tatkal time window.
			 */
			if (!tatkalWindow) {
				signIn(driver, wait);
			}
			
			/*
			 * Code block to handle previous pending transaction popup, uncomment below
			 * lines of code when booking outside tatkal time window and you have
			 * pending transaction.
			 */
			if (!tatkalWindow) {
				try {
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(alternateImplicitWaitTime));
					WebElement closeTransactionButton = driver.findElement(By.xpath(
							"//div[@aria-labelledby='ui-dialog-2-label'] //button[contains(text(), 'Close')]"));
					wait.until(ExpectedConditions.elementToBeClickable(closeTransactionButton));
					closeTransactionButton.click();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultImplicitWaitTime));
				}
			}
			
			int passengerCount = Integer
					.parseInt(bookingProperties.getProperty(BookingProperty.PASSENGER_COUNT.toString()).trim());
			
			if (passengerCount > 0) {
				for (int i = 1; i <= passengerCount; i++) {
					String[] passengerDetails = bookingProperties.getProperty("passenger" + i).trim()
							.split("\\s*\\|\\s*");
					
					// add passenger details
					List<WebElement> appPassengers = driver.findElements(By.tagName("app-passenger"));
					WebElement appPassenger = appPassengers.get(i - 1);
					
					actions.moveToElement(appPassenger);
					
					WebElement passengerNameElement = appPassenger
							.findElement(By.cssSelector("input[placeholder='Passenger Name']"));
					
					// limiting to maximum characters allowed in the passenger name field
					String passengerNameMaxLength = passengerNameElement.getDomAttribute("maxLength");
					if (passengerNameMaxLength != null) {
						int maxPassengerNameLength = Integer.parseInt(passengerNameMaxLength);
						
						if (passengerDetails[0].length() > maxPassengerNameLength) {
							passengerDetails[0] = passengerDetails[0].substring(0, maxPassengerNameLength);
						}
					}
					
					// fill passenger name
					passengerNameElement.sendKeys(passengerDetails[0]);
					
					// fill passenger age
					appPassenger.findElement(By.cssSelector("input[placeholder='Age']")).sendKeys(passengerDetails[1]);
					
					// select passenger gender
					appPassenger.findElement(By.cssSelector("select[formcontrolname='passengerGender']")).click();
					appPassenger.findElement(
							By.cssSelector(String.format("select[formcontrolname='passengerGender'] option[value='%s']",
									passengerDetails[2])))
							.click();
					
					// select passenger berth preference
					if (passengerDetails.length >= 4) {
						appPassenger.findElement(By.cssSelector("select[formcontrolname='passengerBerthChoice']"))
								.click();
						appPassenger.findElement(By.cssSelector(
								String.format("select[formcontrolname='passengerBerthChoice'] option[value='%s']",
										passengerDetails[3])))
								.click();
					}
					
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
				
				// increasing wait time to review the journey
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultImplicitWaitTime * 2));
				
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
				wait.until(ExpectedConditions.elementToBeClickable(payAndBookButton));
//				payAndBookButton.click();
				jsExecutor.executeScript("arguments[0].click()", payAndBookButton);
				
				// fill upi id
				driver.findElement(By.id("vpaCheck")).sendKeys(bookingProperties.getProperty(BookingProperty.UPI_ID.toString()).trim());
				
				// click pay
				WebElement finalPayButton = driver.findElement(By.id("upi-sbmt"));
				wait.until(ExpectedConditions.elementToBeClickable(finalPayButton));
				finalPayButton.click();
				
				closeBrowser = false;
				
//				jsExecutor.executeScript("window.alert('Tatkal booking process successful. Hooray!!')");
			}
			
//			TimeUnit.SECONDS.sleep(60);

		} catch (Exception e) {
			throw e;
		} finally {
			if (closeBrowser) {
				driver.quit();
			}
		}
	}

	private static void signIn(WebDriver driver, WebDriverWait wait) {
		WebElement userIdInput = driver.findElement(By.cssSelector("input[formcontrolname='userid']"));
		userIdInput.sendKeys(bookingProperties.getProperty(BookingProperty.USERNAME.toString()).trim());
		
		WebElement passwordInput = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
		passwordInput.sendKeys(bookingProperties.getProperty(BookingProperty.PASSWORD.toString()).trim());
		
		WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit'][contains(text(), 'SIGN IN')]"));
		wait.until(ExpectedConditions.elementToBeClickable(signInButton));
//		signInButton.click();
		
		wait.until(ExpectedConditions.invisibilityOf(signInButton));
	}
	
	private static void preBookingChecks() {
		ZonedDateTime irctcTatkalWindowStart = ZonedDateTime.now(indiaZoneId).withHour(8).withMinute(30).withSecond(0)
				.truncatedTo(ChronoUnit.SECONDS);
		ZonedDateTime irctcTatkalWindowEnd = ZonedDateTime.now(indiaZoneId).withHour(11).withMinute(30).withSecond(0)
				.truncatedTo(ChronoUnit.SECONDS);
		ZonedDateTime indiaDateTime = ZonedDateTime.now(indiaZoneId);

		if (indiaDateTime.isAfter(irctcTatkalWindowStart) && indiaDateTime.isBefore(irctcTatkalWindowEnd)) {
			IRCTCBooking.tatkalWindow = true;
		}
		
		seatLinkDateSearch = LocalDate.parse(
				bookingProperties.getProperty(BookingProperty.JOURNEY_DATE.toString()), journeyDateFormattter)
				.format(dateTimeFormatter);
	}
	
}
