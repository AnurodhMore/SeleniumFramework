package Academy.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import Academy.TestComponents.BaseTest;
import Academy.pageobjects.CartPage;
import Academy.pageobjects.CheckoutPage;
import Academy.pageobjects.ConfirmationPage;
import Academy.pageobjects.LandingPage;
import Academy.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})     // using testng instead of public static void main // grouping to run from testng2.xml
	public void loginErrorValidation() throws IOException {

		// String productName = "ZARA COAT 3";

		landingPage.loginApplication("anurodhmore18@gmail.com", "Anuro18$");
		// landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void productErrorValidation()
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		List<WebElement> products = productCatalogue.getProductList();
     	productCatalogue.addProductToCart(productName);
	    CartPage cartPage = productCatalogue.goToCartPage();
	    Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33"); //intentionally failing the test case  (negative scenario)
		Assert.assertFalse(match); 
	}

}
