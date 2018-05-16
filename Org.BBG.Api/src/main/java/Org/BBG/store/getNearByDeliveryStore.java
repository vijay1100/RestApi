package Org.BBG.store;

import static com.jayway.restassured.RestAssured.given;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import Org.BBG.Org.BBG.Api.BaseClass;

public class getNearByDeliveryStore extends BaseClass {

	Response res;
	WebDriver driver;
	Float latitude;
	Float longitude;

	@Test
	public void GetNearByPickupStore() throws Exception {
		connectWithDataBase();
		dataBaseQuery("select store_latitude,store_longitude from store_master");
		while (resultSet.next()) {
			while (resultSet.next()) {
				latitude = resultSet.getFloat(1);
				longitude = resultSet.getFloat(2);

				given().param("latitude", "longitude").when()
						.get("http://preprodapi.bigbazaar.co.in/api/v1/stores/pickup/" + latitude + "/" + longitude);

				RestAssured r = new RestAssured();
				@SuppressWarnings("static-access")
				String data = r
						.get("http://preprodapi.bigbazaar.co.in/api/v1/stores/pickup/" + latitude + "/" + longitude)
						.asString();
				System.out.println(data);
				result("http://preprodapi.bigbazaar.co.in/api/v1/stores/pickup/" + latitude + "/" + longitude);
				// String responceData = res.asString();
			}

			// result("http://preprodapi.bigbazaar.co.in/api/v1/stores/pickup/"
			// + latitude + "/" + longitude);
		}
	}
}
