package amazonEcom.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazonEcom.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		// Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory

	@FindBy(xpath = "//span[@id='attach-sidesheet-view-cart-button']/span[@class='a-button-inner']/input[@class='a-button-input']")
	WebElement btnCart;

	// Fetching all the webElements in the cart page
	@FindBy(css = "#activeCartViewForm .a-size-base-plus .a-truncate")
	List<WebElement> cartProducts;

	@FindBy(xpath = "//div[@class='sc-badge-price-to-pay']//p[@class='a-spacing-mini']//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")
	WebElement productPrice;
	
	@FindBy(xpath="//span[@id='sc-subtotal-amount-activecart']/span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")
	WebElement subTotal;

	@FindBy(xpath="//span[@class='a-button-inner']//span[@class='a-dropdown-prompt']")
	WebElement dropDownQty;
	
	@FindBy(xpath="//*[@class='a-button-text a-declarative']//span[2]")
	WebElement dropDownOpen;
	
	@FindBy(css="#quantity_3")
	WebElement qtySelectFromDropDown;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public String productCost() {
		String Price = productPrice.getText().trim();
		return Price;
	}

	public void btnCartClick() {
		btnCart.click();
	}

	public String TotalPrice() {
		String Cost = subTotal.getText().trim();
		return Cost;
	}
	
	public String Quantity() {
		String qty = dropDownQty.getText().trim();
		return qty;
			}

	public void DropDownClick() {
		dropDownOpen.click();
	}
	
	public void UpdateDropDown() {
		qtySelectFromDropDown.click();
	}

}
