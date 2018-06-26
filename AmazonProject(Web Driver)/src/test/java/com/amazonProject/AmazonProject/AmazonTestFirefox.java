package com.amazonProject.AmazonProject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;


public class AmazonTestFirefox 
    extends TestCase
{
	private WebDriver driver; 
	

	@BeforeClass
	public void testSetUp() {
		
		
	//Load the firefox driver
		
		System.setProperty("webdriver.gecko.driver","geckodriver-v0.21.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		
	//Redirect to the amazon home website
		String appURL = "https://www.amazon.com/. ";
		driver.get(appURL);

	//Get title of the home page
		String getTitle = driver.getTitle();
		System.out.println(getTitle);
		
	//Verify title making use of assert statement
		Assert.assertEquals(getTitle, "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
		WebElement element ;
		
	//Making use of xpath to find the search Box
		element = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")); 
		element.click();
		element.clear();
		
	//Entering the input for the search box
		element.sendKeys("Ender's Game by Orson Scott Card.");
		
	//xpath to locate the search button
		element = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div[3]/div/form/div[2]/div/input"));
		element.click();
		
	//xpath to click on the book link
		element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div[2]/div/div[4]/div[1]/div/ul/li[1]/div/div[3]/div[1]/a/h2"));
		element.click();

	//Locating the add to cart button making use of id as a selector
		element = driver.findElement(By.id("add-to-cart-button")); 
		element.click();
		
	//Making use of Assert to check whether the product is added to cart
		String cartConfirmation = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div/div/div/div[2]/div/div/div/div/div/h1")).getText();
		String actualCartConfirmation="Added to Cart";
		Assert.assertEquals(cartConfirmation, actualCartConfirmation);
		
	//Locating the add to cart button using Id
		driver.findElement(By.id("hlb-view-cart-announce")).click();
		
	//Making use of Assert to check whether the correct product is added to the cart
		String itemInCartActual="Ender's Game (The Ender Quintet)" ;
		String itemInCartExpected=driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[4]/div/div[2]/div[4]/form/div[2]/div/div[4]/div/div[1]/div/div/div[2]/ul/li[1]/span/a/span"))
				.getText();
		Assert.assertEquals(itemInCartExpected, itemInCartActual);

	//Locating the Delete link making use of xpath
		element=driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[4]/div/div[2]/div[4]/form/div[2]/div/div[4]/div/div[1]/div/div/div[2]/div/span[1]/span/input"));
		element.click();
		
	//Making use of Assert to check whether the correct product is deleted from the cart
		String itemDeletedActual="Ender's Game (The Ender Quintet) was removed from Shopping Cart.";
		String itemDeletedExpected=driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[4]/div/div[2]/div[4]/form/div[2]/div/div[3]/div[1]/span")).getText();
		Assert.assertEquals(itemDeletedExpected, itemDeletedActual);
	}
	
	public AmazonTestFirefox( String testName )
    {
        super( testName );
    }

   
    public static Test suite()
    {
        return new TestSuite( AmazonTestFirefox.class );
    }

    
    public void testApp()
    {
        assertTrue( true );
    }
}
