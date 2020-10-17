package insta;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Unfollow {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe") ;
	}
	public static void main(String[] args) throws InterruptedException {
		//credentials
		String username="kuntal7875";
		String password="kuntal123";
		String url="https://www.instagram.com/?hl=en";
		
		int unfollowCounter = 0;

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//login
		driver.findElement(By.xpath("//input[@aria-label='Phone number, username, or email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@aria-label='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(5000);
		String title = driver.getTitle();
		for(int i=0;i<2;i++) {
			if(title.equalsIgnoreCase("Instagram")) {
				driver.findElement(By.xpath("//button[.='Not Now']")).click();
				Thread.sleep(3000);
			}
		}

		//go to profile
		driver.findElement(By.xpath("//div[@class='MWDvN  nfCOa']/div[3]/div/div[5]//img[@data-testid='user-avatar']")).click();
		driver.findElement(By.xpath("//a[@class='-qQT3']/div[.='Profile']")).click();

		driver.findElement(By.xpath("(//span[@class='g47SY '])[3]")).click();

		//unfollow
		Thread.sleep(3000);
		 List<WebElement> unfollow = driver.findElements(By.xpath("//li[@class='wo9IH']/div/div[2]/button[.='Following']"));
//		int x = unfollow.size();
//		System.out.println(x+" total people following you.");
//		for(int i=x;i>=10;i--) {
//			WebElement uf=unfollow.get(i);
//			Thread.sleep(2000);
//			uf.click();
//			driver.findElement(By.xpath("//button[.='Unfollow']")).click();
//			unfollowCounter++;
//		}
		
		for(WebElement uf:unfollow) {
			Thread.sleep(2000);
			uf.click();
			driver.findElement(By.xpath("//button[.='Unfollow']")).click();
			unfollowCounter++;
		}
		System.out.println(unfollowCounter+" were unfollowed.");
		
		driver.findElement(By.xpath("(//div[@class='QBdPU '])[2]")).click();//click on close

		//logout
		driver.findElement(By.xpath("//div[@class='MWDvN buoMu nfCOa']/div[1]/a")).click();	//go to homepage
		driver.findElement(By.xpath("//div[@class='MWDvN  nfCOa']/div[3]/div/div[5]//img[@data-testid='user-avatar']")).click();
		driver.findElement(By.xpath("//div[@class='-qQT3']/div[.='Log Out']")).click();
		driver.close();
	}
}
