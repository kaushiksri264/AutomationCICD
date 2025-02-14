package automationtesting.SeleniumFrameWorkDesign.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationtesting.SeleniumFrameWorkDesign.abstractcomponent.AbstractComponent;

public class LoginDetails extends AbstractComponent{
	
	public WebDriver driver;
	
	public LoginDetails(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	public void goToLoginPage() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}

	
	//PageFactoryDesginPattern
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement pass;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement invalidDetailsMessage;
	
	public ProductCatalog loginApplication(String email,String password) {
		userEmail.sendKeys(email);
		pass.sendKeys(password);
		submit.click();
		
		ProductCatalog productCatalog = new ProductCatalog(driver);
		
		return productCatalog;
	}
	
	public String getErrorMessage() {
		
		waitForWebelementToAppear(invalidDetailsMessage);
		return invalidDetailsMessage.getText();
	}

}
