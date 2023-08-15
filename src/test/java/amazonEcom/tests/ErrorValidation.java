package amazonEcom.tests;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import amazonEcom.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest{

	@Test
	public void UserError() throws IOException, InterruptedException
	{
		Actions action = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//div[@id='navbar']/div[@id='nav-belt']//div[@class='nav-right']//a[@id='nav-link-accountList']"));
		action.moveToElement(ele).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='nav-flyout-ya-signin']//a[@class='nav-action-signin-button']//span[@class='nav-action-inner']")).click();
		landingpage.LoginUser("shrutigreseen@gmail.com");
		System.out.println(landingpage.incorrectUserName.getText());
		Assert.assertEquals(landingpage.incorrectUserName.getText(), "There was a problem");
	}
	
	
	
	
	
	
}
	
