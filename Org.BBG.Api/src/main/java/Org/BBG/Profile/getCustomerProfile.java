package Org.BBG.Profile;

import static com.jayway.restassured.RestAssured.given;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import Org.BBG.Org.BBG.Api.BaseClass;

public class getCustomerProfile extends BaseClass {
	public int Id_customer;
	public String Url;
	String respon;
	WebDriver driver;

	@SuppressWarnings("static-access")
	@Test
	public void GetCustomerProfile() throws Exception {
		connectWithDataBase();
		dataBaseQuery("select id_customer from customer");

		while (resultSet.next()) {
			Id_customer = resultSet.getInt(1);
			given().param("Id_customer").when()
					.get("http://preprodapi.bigbazaar.co.in/api/v1/customer/" + Id_customer + "/profile").then()
					.assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().status();
			RestAssured r = new RestAssured();
			respon = r.get("http://preprodapi.bigbazaar.co.in/api/v1/customer/" + Id_customer + "/profile").asString();

			Reporter re = new Reporter();
			re.log(respon);

			// result("http://preprodapi.bigbazaar.co.in/api/v1/customer/" +
			// Id_customer + "/profile");
		}

	}
}
