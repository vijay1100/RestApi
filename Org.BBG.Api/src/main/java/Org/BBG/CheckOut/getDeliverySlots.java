package Org.BBG.CheckOut;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;

import Org.BBG.Org.BBG.Api.BaseClass;

public class getDeliverySlots extends BaseClass {
	public int store_Code;
	public String respon;

	@SuppressWarnings("static-access")
	@Test
	public void GetDeliverySlots() throws Exception 
	{
		connectWithDataBase();
		dataBaseQuery("select store_code from store_master");

		while (resultSet.next()) {
			store_Code = resultSet.getInt(1);
			given().param("Id_customer").when()
					.get("http://preprodapi.bigbazaar.co.in/api/v1/order/delivery/slots/"+store_Code).then()
					.assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().status();
			RestAssured r = new RestAssured();
			respon = r.get("http://preprodapi.bigbazaar.co.in/api/v1/order/delivery/slots/"+store_Code)
					.asString();
			System.out.println(respon);
			Reporter re = new Reporter();
			re.log(respon);
			result("http://preprodapi.bigbazaar.co.in/api/v1/order/delivery/slots/"+store_Code);
		}
	}
}
