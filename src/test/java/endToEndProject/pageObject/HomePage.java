package endToEndProject.pageObject;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class HomePage {

	private WebDriver driver;
	
	@Test(dataProvider = "getData")
	public void basePageNavigation(String username, String password) {
		driver = DriverUtility.getDriver(BrowserName.CHROME);
		
		try {
			driver.get("http://qaclickacademy.com");
			
			LandingPage lp = new LandingPage(driver);
			
			Assert.assertEquals(lp.getTitle().getText(), "FEATURED COURSES");
			
			lp.getLogin().click();
			
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(username, password);
			
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][2];
		
		data[0][0] = "abhishek@example.com";
		data[0][1] = "1234";
		
		data[1][0] = "neha@example.com";
		data[1][1] = "1234";
		
		return data;
	}
}
