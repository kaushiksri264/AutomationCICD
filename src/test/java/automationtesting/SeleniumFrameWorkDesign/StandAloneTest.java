package automationtesting.SeleniumFrameWorkDesign;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String args[]) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "IPHONE 13 PRO";

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.manage().window().maximize();
		

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("tonystark15@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("tonyStark1$");

		driver.findElement(By.name("login")).click();
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.xpath("//div[@class='card-body'] /button[2]")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//make loading disappear
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		
		List<WebElement> cartProd = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match = cartProd.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".cartSection button:first-of-type")).click();
		
		
		
		Actions act = new Actions(driver);
		
		act.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "United States").build().perform();
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[1]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		
		

	}   
	

}
