package Org.BBG.store;

import static com.jayway.restassured.RestAssured.given;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;
import Org.BBG.Org.BBG.Api.BaseClass;
public class getNearByDeliveryStore extends BaseClass {
	String uname;
	Response res;
	WebDriver driver;
	Float latitude;
	Float longitude;

	@Test(description = "Testing the web service whether success 200 has been received")
	

	public void GetNearByDelivryStore() throws Throwable {

		connectWithDataBase();
		dataBaseQuery("select store_latitude,store_longitude from store_master");

		while (resultSet.next()) {
			latitude = resultSet.getFloat(1);
			longitude = resultSet.getFloat(2);

			Response res = given().param("latitude", "longitude").when()
					.get("http://preprodapi.bigbazaar.co.in/api/v1/stores/pickup/" + latitude + "/" + longitude);
			System.out.println(res.statusCode());
			System.out.println(res.statusLine());

			// String responceData = res.asString();
		}

		result("http://preprodapi.bigbazaar.co.in/api/v1/stores/pickup/" + latitude + "/" + longitude);
	}

}
