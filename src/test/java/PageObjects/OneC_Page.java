package PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OneC_Page extends Base_Page {

	public OneC_Page(WebDriver driver) {
		super(driver);
	}
	
	//Search_Box
	@FindBy(xpath = "//*[@id='oneC_searchAutoComplete']")
	WebElement SearchBox;
		
	//View TimeSheet
	@FindBy(xpath = "//*[@id=\"newSearchQALST\"]/div[2]/div/div[2]")
	WebElement timesheetBtn;
		
	public void verifyNavigationToOneCognizant() {
		
		Set<String>id = driver.getWindowHandles();
		List<String> winid = new ArrayList<String>(id);
		for (int i =0;i<winid.size();i++) {
			String title=driver.switchTo().window(winid.get(i)).getTitle();	
			if (title.equals("OneCognizant")) {
				
				break;
			}
			System.out.println("navigated to one cognizant");
		}
	}
		
	public void click_SearchBtn() {
		SearchBox.sendKeys("timesheet");
	}
		
	public void click_ViewTimeSheetBtn() {
		timesheetBtn.click();
	}
}
