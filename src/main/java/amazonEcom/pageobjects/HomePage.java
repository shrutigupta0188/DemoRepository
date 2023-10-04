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
	
//	@FindBy(xpath="//input[@id='add-to-cart-button']")
//	WebElement btnAddToCart;
	
	@FindBy(css="input#add-to-cart-button.a-button-input")
    WebElement btnAddToCart;
	
	@FindBy(css="div#attachDisplayAddBaseAlert div.a-box-inner h4.a-alert-heading")
	public WebElement txtChromeAddedToCartValidation;
	
	//	@FindBy(css="span.a-size-medium-plus a-color-base sw-atc-text a-text-bold")
	@FindBy(css="div#NATC_SMART_WAGON_CONF_MSG_SUCCESS span.sw-atc-text")
	public WebElement txtFirefoxAddedToCartValidation;
	
	
	public void productSearch(String product) throws InterruptedException
	{
		Thread.sleep(3000);
		searchBox.clear();
		searchBox.sendKeys(product);
	}
	
	By ProductsBy = By.className("a-text-normal");
	//Waiting for the products to appear after searching.
	public List<WebElement> returnProducts() {
		waitForElementToAppear(ProductsBy);
		return products;
	}
	
	//Search the specific product (iphone) and returns as WebElement
	By ProdSearchResults = By.xpath("//span[contains(text(),'Results')]");
	public WebElement getProductByName(String productName) {
		waitForElementToAppear(ProdSearchResults);
		WebElement prod = returnProducts().stream().filter(product->product.getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	
	public void clickProductFromList(String productName) {
		getProductByName(productName).click();
		
	}
	
	//By BtnAddCart = By.xpath("//input[@id='add-to-cart-button']");
	By BtnAddCart = By.cssSelector("input#add-to-cart-button.a-button-input");
	// Main action to add the product to cart.
	public void addProductToCart() throws InterruptedException {
	    Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		Thread.sleep(2000);
		waitForElementToAppear(BtnAddCart);
		btnAddToCart.click();
	}
	
	By txtAddToCartBy = By.cssSelector("div#attachDisplayAddBaseAlert div.a-box-inner h4.a-alert-heading");
	public void CartValidation() {
		waitForElementToAppear(txtAddToCartBy);
	}
	
}
