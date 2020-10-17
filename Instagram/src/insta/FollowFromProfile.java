package insta;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FollowFromProfile {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe") ;
	}
	public static void main(String[] args) throws InterruptedException {
		//credentials
		String username="kuntal7875";
		String password="kuntal123";
		
		String url="https://www.instagram.com/?hl=en";

		String NameOfProfile="technicalguruji";

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//login
		driver.findElement(By.xpath("//input[@aria-label='Phone number, username, or email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@aria-label='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(3000);
		String title = driver.getTitle();
		for(int i=0;i<2;i++) {
			if(title.equalsIgnoreCase("Instagram")) {
				driver.findElement(By.xpath("//button[.='Not Now']")).click();
				Thread.sleep(3000);
			}
		}

		//search bar
		driver.findElement(By.xpath("//div[@class='MWDvN  nfCOa']/div[2]/input")).sendKeys(NameOfProfile);

		//click on profile
		driver.findElement(By.xpath("(//div[@class='fuqBx']/a)[1]")).click();

		//first post
		driver.findElement(By.xpath("(//div[@class=' _2z6nI']/article/div/div/div/div/a/div/div[2])[1]")).click();


		try {
			driver.findElement(By.xpath("(//button[@class='sqdOP yWX7d     _8A5w5    '])[2]")).click();//others btn	
		}
		catch(Exception e){
			driver.findElement(By.xpath("//div[@class='Nm9Fw']/button")).click();//like btn
		}

		//loop for following many people
		for(int i=0;i<4;i++) {

			//follow
			Thread.sleep(3000);
			List<WebElement> follow = driver.findElements(By.xpath("//div[@class='                    Igw0E   rBNOH        eGOV_     ybXk5    _4EzTm                                                                                   XfCBB          HVWg4                 ']/div[3]/button[.='Follow']"));
			int x = follow.size();
			System.out.println("You followed "+x+" people.");
			for(WebElement f:follow) {
				f.click();
			}

			//"We restrict certain activity to protect our community" popup
			Thread.sleep(500);
			try {
				driver.findElement(By.xpath("//button[.='OK']")).click();
			}
			catch(Exception e) {}
			driver.findElement(By.xpath("(//button[@class='wpO6b ']/div[@class='QBdPU '])[18]")).click();//close popup:follow
		}

		driver.findElement(By.xpath("(//button[@class='wpO6b ']/div[@class='QBdPU '])[19]")).click();//close popup:post

		//logout
		driver.findElement(By.xpath("//div[@class='MWDvN buoMu nfCOa']/div[1]/a")).click();	//go to homepage
		driver.findElement(By.xpath("//div[@class='MWDvN  nfCOa']/div[3]/div/div[5]//img[@data-testid='user-avatar']")).click();
		driver.findElement(By.xpath("//div[@class='-qQT3']/div[.='Log Out']")).click();
		driver.close();
	}
}

