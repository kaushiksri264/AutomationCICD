package automationtesting.SeleniumFrameWorkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.CheckOutProducts;
import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.ConfirmationPage;
import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.OrdersPage;
import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.PaymentPage;
import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.ProductCatalog;
import automationtesting.SeleniumFrameWorkDesign.testcomponents.BaseTest;

public class EnterLoginDetails extends BaseTest{
	
	
		@Test(dataProvider= "getData", groups= {"PurchaseOrder"})
		public void submitOrder(HashMap<String,String> input) throws IOException {
			
			
		//Login using user and password
		ProductCatalog productCatalog = loginDetails.loginApplication(input.get("email"), input.get("password"));
		
		
		//add product to cart
		
		List<WebElement> products = productCatalog.getProductList();
		
		productCatalog.addProductToCart(input.get("productName"));
		
		CheckOutProducts checkOutProd = productCatalog.goToCartPage();
		
		
		//Find Product in check out and proceed
		//List<WebElement> cartProducts = checkOutProd.cartProducts();
		
		boolean match = checkOutProd.getProdFromCart(input.get("productName"));
		
		AssertJUnit.assertTrue(match);
		//PaymentProcessing
		PaymentPage paymentPage = checkOutProd.goToCheckOut();
		
		paymentPage.selectCountry("United States");
		
		ConfirmationPage confirmationPage = paymentPage.submitOrder();
			
		String message = confirmationPage.getSuccessMessage();
		AssertJUnit.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

		}
		
		@Test(dependsOnMethods = {"submitOrder"})
		public void orderHistoryTest() {
			String productName = "IPHONE 13 PRO";
			
			ProductCatalog productCatalog = loginDetails.loginApplication("tonystark15@gmail.com", "tonyStark1$");
			
			OrdersPage ordersPage = productCatalog.goToOrders();
			
			Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
			
		}
		
		
		
		@DataProvider
		public Object[][] getData() throws IOException {
			
//			HashMap<String,String> map = new HashMap<>();
//			
//			map.put("email","tonystark15@gmail.com");
//			map.put("password","tonyStark1$");
//			map.put("productName","IPHONE 13 PRO");
//			
//			HashMap<String,String> map1 = new HashMap<>();
//			
//			map1.put("email","cap15@gmail.com");
//			map1.put("password", "capAm123$");
//			map1.put("productName","ADIDAS ORIGINAL");
			
			
			List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//main//java//automationtesting//SeleniumFrameWorkDesign//data//PurchaseOrder.json");
			return new Object[][] {{data.get(0)}, {data.get(1)}};
		}
	}   
	
