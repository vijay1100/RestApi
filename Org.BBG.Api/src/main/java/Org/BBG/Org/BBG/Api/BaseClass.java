package Org.BBG.Org.BBG.Api;

import static com.jayway.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.jayway.restassured.response.Response;

public class BaseClass {
	WebDriver driver;
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet resultSet = null;
	String uname;
	Response res;
	Float latitude;
	Float longitude;

	public void OpenBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	public void connectWithDataBase() throws Exception 
	{

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bbgennxt", "root", "vijay");

	}

	public void dataBaseQuery(String DataBaseQuery) throws SQLException 
	{
		stmt = conn.createStatement();

		resultSet = stmt.executeQuery(DataBaseQuery);
		// select store_latitude,store_longitude from store_master
	}

	public void result(String Url) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to(Url);

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
