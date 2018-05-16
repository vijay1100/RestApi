package Org.BBG.Profile;

import static com.jayway.restassured.RestAssured.given;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import Org.BBG.Org.BBG.Api.BaseClass;

public class getCustomerAddresses extends BaseClass {

	public int Id_customer;
	public String Url;
	String respon;
	WebDriver driver;
@SuppressWarnings("static-access")
@Test
	public void GetCustomerAddresses() throws Exception {
		connectWithDataBase();
		dataBaseQuery("select id_customer from customer");

		while (resultSet.next()) {
			Id_customer = resultSet.getInt(1);
			given().param("Id_customer").when()
					.get("http://preprodapi.bigbazaar.co.in/api/v1/customer/" + Id_customer + "/addresses").then()
					.assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().status();
			RestAssured r = new RestAssured();
			respon = r.get("http://preprodapi.bigbazaar.co.in/api/v1/customer/" + Id_customer + "/addresses")
					.asString();
			System.out.println(respon);
			Reporter re = new Reporter();
			re.log(respon);
		}
	}
}
