package amazonEcom.tests;


import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import amazonEcom.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		LandingPage landingpage= new LandingPage(driver);
		WebDriverWait wait = new WebDriverWait (driver,Duration.ofSeconds(10));
		String iPhoneModel ="Apple iPhone 12 (64GB) - Blue";
		Actions action = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//div[@id='navbar']/div[@id='nav-belt']//div[@class='nav-right']//a[@id='nav-link-accountList']"));
		action.moveToElement(ele).perform();

		driver.findElement(By.xpath("//div[@id='nav-flyout-ya-signin']//a[@class='nav-action-signin-button']//span[@class='nav-action-inner']")).click();
		driver.findElement(By.cssSelector("input#ap_email")).clear();
		driver.findElement(By.cssSelector("input#ap_email")).sendKeys("shrutigreen@gmail.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#continue")));
		driver.findElement(By.cssSelector("input#continue")).click();
		driver.findElement(By.cssSelector("#ap_password")).clear();
		driver.findElement(By.cssSelector("#ap_password")).sendKeys("Amazon@123");
		driver.findElement(By.cssSelector("input#signInSubmit")).click();
	
		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iPhone" + Keys.ENTER);
		System.out.println("Title of the page is : " + driver.getTitle());
		System.out.println("URL of the page is : " + driver.getCurrentUrl());

		List<WebElement> options = driver.findElements(By.className("a-text-normal"));
		WebElement prod = options.stream().filter(product->product.getText().equalsIgnoreCase(iPhoneModel)).findFirst().orElse(null);
		prod.click();
		
		
		
		
		/*
		 * System.out.println(options.size()); for (WebElement iPhone : options) { //
		 * System.out.println(iPhone.getText()); if
		 * (iPhone.getText().equalsIgnoreCase(iPhoneModel)) { iPhone.click();
		 * System.out.println("Title of the page is : " + driver.getCurrentUrl());
		 * break; } }
		 */	try {
			Assert.assertEquals(driver.getTitle(), "Amazon.in : iPhone");
			System.out.println("New tab Found");
		} catch (AssertionError e) {
			System.out.println("New tab not found");
		}

		Thread.sleep(15000);

	

// Inside the tab window
	
		

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentId = it.next();
		String childId = it.next();
		System.out.println(parentId +" gap "+ childId);
		driver.switchTo().window(childId);
		Thread.sleep(5000);
		List<WebElement> addtoCartButton = driver.findElements(By.xpath("//input[@id='add-to-cart-button']"));
		if (addtoCartButton.size() == 0) {
			System.out.println("AddToCart btn not present");
		} else {
			System.out.println("AddToCart button is present :" +addtoCartButton.size()+ " times");
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		
		//Action: Click on 'Add To Cart' button and validate the text -> "Added to Cart"
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#attachDisplayAddBaseAlert div.a-box-inner h4.a-alert-heading")));
		//		Thread.sleep(20000);
		String txtAtCValidation = driver.findElement(By.cssSelector("div#attachDisplayAddBaseAlert div.a-box-inner h4.a-alert-heading")).getText();
		System.out.println(txtAtCValidation);
		System.out.println("waited for 30 secs");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals(txtAtCValidation, "Added to Cart");
		
		//Action: Click on "Cart" button to view the cart.
		driver.findElement(By.xpath("//span[@id='attach-sidesheet-view-cart-button']/span[@class='a-button-inner']/input[@class='a-button-input']")).click();
		
		//Fetching the text in Cart for the product selected
		List <WebElement> cartProducts = driver.findElements(By.cssSelector("#activeCartViewForm .a-size-base-plus .a-truncate"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(iPhoneModel));
		Assert.assertTrue(match);
		
		System.out.println("line 109 *********************************************************************");
		
		// Fetch the product price
		String productPrice = driver.findElement(By.xpath("//div[@class='sc-badge-price-to-pay']//p[@class='a-spacing-mini']//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")).getText().trim();
		System.out.println(productPrice);

		//Fetch the subTotal price
		String subTotal=driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")).getText();
		System.out.println(subTotal);

		//Fetch the dropdown value for quantity
		String qty = driver.findElement(By.xpath("//span[@class='a-button-inner']//span[@class='a-dropdown-prompt']")).getText();
		System.out.println(qty);
	
		//Update dropdown value for quantity and fetch the dropdown value 
		WebElement dropDown = driver.findElement(By.xpath("//*[@class='a-button-text a-declarative']//span[2]"));
		dropDown.click();
		driver.findElement(By.cssSelector("#quantity_3")).click();
			String updatedQty = driver.findElement(By.xpath("//span[@class='a-button-inner']//span[@class='a-dropdown-prompt']")).getText().trim();
		System.out.println("Quantity post selection from DropDown: " +updatedQty);
		
	//Getting the cost based on quantity selected
		int quantity = Integer.parseInt(updatedQty);
		float Price=	Float.parseFloat(productPrice.replaceAll(",", ""));
		float totalPrice = quantity * Price;
		System.out.println("UpdatedPrice: "+ totalPrice);

		Thread.sleep(10000);
String costValidation = driver.findElement(By.xpath("//div[@data-name='Subtotals']/span[2]/span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")).getText().trim();
float pricePay = Float.parseFloat(costValidation.replaceAll(",",""));
System.out.println("Price to be paid: "+pricePay);
Assert.assertEquals(pricePay, totalPrice);
driver.close();

		}
}
	
	/* Negative case
	 * Actions action = new Actions(driver); WebElement ele =
	 * driver.findElement(By.xpath(
	 * "//div[@id='navbar']/div[@id='nav-belt']//div[@class='nav-right']//a[@id='nav-link-accountList']"
	 * )); action.moveToElement(ele).perform(); Thread.sleep(2000);
	 * driver.findElement(By.xpath(
	 * "//div[@id='nav-flyout-ya-signin']//a[@class='nav-action-signin-button']//span[@class='nav-action-inner']"
	 * )).click();
	 */
	
}