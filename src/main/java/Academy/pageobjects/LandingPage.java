package Academy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	 WebDriver driver;

	public LandingPage(WebDriver driver)  // CONSTRUCTOR FOR INITIALIZING DRIVER
	{        
		super(driver); // send driver from child to parent 
		this.driver = driver;
		PageFactory.initElements(driver, this); // initializing here as constructor is the first method to execute
	}

	//WebElement userId = driver.findElement(By.id("userEmail"));

	//Page Factory to initialize locators
	
	@FindBy(id="userEmail")  // at runtime it will be executed as driver.find... with the help og intiELements
	WebElement userId ;
	
	@FindBy(id="userPassword")
	WebElement userPassword ;
	
	@FindBy(css="input[class='btn btn-block login-btn']")
	WebElement submit ;
	
	@FindBy(css="[class*='flyInOut']") //Forgot Password Validation
	WebElement errorMessage ;  
	
	
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userId.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver); // initializing object here
		return productCatalogue; //Driver object creation within page object classes encapsulating it from tests
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	
	
}
