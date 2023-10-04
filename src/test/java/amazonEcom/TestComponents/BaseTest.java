// BaseTest file for reusable components for test cases 

package amazonEcom.TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import amazonEcom.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	public String browserName;
	public String userName;
	public String password;
	public String strProduct;
	public String errUsername;
	public String url;
	public WebDriver initializeDriver() throws IOException {
		
		//properties class to read the GlobalData properties file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//amazonEcom//resources//GlobalData.properties");
		prop.load(fis); 
		browserName = prop.getProperty("browser");
		userName = prop.getProperty("username");
		password = prop.getProperty("password");
		strProduct = prop.getProperty("prodName");
		errUsername = prop.getProperty("incorrectUN");
		
		if (browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		} 
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();	
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			 driver= new EdgeDriver();
			
		}
				
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	
	@BeforeClass
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;

	}

	@AfterClass
	public void closebrowser() {
	driver.quit();
	}
	
}
