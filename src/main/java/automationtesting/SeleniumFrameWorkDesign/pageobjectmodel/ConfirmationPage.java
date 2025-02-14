package automationtesting.SeleniumFrameWorkDesign.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationtesting.SeleniumFrameWorkDesign.abstractcomponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

public WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement successMessage;
	
	
	public String getSuccessMessage() {
		
		return successMessage.getText();
	}
	 
}
