package SeleniumTest_SP.SeleniumTest_SP;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.utility.RandomString;

public class Utils 
{
	WebDriver theDriver;
	WebDriverWait maxWait;
	
	public Utils(WebDriver driver, WebDriverWait maxWt)
	{
		theDriver = driver;
		maxWait = maxWt;	
	}
	
	public void clickElement(WebElement theElement)
	{		
		try
		{
			theElement.click();
		}
		catch(Exception e)
		{
			System.err.println("CouldNotClickWebElement: " + theElement.getAttribute("name"));
			System.err.println(e.toString());
		}		
	}
	
	public WebElement findElement(By locator)
	{
		WebElement theElement = null;
		
		try
		{
			theElement = maxWait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch(Exception e)
		{
			System.err.println("CouldNotIdentifyWebElementWithLocator: " + locator.toString());
			System.err.println(e.toString());
		}
		
		if (!theElement.isDisplayed())
			scrollToElement(theElement);
		
		return theElement;
	}
	
	public String generateRandomString()
	{
		RandomString rand = new RandomString();
		String random = rand.toString();
		String finalStr = "";
		
		try
		{
			finalStr = random.substring(random.indexOf('@') + 1);
		}
		catch (Exception e)
		{
			System.err.println("NotAbleToGenerateRandomString");
			System.err.println(e.toString());
		}		
		
		return finalStr;
	}
	
	public void hoverMouse(WebElement theElement)
	{
		Actions action  = new Actions(theDriver);
				
		try
		{
			action.moveToElement(theElement).perform();
		}
		catch(Exception e)
		{
			System.err.println("CouldNotHoverMouseOverWebElement: " + theElement.getAttribute("name"));
			System.err.println(e.toString());
		}
	}
	
	public void scrollToElement(WebElement element)
	{
		try
		{
		((JavascriptExecutor)theDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		}
		catch (Exception e)
		{
			System.err.println("CouldNotScrollToWebElement: " + element.getAttribute("name"));
			System.err.println(e.toString());
		}
	}
	
	
}
