package automationtesting.SeleniumFrameWorkDesign.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import automationtesting.SeleniumFrameWorkDesign.pageobjectmodel.LoginDetails;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	
	public LoginDetails loginDetails;
	
	public WebDriver initializerDriver() throws IOException {

		Properties prop = new Properties();
		
		//user.dir get the local file storage source path
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
			       "//src/main//java//automationtesting//SeleniumFrameWorkDesign//resources//GlobalData.properties");


		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			
			//Headless mode
			ChromeOptions options = new ChromeOptions();
			
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) {
				
			options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			
			//Optional and is full screen
			driver.manage().window().setSize(new Dimension(1440,900));

		} else if(browserName.equalsIgnoreCase("FireFox")) {
			
			WebDriverManager.firefoxdriver().setup();
			
			driver = new FirefoxDriver(); 
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();
		
		return driver;
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//Read from json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	
		//String to HashMap - JackSOn databind
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){}); 
		
		return data;
		
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		File file  = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginDetails launchApplication() throws IOException {
		
		driver = initializerDriver();
		
		loginDetails = new LoginDetails(driver);
		
		loginDetails.goToLoginPage();
		
		return loginDetails;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		
		 if (driver != null) {
		        driver.quit();
		    }
	}

}
