package Academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Academy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	 WebDriver driver;

	 
	@FindBy(css="div.cartSection h3") 
	List<WebElement> cartProducts;
	 
	@FindBy(css=".totalRow [class='btn btn-primary']") 
	WebElement checkoutEle;
	 
	public CartPage(WebDriver driver)  // CONSTRUCTOR FOR INITIALIZING DRIVER
	{        
		super(driver); // every child class has to serve parent with driver
		this.driver =driver;
		PageFactory.initElements(driver, this); // initializing here as constructor is the first method to execute
	}
	
	public Boolean verifyProductDisplay(String productName)
    {
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
    }
	
	public CheckoutPage goToCheckout()
	{
	  checkoutEle.click();
	  CheckoutPage checkoutEle = new CheckoutPage(driver);  // initilazing object of next class
	  return checkoutEle;
	 }


	
	
	
}
