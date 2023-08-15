package amazonEcom.tests;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import amazonEcom.TestComponents.BaseTest;
import amazonEcom.pageobjects.CartPage;
import amazonEcom.pageobjects.HomePage;
import amazonEcom.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest extends BaseTest{
	String iPhoneModel = "Apple iPhone 14 (128 GB) - Starlight";
	@Test
	public void submitOrder() throws IOException, InterruptedException
	{	

		HomePage homepage = new HomePage(driver);
		CartPage cartpage = new CartPage(driver);
 
		System.out.println(iPhoneModel);

//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
		landingpage.goTo();
		Actions action = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//div[@id='navbar']/div[@id='nav-belt']//div[@class='nav-right']//a[@id='nav-link-accountList']"));
		action.moveToElement(ele).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='nav-flyout-ya-signin']//a[@class='nav-action-signin-button']//span[@class='nav-action-inner']")).click();
		System.out.println(homepage);
		landingpage.Login("shrutigreen@gmail.com", "Amazon@234");
		homepage.ProductSearch("iphone" + Keys.ENTER);
		//List<WebElement> products = homepage.returnProducts();
		homepage.getProductByName(iPhoneModel);
		homepage.clickProductFromList(iPhoneModel);
		homepage.addProductToCart();
		homepage.CartValidation();
		Assert.assertEquals(homepage.txtAddedToCartValidation.getText(), "Added to Cart");
		cartpage.btnCartClick();
		
		System.out.println("Title of the page is : " + driver.getTitle());
		System.out.println("URL of the page is : " + driver.getCurrentUrl());
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void fetchCartdetails() throws InterruptedException {
		
		String iPhoneModel = "Apple iPhone 14 (128 GB) - Starlight";
		HomePage homepage = new HomePage(driver);
		CartPage cartpage = new CartPage(driver);
		//Fetching the text in Cart for the product selected
		Boolean match = cartpage.verifyProductDisplay(iPhoneModel);
		Assert.assertTrue(match);
	
		// Fetch the product price
		System.out.println("The product cost is: "+cartpage.productCost());	
		
		//Fetch the subTotal price
		System.out.println("The total cost displayed in cart is: "+cartpage.TotalPrice());

		//Update dropdown value for quantity and fetch the dropdown value 
		System.out.println("Earlier Quantity is: " +cartpage.Quantity());
		cartpage.DropDownClick();
		cartpage.UpdateDropDown();
		System.out.println("Updated Quantity is: " +cartpage.Quantity());
		
		//Getting the cost based on quantity selected
		int quantity = Integer.parseInt(cartpage.Quantity());
		float Price = Float.parseFloat(cartpage.productCost().replaceAll(",", ""));
		float totalPrice = quantity * Price;
		System.out.println("UpdatedPrice: "+ totalPrice);

		Thread.sleep(10000);
	//	String costValidation = driver.findElement(By.xpath("//div[@data-name='Subtotals']/span[2]/span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")).getText().trim();
		float pricePay = Float.parseFloat(cartpage.TotalPrice().replaceAll(",",""));
		System.out.println("Price to be paid: "+pricePay);
		Assert.assertEquals(pricePay, totalPrice);
	

		}
}
	
