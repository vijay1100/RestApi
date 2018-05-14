package Org.BBG.Org.BBG.Api;

import static com.jayway.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

public class AddCart {
	String uname;
	Response res;
	WebDriver driver;
	Float latitude;
	Float longitude;

	@Test
	public void postAddCart() throws Exception {

		Connection conn = null;

		// Object of Statement. It is used to create a Statement to execute the
		// query
		Statement stmt = null;

		// Object of ResultSet => 'It maintains a cursor that points to the
		// current row in the result set'
		ResultSet resultSet = null;
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Open a connection
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bbgennxt", "root", "vijay");

		// Execute a query
		stmt = conn.createStatement();

		resultSet = stmt.executeQuery("select store_latitude,store_longitude from store_master");

		while (resultSet.next()) {
			latitude = resultSet.getFloat(1);
			longitude = resultSet.getFloat(2);

			Response res = given().param("latitude", "longitude").when()
					.get("http://preprodapi.bigbazaar.co.in/api/v1/stores/pickup/" + latitude + "/" + longitude);
			String responceData = res.asString();
			System.out.println(responceData);
		}

		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://preprodapi.bigbazaar.co.in/api/v1/stores/pickup/" + latitude + "/" + longitude);

		String currentWindow = driver.getWindowHandle();
		driver.switchTo().window(currentWindow);
		Actions search = new Actions(driver);
		search.sendKeys(Keys.chord(Keys.CONTROL, "+a")).build().perform();
		search.sendKeys(Keys.chord(Keys.CONTROL, "+c")).build().perform();
		driver.close();
		Thread.sleep(3000L);
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://jsonviewer.stack.hu/");
		driver.findElement(By.id("edit")).sendKeys(Keys.chord(Keys.CONTROL, "+v"));

		driver.findElement(By.className("x-tab-strip-text ")).click();
		Thread.sleep(2000L);
		driver.findElement(By.xpath(".//*[@class='x-tree-root-ct x-tree-lines']/li/ul/li[3]/div/img[1]")).click();
		driver.manage().window().maximize();
	}

}
