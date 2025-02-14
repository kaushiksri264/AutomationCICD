package automationtesting.SeleniumFrameWorkDesign.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationtesting.SeleniumFrameWorkDesign.abstractcomponent.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	
	public WebDriver driver;
	
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	//PageFactoryDesginPattern
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	//driver.findElement(By.cssSelector(".ng-animating")
	@FindBy(css=".ng-animating")
	WebElement pageLoader;

	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductName(String productName) {
		WebElement prod = getProductList().stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement product = getProductName(productName); 
		product.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(pageLoader);
	
	}

}
