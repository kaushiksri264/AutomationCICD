package automationtesting.SeleniumFrameWorkDesign.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationtesting.SeleniumFrameWorkDesign.abstractcomponent.AbstractComponent;

public class PaymentPage extends AbstractComponent{
	
	public WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[1]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submitPayment;
	
	
	
	
	public void selectCountry(String countryName) {
		Actions act = new Actions(driver);
		
		act.sendKeys(country, countryName).build().perform();
		
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submitPayment.click();
		
		return new ConfirmationPage(driver);
	}
}
