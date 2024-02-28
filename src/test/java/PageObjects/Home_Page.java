package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home_Page extends Base_Page{

	public Home_Page(WebDriver driver) {
		super(driver);
	}
	
	//myAccount_button 
	@FindBy(xpath = "//div[@id='O365_HeaderRightRegion']")
	WebElement myAccount_button;
	
	
	//verify OneCognizant Button
	@FindBy(xpath = "//*[@id='QuicklinksItemTitle']")
	List<WebElement> links;  
	
	@FindBy(xpath="//*[@id='QuicklinksItemTitle']")
	WebElement App;
	
	
	public void click_MyAccBtn() {
		myAccount_button.click();
	}
	
	public void verifyUser() {
		WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String userName = myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mectrl_currentAccount_primary']"))).getText();
		if(userName.isEmpty()) {
			System.out.println("No user found");
		}
		else{
			System.out.println(userName);
		}
			
		String user_Email = myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mectrl_currentAccount_secondary']"))).getText();
		if(user_Email.isEmpty()) {
			System.out.println("No user found");
		}
		else{
			System.out.println(user_Email);
		}
		
	}
	
	public void scrolldown() {
    	JavascriptExecutor js= (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView()",App);
	}
	
	public void click_OneCBtn() throws InterruptedException {
		
		
		for (int i =0;i<links.size();i++) {
			String linkName= links.get(i).getText().toString();
			if (linkName.equals("OneCognizant")) {
				System.out.println(linkName);
				links.get(i).click();
				Thread.sleep(3000);
				System.out.println(linkName + "is present");
				
			}
		}
	}
	
}