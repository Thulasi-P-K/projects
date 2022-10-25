import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class projectflipkart {
	public static void main(String[] args) throws SQLException, ClassNotFoundException,NoSuchElementException, InterruptedException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection ct =DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","root");//localhost is our system ,3306 port num,database name, username of mysql,pwd
		Statement st = ct.createStatement();

		
		
		System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
		WebDriver ff = new FirefoxDriver();
		ff.get("https://www.flipkart.com/");
		ff.manage().window().maximize();
		ff.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement closebutton = ff.findElement(By.cssSelector("button[class='_2KpZ6l _2doB4z']"));
		closebutton.click();
		WebElement searchBox=ff.findElement(By.className("_3704LK"));
		searchBox.sendKeys("Oneplus Mobiles");
		WebElement searchButton=ff.findElement(By.className("L0Z3Pu"));
		searchButton.click();
		//String url = ff.getCurrentUrl();
	//	String parent = ff.getWindowHandle();
		//System.out.println(s);
		List<WebElement> links = ff.findElements(By.cssSelector("div[class='_4rR01T']"));
		//List<WebElement> price = ff.findElements(By.cssSelector("div[class='_25b18c']"));
		
		
		
		String[] linksize=new String[links.size()];
	//	String[] pricesize=new String[price.size()];
		int i=0;
		for(WebElement e:links){
			linksize[i]=e.getText();
			i++;
		}
	//	WebDriverWait expwait=new WebDriverWait(ff,20);
		
		
		for(String t:linksize) {
			System.out.println("Product: "+t);
			//ff.findElement(By.linkText(t));
			//ff.findElement(By.partialLinkText(t));
			ff.findElement(By.partialLinkText(t)).click();
			//Set<String> Winhandles=ff.getWindowHandles();
			//ff.navigate().forward();
			//String s2 = ff.getWindowHandle();
			//ff.switchTo().window(s2);
		
			//ff.getCurrentUrl();
		
			Thread.sleep(10000);
			//for(String handle:Winhandles) {
				//if(!handle.equalsIgnoreCase(parent)) {
					//ff.switchTo().defaultContent();
			ArrayList<String> w = new ArrayList<String>(ff.getWindowHandles());
			ff.switchTo().window(w.get(1));
			        String Price=ff.findElement(By.cssSelector("div[class='_25b18c']")).getText();
			//st.executeUpdate("insert into productprice2 (ProductName,Price) values ('"+t+"','"+Price+"')");
			        System.out.println("Price: "+Price);
			        st.executeUpdate("insert into productprice1 (ProductName,Price) values ('"+t+"','"+Price+"')");
		
			        System.out.println("URL is valid");
			      
			        ff.close();
				
			//ff.close();
			//ff.switchTo().window(arg0)
			//ff.navigate().back();
		//	ff.navigate().to(url);
			//Thread.sleep(10000);
			ff.switchTo().window(w.get(0));
			
			//ff.switchTo().window(s1);
		
			
			//ff.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Thread.sleep(1000);
			//Thread.sleep(1000);
			//expwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='_25b18c']")));
	         
	         //Thread.sleep(50000);-
			}
		ResultSet rs = st.executeQuery("select * from productprice1");
		while (rs.next()) {
			System.out.println(rs.getString("ProductName"));
			System.out.println(rs.getString("Price"));
		
				
			
		
			//ResultSet rs = st.executeQuery("select * from productprice2");
			//while (rs.next()) {
				//System.out.println(rs.getString("ProductName"));
				//System.out.println(rs.getString("Price"));
				
			//}
			
			
			
			//String Price1=ch.findElement(By.linkText(t)).findElement(By.cssSelector("span[class='a-price-whole']")).getText();
			
		

	}

	}
}

