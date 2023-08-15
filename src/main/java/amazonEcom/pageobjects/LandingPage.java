package amazonEcom.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazonEcom.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	public LandingPage(WebDriver driver) 
	{
		// Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement email=driver.findElement(By.cssSelector("input#ap_email"));
	
	//PageFactory
	@FindBy(css="input#ap_email")
	WebElement username;
	
	@FindBy(css="#ap_password")
	WebElement passwordEle;
	
	@FindBy(css="input#continue")
	WebElement btnContinue;
	
	@FindBy(css="input#signInSubmit")
	WebElement btnSubmit;
	
	@FindBy(css="div[id='auth-error-message-box'] h4[class='a-alert-heading']")
	public WebElement incorrectUserName;
	
	@FindBy(css=".a-list-item")
	public WebElement incorrectPassword;
	
	
	public void Login(String email, String password) {
		username.clear();
		username.sendKeys(email);
		btnContinue.click();
		passwordEle.clear();
		passwordEle.sendKeys(password);
		btnSubmit.click();
	}
	
	public void LoginUser(String email) {
		username.clear();
		username.sendKeys(email);
		btnContinue.click();
	}
	
	public void goTo() {
		driver.get("https://www.amazon.in");
	}
	
}
