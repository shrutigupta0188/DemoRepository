package amazonEcom.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import amazonEcom.TestComponents.BaseTest;
import amazonEcom.pageobjects.LandingPage;

public class ErrorValidation extends BaseTest{

	@Test(dataProvider = "getCredentials")
	public void UserError(String Username) throws IOException, InterruptedException
	{
		landingpage.accountSignIn();
		//landingpage.loginUser(errUsername);
		landingpage.loginUser(Username);
		
		System.out.println(landingpage.incorrectUserName.getText());
		Assert.assertEquals(landingpage.incorrectUserName.getText(), "There was a problem");
		
	}
	
	@Test
	public void CheckPrint() {
		System.out.println("Printing checking");
	}
	
	@DataProvider
	public Object[] getCredentials() {
		Object[] data = new Object[3];
		data[0]="radndomuser";
		data[1]="radndomuser1";
		data[2]="radndomuser2";
		return data;
	}
	
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingpage= new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	
		
	
	
}
	
