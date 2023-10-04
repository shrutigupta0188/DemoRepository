package amazonEcom.tests;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import amazonEcom.TestComponents.BaseTest;
import amazonEcom.pageobjects.CartPage;
import amazonEcom.pageobjects.HomePage;

public class LoginTest extends BaseTest {
	//String strProduct = "Apple iPhone 14 (128 GB) - (Product) RED";
			//"Apple iPhone 14 (128 GB) - Starlight";

	@Test
	public void submitOrder() throws IOException, InterruptedException {

		HomePage homepage = new HomePage(driver);
		CartPage cartpage = new CartPage(driver);

		landingpage.goTo();
		System.out.println(browserName);

		landingpage.accountSignIn();
		landingpage.login(userName,password);
		homepage.productSearch("iphone" + Keys.ENTER);
		homepage.getProductByName(strProduct);
		homepage.clickProductFromList(strProduct);
		homepage.addProductToCart();

		if (browserName.equalsIgnoreCase("firefox")) {
			Assert.assertEquals(homepage.txtFirefoxAddedToCartValidation.getText(), "Added to Cart");
			cartpage.btnCartFirefoxClick();
			System.out.println("out of FF");
		} else if (browserName.equalsIgnoreCase("chrome")) {
			homepage.CartValidation();
			Assert.assertEquals(homepage.txtChromeAddedToCartValidation.getText(), "Added to Cart");
			cartpage.btnCartChromeClick();
			System.out.println("Out of chrome");
		}

		System.out.println("Title of the page is : " + driver.getTitle());
		System.out.println("URL of the page is : " + driver.getCurrentUrl());
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void fetchCartdetails() throws InterruptedException {

		//String iPhoneModel = "Apple iPhone 14 (128 GB) - Starlight";
		CartPage cartpage = new CartPage(driver);
		// Fetching the text in Cart for the product selected
		Boolean match = cartpage.verifyProductDisplay(strProduct);
		Assert.assertTrue(match);

		// Fetch the product price
		System.out.println("The product cost is: " + cartpage.productCost());

		// Fetch the subTotal price
		System.out.println("The total cost displayed in cart is: " + cartpage.TotalPrice());

		// Update dropdown value for quantity and fetch the dropdown value
		System.out.println("Earlier Quantity is: " + cartpage.Quantity());
		cartpage.DropDownClick();
		cartpage.UpdateDropDown();
		System.out.println("Updated Quantity is: " + cartpage.Quantity());

		// Getting the cost based on quantity selected
		int quantity = Integer.parseInt(cartpage.Quantity());
		float Price = Float.parseFloat(cartpage.productCost().replaceAll(",", ""));
		float totalPrice = quantity * Price;
		System.out.println("UpdatedPrice: " + totalPrice);

		Thread.sleep(7000);
		float pricePay = Float.parseFloat(cartpage.TotalPrice().replaceAll(",", ""));
		System.out.println("Price to be paid: " + pricePay);
		Assert.assertEquals(pricePay, totalPrice);

	}
}
