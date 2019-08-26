package SeleniumTest_SP.SeleniumTest_SP;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SecPaySimpleTest {
	
	static final String CHROME_DIR = "C:\\chromedriver.exe";
	static final String GOOGLE_AU_URL = "http://www.google.com.au";
	static final String GOOGLE_STRING = "SecurePay";
	static final String SP_URL = "https://www.securepay.com.au";
	static final Integer MAX_WAIT_SEC = 20;
	
	WebDriver driver;
	WebDriverWait maxWait;	
	Utils theUtil;
	
	//@Before   //  this  tag doesn't work with Cucumber/BDD further work reqd
    private void initialize() 
	{
		System.setProperty("webdriver.chrome.driver", CHROME_DIR);
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    maxWait = new WebDriverWait(driver, MAX_WAIT_SEC);	    
	    theUtil = new Utils(driver, maxWait);	    
    }	
	
  @Given("^I open Google AU$")
  public void givenIOpenGoogleAU() throws Throwable 
  {
	  initialize();
	  driver.navigate().to(GOOGLE_AU_URL);
  }
  
  @And("^I search for SecurePay$")
  public void givenISearchForSecurePay() throws Throwable 
  {
	  googleSite(GOOGLE_STRING);
  }
  
  @And("^I bring up the SecurePay website$")
  public void givenIBringUpTheSecurePayWebsite() throws Throwable {
	  bringUpSecPayPage();
  }
  

  @When("^I select the ContactUs section$")
  public void whenISelectTheContactUsSection() throws Throwable {
	  bringUpContactUsPage();
  }

  @Then("^I have the option to fill in several fields$")
  public void thenIHaveTheOptionToFillInSeveralFields() throws Throwable {
	  enterContactUsData();
	  end();
  }
  
  private void bringUpContactUsPage()
  {
		WebElement topMenu = theUtil.findElement(By.id("nav"));
		WebElement supportLink = topMenu.findElement(By.linkText("Support"));
		theUtil.hoverMouse(supportLink);	
		WebElement contactUs = theUtil.findElement(By.cssSelector("a[href*='/contact-us/']"));
		theUtil.clickElement(contactUs);
		maxWait.until(ExpectedConditions.urlContains("contact-us"));
  }

  private void bringUpSecPayPage()
  {
		WebElement spLink = theUtil.findElement(By.cssSelector("a[href *= '" + SP_URL  + "']"));
		theUtil.clickElement(spLink);
		maxWait.until(ExpectedConditions.urlContains(SP_URL));
  }
  
  //later needs to be extended to actually fill all mandatory fields and submit enquiry
  private void enterContactUsData()
  {
		WebElement fNameInput = theUtil.findElement(By.cssSelector("input[name='first-name']"));
		fNameInput.sendKeys(theUtil.generateRandomString());
		
		WebElement lNameInput = theUtil.findElement(By.cssSelector("input[name='last-name']"));
		lNameInput.sendKeys(theUtil.generateRandomString());
		
		WebElement emailInput = theUtil.findElement(By.cssSelector("input[type='email']"));
		emailInput.sendKeys(theUtil.generateRandomString());
		
		WebElement phoneInput = theUtil.findElement(By.cssSelector("input[name='phone-number']"));
		phoneInput.sendKeys(theUtil.generateRandomString());
		
		WebElement companyInput = theUtil.findElement(By.cssSelector("input[name='business-name']"));
		companyInput.sendKeys(theUtil.generateRandomString());		
  }
  
  private void googleSite(String toGoogle) 
  {
		WebElement searchInput = theUtil.findElement(By.cssSelector("input[title='Search']"));
		searchInput.sendKeys(toGoogle);
		
		WebElement returnList = theUtil.findElement(By.cssSelector("ul[role='listbox']"));
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			System.err.println(e.toString());
		}
		
		List<WebElement> returnOptions = returnList.findElements(By.tagName("li"));
		
		if (returnOptions.size() > 0)
		{
			theUtil.clickElement(returnOptions.get(0));
		}
		
		else
		{
			WebElement submitBtn = theUtil.findElement(By.cssSelector("input[value='Google Search']"));
			theUtil.clickElement(submitBtn);
		}
  }

  //@After  //  this  tag doesn't work with Cucumber/BDD further work reqd
  public void end() {
     driver.quit();
  }
}
