package automationtesting.SeleniumFrameWorkDesign.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationtesting.SeleniumFrameWorkDesign.abstractcomponent.AbstractComponent;

public class CheckOutProducts extends AbstractComponent{
	
	public WebDriver driver;
	
	
	public CheckOutProducts(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProd;
	
	@FindBy(css=".subtotal button")
	WebElement checkOut;
	
	public List<WebElement> cartProducts(){

		return cartProd;
	}
	
	public Boolean getProdFromCart(String productName) {
		
	boolean match = cartProd.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentPage goToCheckOut() {
		checkOut.click();
		return new PaymentPage(driver);
	}
	

}
