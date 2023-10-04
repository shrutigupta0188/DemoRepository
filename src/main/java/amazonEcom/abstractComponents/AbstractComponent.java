package amazonEcom.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Parent class - Holding all reusable code.
public class AbstractComponent {
WebDriver driver;

	public AbstractComponent(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#nav-cart-count")
	WebElement cartHeader;
	
	public void goToCartIcon() {
		cartHeader.click();
	}

	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait (driver,Duration.ofSeconds(15));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
}
