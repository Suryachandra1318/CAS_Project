package PageObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class timesheets_Page extends Base_Page{

	public timesheets_Page(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//label[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH_LBL']")
	WebElement searchLabel;
	
	@FindBy(xpath = "//*[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH']")
	WebElement searchBox;
	
	@FindBy(xpath = "//div[@class='ps_box-link timesheet_period']")
	List<WebElement> dates;
	
	@FindBy(xpath = "//*[@id='CTS_TS_LAND_WRK_DATE_LBL']")
	WebElement Date;
	
	@FindBy(xpath = "//*[@id='CTS_TS_LAND_WRK_DATE']")
	WebElement Datebox;
	
	@FindBy(xpath = "//*[@id='CTS_TS_LAND_WRK_SEARCH$span']")
	WebElement searchButton;
	
	@FindBy(xpath = "//*[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']" )
	WebElement StatusDrpDown;
	
	@FindBy(xpath = "//*[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']//option")
	List<WebElement> options;
	
	@FindBy(xpath = "//*[@id='#ICOK']")
	WebElement OKBtn; 
	
	@FindBy(xpath = "//*[@id='CTS_TS_LAND_PER_DESCR30$0']")
	WebElement Result;
	
	@FindBy(xpath = "//div[@class='ps_box-group psc_layout timesheet_period_group_box']//div[2]")
	List<WebElement> Results;
	
	@FindBy(xpath = "//*[@id='CTS_TS_LAND_PER_CTS_TS_STATUS_LAND$0']")
	WebElement ResultMessage;
	
	@FindBy(xpath = "//div[@class='ps_box-group psc_layout timesheet_period_group_box']//div[2]")
	WebElement ResultMsg; 
	
	public void verify_NavigationTo_TimesheetPage() {
		
		Set<String>id = driver.getWindowHandles();
		List<String> winid = new ArrayList<String>(id);
		for (int i =0;i<winid.size();i++) {
			String title=driver.switchTo().window(winid.get(i)).getTitle();
			if(title.equals("Timesheet Landing Component")) {
				System.out.println("navigated to Time sheet Page");
				break;
			}
		}	
	}
	
	public void compareTopThreeWeeks() throws ParseException {
		
		for(int i=0;i<3;i++) {
			String d =dates.get(i).getText();
			String givenStartDate = d.substring(0,11);
			String givenEndDate = d.substring(15,26);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		    Date startDate = sdf.parse(givenStartDate);
	        Date endDate = sdf.parse(givenEndDate);
	        
	        // Verify whether start date is Saturday or not
	        Calendar startCalendar = Calendar.getInstance();
	        startCalendar.setTime(startDate);
	        if (startCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
	        	System.out.println("Start date is a Saturday");
	        }
	        else {
	        	System.out.println("Start date is not a Saturday");
	        }
	        // Verify whether end date is Friday or not
	        Calendar endCalendar = Calendar.getInstance();
	        endCalendar.setTime(endDate);
	        if (endCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
	        	System.out.println("End date is a Friday");
	        } 
	        else {
	        	System.out.println("End date is not a Friday");
	        }
	        
	        // Verify whether start and end together form a complete week
            if (startCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && endCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            	System.out.println("Start and end together form a complete week with start as saturday and end as friday");
            }
            else {
            	System.out.println("Start and end together does not form a complete week");
            }	
		}	
	}
	public void verifySearchAndClickDate() {
		WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement SearchLabel = myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH_LBL']")));
		if(SearchLabel.isDisplayed()) {
			System.out.println("Search is displayed");
			Select sc = new Select(searchBox);
			sc.selectByVisibleText("Date");
		}
		else {
			System.out.println("Search is not displayed");
		}
	}
	public void verifyDateAndClick() {
	
		WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String DateLabel = myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='CTS_TS_LAND_WRK_DATE_LBL']"))).getText();
		if(DateLabel.equals("Date")) {
			System.out.println("Date is displayed");
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String curDate = dateFormat.format(currentDate);
			Datebox.sendKeys(curDate);
		}
		else {
			System.out.println("Date box is not displayed");
		}
	}
	
	public void click_SearchButton() {
		searchButton.click();
	}
	
	public void verifyCurrentWeekDetails() throws InterruptedException {
		
		   SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		   SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		   LocalDate now=LocalDate.now();
		   System.out.println(now);
		   Thread.sleep(5000);   
		   WebElement d=dates.get(0);
		   String text1=d.getText().substring(0,11).toLowerCase();
		   String text2=d.getText().substring(15,d.getText().length()).toLowerCase();
		   
		   try {
		   Date date = formatter.parse(text1);
		   String date1=formatter2.format(date);
		   LocalDate loc=LocalDate.parse(date1);
		   Date dates = formatter.parse(text2);
		   String date2=formatter2.format(dates);
		   LocalDate loc1=LocalDate.parse(date2);
		   System.out.println(loc+" "+loc1);
		 
		   if ((now.isAfter(loc)||now.isEqual(loc)) && (now.isBefore(loc1)||now.isEqual(loc1))) {
		      System.out.println("its current week");
		       }
		   }catch(Exception e) {   
		   }
		   if (dates.size()>1) {
			   System.out.println("More weeks are displayed");
		   }
		   else {
			   System.out.println("only current week displayed");
		    }
	}
	
	public void selectStatus (){
		Select sc = new Select(searchBox);
		sc.selectByVisibleText("Status");
		System.out.println("Status is Selected");
	}
	
	public void checkOkBtn() {
		try{
			if(OKBtn.isDisplayed()) {
				OKBtn.click();
				System.out.println("Ok button is Displayed");
				String ResultMsg= Result.getText();
				System.out.println(ResultMsg);
				}
		}
		catch(Exception e) {	
		}
	}
	
	public void checkStatus() {
		try {
			int count =0;
			for(int i=0; i<Results.size(); i++) {
				String result1 = Results.get(i).getText();
				if(result1.equals("result1")){
					if (count ==0) {
						count++;
						System.out.println("Displaying only " + result1);
						}
					}
				else {
					System.out.println("Displaying other than " + result1);
					Assert.fail();
				}
			}	
		}
		
		catch(Exception E) {	
		}
	}
	
	public void verify_Approved() throws InterruptedException {
		
		Select sc1 = new Select(StatusDrpDown);
		sc1.selectByVisibleText("Approved");
		System.out.println("Approved");
		searchButton.click();
		Thread.sleep(3000);
		try {
			int count =0;
			for(int i=0; i<Results.size(); i++) {
				String result1 = Results.get(i).getText();
				if(result1.equals("Approved")){
					if (count ==0) {
						count++;
						System.out.println("Displaying only Approved");
						}
					}
				else {
					System.out.println("Displaying other than Approved");
					Assert.fail();
				}
			}
		}
		catch(Exception e) {
			checkOkBtn();
		
		}
	}
	
	public void verify_Overdue() throws InterruptedException {
		
		Select sc1 = new Select(StatusDrpDown);
		sc1.selectByVisibleText("Overdue");
		System.out.println("Overdue");
		searchButton.click();
		Thread.sleep(3000);
		try{
			checkOkBtn();
		}
		catch(Exception e) {
		checkStatus();
		}
	}
	
	public void verify_PartiallyApproved() throws InterruptedException {
		
		Select sc1 = new Select(StatusDrpDown);
		sc1.selectByVisibleText("Partially Approved");
		System.out.println("Partially Approved");
		searchButton.click();
		Thread.sleep(3000);
		try{
			checkOkBtn();
		}
		catch(Exception e) {
		checkStatus();
		}
	}
	
	public void verify_Pending() throws InterruptedException {
		
		Select sc1 = new Select(StatusDrpDown);
		sc1.selectByVisibleText("Pending");
		System.out.println("Pending");
		searchButton.click();
		Thread.sleep(3000);
		try {
			int count =0;
			for(int i=0; i<Results.size(); i++) {
				String result1 = Results.get(i).getText();
				if(result1.equals("Pending")){
					if (count ==0) {
						count++;
						System.out.println("Displaying only Pending");
						}
					}
				else {
					System.out.println("Displaying other than Pending");
					Assert.fail();
				}
			}
		}
			
		catch(Exception e) {
			checkOkBtn();
		}
	}
	
	public void verify_Saved() throws InterruptedException {
		
		Select sc1 = new Select(StatusDrpDown);
		sc1.selectByVisibleText("Saved");
		System.out.println("Saved");
		searchButton.click();
		Thread.sleep(3000);
		try{
			checkOkBtn();
		}
		catch(Exception e) {
		checkStatus();
		}
	}
	
	public void verify_SentBackforRevision() throws InterruptedException {
		
		Select sc1 = new Select(StatusDrpDown);
		sc1.selectByVisibleText("Sent Back for Revision");
		System.out.println("Sent Back for Revision");
		searchButton.click();
		Thread.sleep(3000);
		try{
			checkOkBtn();
		}
		catch(Exception e) {
		checkStatus();
		}
	}
	
	public void verify_SubmittedforApproval() throws InterruptedException {
		
		Select sc1 = new Select(StatusDrpDown);
		sc1.selectByVisibleText("Submitted for Approval");
		System.out.println("Submitted for Approved");
		searchButton.click();
		Thread.sleep(3000);
		try {
			int count =0;
			for(int i=0; i<Results.size(); i++) {
				String result1 = Results.get(i).getText();
				if(result1.equals("Submitted for Approval")){
					if (count ==0) {
						count++;
						System.out.println("Displaying only Submitted for Approval");
						}
					}
				else {
					System.out.println("Displaying other than Submitted for Approval");
					Assert.fail();
				}
			}
		}
		catch(Exception e) {
			checkOkBtn();
		}
	}
	
	/*
	public void verifyEveryStatus() throws InterruptedException {
		for (int i=1; i<options.size();i++) {
			if (i == 4) {
				
				String optionPresent = options.get(i).getText();
				Select sc1 = new Select(StatusDrpDown);
				sc1.selectByVisibleText(optionPresent);
				searchButton.click();
				Thread.sleep(3000);
				String msg = ResultMsg.getText();
				System.out.println(msg);
				if(msg.equals("Pending")) {
					System.out.println("Displaying only Pending");
				}
				else {
					System.out.println("Displaying other than Pending");
				}
			}
			
			else if (i==7) {
				
				String optionPresent = options.get(i).getText();
				Select sc1 = new Select(StatusDrpDown);
				sc1.selectByVisibleText(optionPresent);
				searchButton.click();
				Thread.sleep(3000);
				String msg = ResultMsg.getText();
				System.out.println(msg);
				if(msg.equals("Submitted for Approval")) {
					System.out.println("Displaying only Submitted for Approval");
				}
				else {
					System.out.println("Displaying other than Submitted for Approval");
				}
			}
				
			
			
			else {
				
				String optionPresent = options.get(i).getText();
				Select sc1 = new Select(StatusDrpDown);
				sc1.selectByVisibleText(optionPresent);
				System.out.println(optionPresent);
				searchButton.click();
				
				try{
					if(OKBtn.isDisplayed()) {
						OKBtn.click();
						String ResultMsg= Result.getText();
						System.out.println(ResultMsg);
						}
				}
				
				catch(Exception E) {
					int count =0;
					for(int j=0; j<Results.size(); j++) {
						String result1 = Results.get(i).getText();
						if(result1.equals(optionPresent)){
							if (count ==0) {
								count++;
								System.out.println("Displaying only " + optionPresent);
								}
							}
						else {
							System.out.println("Displaying other than" + optionPresent);
						}
					}	
				}	
			}
		}
	}
	*/
}

