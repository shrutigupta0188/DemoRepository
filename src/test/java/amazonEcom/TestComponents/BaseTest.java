package amazonEcom.TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import amazonEcom.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException {
		
		//properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\amazonEcom\\resources\\GlobalData.properties");
		prop.load(fis); 
		String browserName = prop.getProperty("browser");
		
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
				
		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}
	
	
public List<HashMap <String, String>> getJsonData() throws IOException {
		
		// Read from json to String
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\amazonEcom\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);

		// String to hashMap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	
	
	
	@BeforeClass
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingpage= new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
		
	}

	/*
	 * @AfterMethod public void closebrowser() { driver.close(); driver.quit(); }
	 */
	
}
