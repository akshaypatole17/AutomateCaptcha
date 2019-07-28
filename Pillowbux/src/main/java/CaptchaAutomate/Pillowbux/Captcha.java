package CaptchaAutomate.Pillowbux;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

public class Captcha {

	public static void main(String[] args) throws IOException, TesseractException, InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","F:\\Akshay Study\\Study IT\\Languages, Tools and Utilities\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://pillowbux.com/login");
		
		Thread.sleep(10000);
		
		driver.findElement(By.id("name")).sendKeys("akshaypatole17@gmail.com");
		driver.findElement(By.id("email")).sendKeys("@kshay10");
		driver.findElement(By.className("btn-login")).click();
		
		driver.findElement(By.cssSelector("#account > div > div > div.col-lg-3.col-md-3.col-sm-3.col-xs-12.acct-menu-wrapper > ul > li:nth-child(2) > a")).click();
		driver.findElement(By.className("btn btn-primary")).click();
		
		File src = driver.findElement(By.id("captchaimg")).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/captcha.png";
		
		FileHandler.copy(src, new File(path));
		
		ITesseract image = new Tesseract1();
		
		String imageText = image.doOCR(new File(path));
		
		String finalText = imageText;
		
		System.out.println("Final Captcha is "+finalText);
		
		driver.findElement(By.name("captcha")).sendKeys("finalText");
		
		driver.findElement(By.className("btn btn-primary btn-next ng-scope")).click();
		
		

	}

}
