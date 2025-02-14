package automationtesting.SeleniumFrameWorkDesign.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationtesting.SeleniumFrameWorkDesign.abstractcomponent.AbstractComponent;

public class OrdersPage extends AbstractComponent{

public WebDriver driver;
	
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css=".subtotal button")
	WebElement checkOut;
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> ordersInCart;
	
	/*
	 * public List<WebElement> orders(){ return ordersInCart; }
	 */
	
	public Boolean verifyOrderDisplay(String productName) {
		
	boolean match = ordersInCart.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
