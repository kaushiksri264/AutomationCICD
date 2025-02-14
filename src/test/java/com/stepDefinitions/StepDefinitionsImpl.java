package com.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.CheckOutProducts;
import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.ConfirmationPage;
import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.LoginDetails;
import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.PaymentPage;
import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.ProductCatalog;
import automationtesting.SeleniumFrameWorkDesign.testcomponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsImpl extends BaseTest {
	
	public LoginDetails loginDetails;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;
	
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		loginDetails = launchApplication();
	}
	
	//(.+) using regular expression to match any string
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		
		productCatalog = loginDetails.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		
		List<WebElement> products = productCatalog.getProductList();
		
		productCatalog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		
		CheckOutProducts checkOutProd = productCatalog.goToCartPage();
		
		
		//Find Product in check out and proceed
		//List<WebElement> cartProducts = checkOutProd.cartProducts();
		
		boolean match = checkOutProd.getProdFromCart(productName);
		
		AssertJUnit.assertTrue(match);
		//PaymentProcessing
		PaymentPage paymentPage = checkOutProd.goToCheckOut();
		
		paymentPage.selectCountry("United States");
		
		confirmationPage = paymentPage.submitOrder();
			
	}
	
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String message = confirmationPage.getSuccessMessage();
		AssertJUnit.assertTrue(message.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void somethind_message_is_displayed(String strArg1) throws Throwable{
		
		AssertJUnit.assertEquals(strArg1,loginDetails.getErrorMessage());
		driver.close();
	}
}