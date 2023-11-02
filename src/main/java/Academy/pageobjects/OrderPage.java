package Academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Academy.AbstractComponents.AbstractComponent;


public class OrderPage extends AbstractComponent {

	WebDriver driver;
	
	
	@FindBy(css="tr td:nth-child(3) ") 
	List<WebElement> productNames;
	 
	@FindBy(css=".totalRow [class='btn btn-primary']") 
	WebElement checkoutEle;

	public OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this); // initializing here as constructor is the first method to execute
	}

	public Boolean verifyOrderDisplay(String productName)
    {
		Boolean match = productNames.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
    }
	
	
}
