package automationtesting.SeleniumFrameWorkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.CheckOutProducts;
import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.ProductCatalog;
import automationtesting.SeleniumFrameWorkDesign.testcomponents.BaseTest;
import automationtesting.SeleniumFrameWorkDesign.testcomponents.Retry;

public class ErrorValidationTest extends BaseTest{

	
		@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
		public void LoginErrorValidation() throws IOException {
			
			
		//Login using user and password
		loginDetails.loginApplication("tonystark@gmail.com", "tonyStark1$");
		AssertJUnit.assertEquals("Incorrect email or password.",loginDetails.getErrorMessage());
		
		}
		
		@Test
		public void productErrorValidation() throws IOException {
			
			
		//Login using user and password
		ProductCatalog productCatalog = loginDetails.loginApplication("cap15@gmail.com", "capAm123$");
		
		
		//add product to cart
		String productName = "IPHONE 13 PRO";
		
		List<WebElement> products = productCatalog.getProductList();
		
		productCatalog.addProductToCart(productName);
		
		CheckOutProducts checkOutProd = productCatalog.goToCartPage();
		
		boolean match = checkOutProd.getProdFromCart("IPHONE 13 PRO");
		
		Assert.assertFalse(match);
		

		}
	}   
	
