package automationtesting.SeleniumFrameWorkDesign;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import automationtesting.SeleniumFrameWorkDesign.testcomponents.BaseTest;

public class LoginErrorValidation extends BaseTest{

	
		@Test
		public void submitOrder() throws IOException {
			
			
		//Login using user and password
		loginDetails.loginApplication("tonystark@gmail.com", "tonyStark1$");
		AssertJUnit.assertEquals("Incorrect email or password.",loginDetails.getErrorMessage());
		
		}
	}   
	
