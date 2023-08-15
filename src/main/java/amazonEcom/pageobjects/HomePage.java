package amazonEcom.pageobjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazonEcom.abstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent{
	WebDriver driver;
	public HomePage(WebDriver driver) 
	{
		// Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(id="twotabsearchtextbox")
	WebElement searchBox;

	@FindBy(className="a-text-normal")
	List<WebElement> products;
	
	@FindBy(xpath="//input[@id='add-to-cart-button']")
	WebElement btnAddToCart;
	
	@FindBy(css="div#attachDisplayAddBaseAlert div.a-box-inner h4.a-alert-heading")
	public WebElement txtAddedToCartValidation;
	
	
	
	
	public void ProductSearch(String product)
	{
		searchBox.clear();
		searchBox.sendKeys(product);
	}
	
	By ProductsBy = By.className("a-text-normal");
	
	//Waiting for the products to appear after searching.
	public List<WebElement> returnProducts() {
		waitForElementToAppear(ProductsBy);
		return products;
	}
	
	//Search the specific product (iphone 64GB) and returns as WebElement
	public WebElement getProductByName(String productName) {
		WebElement prod = returnProducts().stream().filter(product->product.getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	
	public void clickProductFromList(String productName) {
		getProductByName(productName).click();
		
	}
	
	//Main action to add the product to cart. 
	public void addProductToCart() {

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentId = it.next();
		String childId = it.next();
		System.out.println(parentId +" gap "+ childId);
		driver.switchTo().window(childId);
		btnAddToCart.click();
	}
	
	By txtAddToCartBy = By.cssSelector("div#attachDisplayAddBaseAlert div.a-box-inner h4.a-alert-heading");
	public void CartValidation() {
		waitForElementToAppear(txtAddToCartBy);
		//Assert.assertEquals(txtAddedToCartValidation.getText(), "Added to Cart");
	
	}
	
}
