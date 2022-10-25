
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class projecttest1 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException,NoSuchElementException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection ct =DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","root");//localhost is our system ,3306 port num,database name, username of mysql,pwd
		Statement st = ct.createStatement();

		
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");//key is browser,value chrome driver.exe
		WebDriver ch = new ChromeDriver();//webdriver is interface,chrome driver is class
		ch.get("https://www.amazon.com/");
		ch.manage().window().maximize();
		WebElement searchBox=ch.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("Lenovo Mobiles");
		WebElement searchButton=ch.findElement(By.id("nav-search-submit-button"));
		searchButton.click();
		List<WebElement> links = ch.findElements(By.cssSelector("span[class='a-size-medium a-color-base a-text-normal']"));
		String[] linksize=new String[links.size()];
		int i=0;
		for(WebElement e:links){
			linksize[i]=e.getText();
			i++;
		}
		
		for(String t:linksize) {
			System.out.println("Product: "+t);
			ch.findElement(By.linkText(t)).click();
			try {
			String Price1=ch.findElement(By.cssSelector("span[class='a-price-whole']")).getText();
			st.executeUpdate("insert into productprice (ProductName,Price) values ('"+t+"','"+Price1+"')");
			System.out.println("Price: "+Price1);
			System.out.println("URL is valid");
			}catch(Exception NoSuchElementException) {
				try {
				String Price2=ch.findElement(By.cssSelector("span[class='a-price a-text-price a-size-medium apexPriceToPay']")).getText();
				System.out.println("Price: "+Price2);
				st.executeUpdate("insert into productprice (ProductName,Price) values ('"+t+"','"+Price2+"')");
				System.out.println("URL is valid");
				}catch(Exception e) {
					
						System.out.println("Price:Null");
						System.out.println("URL is valid");
					
				}
			}
			finally {
		
				ch.navigate().back();
				
			}
		}
			
			ResultSet rs = st.executeQuery("select * from productprice");
			while (rs.next()) {
				System.out.println(rs.getString("ProductName"));
				System.out.println(rs.getString("Price"));
				
			}
			
			
			
			//String Price1=ch.findElement(By.linkText(t)).findElement(By.cssSelector("span[class='a-price-whole']")).getText();
			
		

	}

}
