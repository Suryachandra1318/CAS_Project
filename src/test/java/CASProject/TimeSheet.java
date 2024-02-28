/*
Project Description

Navigate to Be.Cognizant portal
Verify user information
Verify one cognizant is displayed under apps and tools or not and click it 
Verify it navigated to one cognizant or not
Enter timesheet in search box
Click on "submit timesheet"
Verify it navigated to timesheet module or not
Compare last 3 weeks time sheet details with system calender whether they are matching or not
Verify the search box field is displayed or not and click on date
Check date field is displayed or not and click on today's date and click search button
Verify whether current week details only displaying or not
Click on status in search drop-down
Click each item in status drop-down and click on search button
Verify Whether it's displaying only corresponding status information or not
*/
package CASProject;

import java.text.ParseException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import PageObjects.Home_Page;
import PageObjects.OneC_Page;
import PageObjects.timesheets_Page;
import TestBase.Base_Class;

public class TimeSheet extends Base_Class {
	
//	public static WebDriver driver;
//	//setting up the WebDriver
//	@BeforeClass
//	public void driverSetUp(){
//		driver = new ChromeDriver();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		//opening the URL
//		driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
//		driver.manage().window().maximize();
//		
//		try {
//		driver.findElement(By.name("passwd")).sendKeys("@Surya#1509CTS");
//		driver.findElement(By.id("idSIButton9")).click();
//		WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idSIButton9"))).click();
//		//driver.findElement(By.id("idSIButton9")).click();
//		}
//		catch(Exception e) {	
//		}
//	}
	
	
	
	@Test(priority = 0)
	public void VerifyUserDetails() {
		
		Home_Page hp = new Home_Page(driver);
		hp.click_MyAccBtn();
		hp.verifyUser();
	}
	
	@Test(priority = 1)
	public void ScrollDown(){
		
		Home_Page hp = new Home_Page(driver);
		hp.scrolldown();
	}
	
	@Test(priority = 2)
	public void openCognizant() throws InterruptedException{
		
		Home_Page hp = new Home_Page(driver);
		hp.click_OneCBtn();
		
	}
	
	@Test(priority = 3)
	public void VerifyOneCPage() {
		
		OneC_Page op = new OneC_Page(driver);
		op.verifyNavigationToOneCognizant();	

	}
	
	@Test(priority = 4)
	public void OpenTimeSheet() throws InterruptedException {
		
		OneC_Page op = new OneC_Page(driver);
		op.click_SearchBtn();
		op.click_ViewTimeSheetBtn();
		Thread.sleep(5000);
	}

	@Test(priority = 5)
	public void VeerifytimeSheetPage() {
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.verify_NavigationTo_TimesheetPage();

	}
	
	@Test(priority = 6)
	public void verifyTopThreeWeeks() throws ParseException {
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.compareTopThreeWeeks();

	}
	
	@Test(priority = 7)
	public void VerifyCurrentWeek() throws InterruptedException {
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.verifySearchAndClickDate();
		tp.verifyDateAndClick();
		tp.click_SearchButton();
		tp.verifyCurrentWeekDetails();
	}
	
	@Test(priority = 8)
	public void SelectStatus() {
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.selectStatus();
	}
	
	@Test(priority = 9)
	public void VerifyApproved() throws InterruptedException{
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.verify_Approved();
	}
	
	@Test(priority = 10)
	public void VerifyOverDue() throws InterruptedException{
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.verify_Overdue();
	}
	
	@Test(priority = 11)
	public void VerifyPartiallyApproved() throws InterruptedException{
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.verify_PartiallyApproved();
	}
	
	@Test(priority = 12)
	public void VerifyPending() throws InterruptedException{
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.verify_Pending();
	}
	
	@Test(priority = 13)
	public void VerifySaved() throws InterruptedException{
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.verify_Saved();

	}
	
	@Test(priority = 14)
	public void VerifySentForRevision() throws InterruptedException{
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.verify_SentBackforRevision();
	}
	
	@Test(priority = 15)
	public void VerifySubmittedForApproval() throws InterruptedException{
		
		timesheets_Page tp = new timesheets_Page(driver);
		tp.verify_SubmittedforApproval();
	}
	
	//closing the browser
	@AfterClass
	public void tearDown() {
		driver.quit();
		
	}
}
