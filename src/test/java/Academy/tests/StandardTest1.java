package Academy.tests;

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

import Academy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandardTest1 {

	public static void main(String[] args) 
	{
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anumore\\Documents\\webdriver\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
		
		
		// above can be replaced with webdrivermanager from mvn repository
		String productName = "ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		
		
	//--------------------------------------------------------------------	
		LandingPage landingPage = new LandingPage(driver); // creating object of landing page class so that driver can be used in landing page class from here
		
	//---------------------------------------------------------------
		
		
		driver.findElement(By.id("userEmail")).sendKeys("anurodhmore18@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Anurodh18$");
		driver.findElement(By.cssSelector("input[class='btn btn-block login-btn']")).click();
		System.out.println(driver.getTitle());
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  // Explict wait
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector("div[class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']"));
//// can locate with any one of the class mentioned above eg mb-3
//
//		for(int i=0; i<products.size();i++)
//		{
//			String name = products.get(i).getText();
//			
//			if(name.contains("ZARA COAT 3"))
//			{
//				driver.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
//			}
//			
//		}
		
//	-----------	It can also be acheived using java streams ----------------------------------
		
	WebElement prod =	products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();  // find add to cart button with help of prod variable
	
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	// wait till loading icons disappears
	
//	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			
	driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
	
	//Verify product added is appearing in the cart
	
	// parent to child css to locate comman locator for all products in the cart =div.cartSection h3
	// xpath for the same = //*[@class='cartSection']/h3
	
	List<WebElement> cartProducts = driver.findElements(By.cssSelector("div.cartSection h3"));
	
//	cartProducts.stream().filter(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	// anyMatch will match any item with that text and it returns boolean value
	
	Boolean match =  cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	
	driver.findElement(By.cssSelector(".totalRow [class='btn btn-primary']")).click();  //also can use = .totalRow button
	
	
//	button[class='ta-item list-group-item ng-star-inserted'] span
	
//	driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
//	
//	List<WebElement> values = driver.findElements(By.cssSelector("button[class='ta-item list-group-item ng-star-inserted'] span"));
//	
//	values.stream().filter(value->value.getText().equals("India")).findFirst().orElse(null).click();
	
//xpath - (button[contains(@class,'ta-item')])[2] -- to find India
	// We can use actions class
	
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class='ta-results list-group ng-star-inserted']")));
	
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	
//	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("btnn action__submit ng-star-inserted")));
	driver.findElement(By.cssSelector("a.action__submit")).click();
	
	
	
	
	 String confirmMessage = driver.findElement(By.className("hero-primary")).getText();
	 Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
	driver.close();
	
	
	
	
	}

}
