package Academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	 WebDriver driver;

	public ProductCatalogue(WebDriver driver)  // CONSTRUCTOR FOR INITIALIZING DRIVER
	{        
		super(driver); // every child class has to serve parent with driver
		this.driver =driver;
		PageFactory.initElements(driver, this); // initializing here as constructor is the first method to execute
	}

	//Page Factory to initialize locators
	
	@FindBy(css="div[class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")  // at runtime it will be executed as driver.find... with the help og intiELements
	List<WebElement> products ;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector("div[class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector("#toast-container");
    
    
    
	
	//ACTION METHODS
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products ;
	}
	
	public WebElement getProductByName(String productName)
	{
		
		WebElement prod =	products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) 
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toast);
		waitForElementToDisappear(spinner);
	}
	
	
	
	
	
	
	
	
}
