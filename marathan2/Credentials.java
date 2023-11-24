package marathan2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.sukgu.Shadow;

public class Credentials {

	public static void main(String[] args) {
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://dev148908.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("en7BCyr1@QG-");
		driver.findElement(By.id("sysverb_login")).click();
		
		Shadow obj = new Shadow(driver);
		obj.setImplicitWait(15);
		obj.findElementByXPath("//div[text()='All']").click();
				
		obj.setImplicitWait(10);
		obj.findElementByXPath("//span[text()='Service Catalog']").click();
		WebElement frame = obj.findElementByXPath("//iframe[@id='gsft_main']");
	    driver.switchTo().frame(frame);
	    
	    driver.findElement(By.xpath("//a[text()='Hardware']")).click();
	    driver.findElement(By.xpath("//span[text()='Ma']")).click();
	    driver.findElement(By.xpath("//button[@id='oi_add_to_cart_button']")).click();
	    
	    String subTotal = driver.findElement(By.xpath("//td[@class='sc_cart_subtotal_value sc_cart_cell_right']")).getText();
	    System.out.println("subTotal: "+subTotal);
	    
	    driver.findElement(By.xpath("//button[@id='catalog_cart_proceed_checkout']")).click();
	    String reqNum = driver.findElement(By.xpath("//a[@id='requesturl']")).getText();
	    System.out.println("RequestNumber: "+reqNum);
	    String Totalprice = driver.findElement(By.xpath("//td[@class='checkoutTotalSum']")).getText();
	    System.out.println("Total Price: "+Totalprice);
	    
	    if(subTotal.contains(Totalprice)) {
	    	System.out.println("Both are Same.");
	    }else {
	    	System.out.println("Both are Different(Not Same.)");
	    }
	    
	    
		//span[text()='Service Catalog']

	}

}
