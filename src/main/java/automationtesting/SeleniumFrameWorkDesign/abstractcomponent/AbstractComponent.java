package automationtesting.SeleniumFrameWorkDesign.abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.CheckOutProducts;
import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.OrdersPage;

public class AbstractComponent {
	
	public WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement goToCart;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orders;

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//success message when item was add to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
	}
	
	public void waitForWebelementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//make loading disappear
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CheckOutProducts goToCartPage() {
		goToCart.click();
		CheckOutProducts checkOutProd = new CheckOutProducts(driver);
		return checkOutProd;
	}
	
	public OrdersPage goToOrders() {
		
		orders.click();
		OrdersPage orderPage = new OrdersPage(driver);
		return orderPage;
	}


}
