import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {
    /*
https://restful-booker.herokuapp.com/booking url'ine
asagidaki body'ye sahip bir POST request gonderdigimizde
donen response'un id haric asagidaki gibi oldugunu test edin.

Request body
{
"firstname" : "Hasan",
"lastname" : "Yagmur",
"totalprice" : 500,
"depositpaid" : false,
"bookingdates" : {
    "checkin" : "2021-06-01",
    "checkout" : "2021-06-10"
    },
"additionalneeds" : "wi-fi"
}

Expected Response Body
{
"bookingid":24,
"booking":{
    "firstname":"Hasan",
    "lastname":"Yagmur",
    "totalprice":500,
    "depositpaid":false,
    "bookingdates":{
        "checkin":"2021-06-01",
        "checkout":"2021-06-10"
        },
"additionalneeds":"wi-fi"
}
}
 */

    @Test
    public void postExpectedData(){
        //1. endpoint ve requestbody hazırlama
        String url="https://restful-booker.herokuapp.com/booking";

        JSONObject innerData=new JSONObject();
        innerData.put("checkin","2021-06-01");
        innerData.put( "checkout","2021-06-10");

        JSONObject reqBody=new JSONObject();
        reqBody.put("firstname" , "Hasan");
        reqBody.put("lastname" , "Yagmur");
        reqBody.put("totalprice" , 500);
        reqBody.put("depositpaid" , false);
        reqBody.put( "bookingdates",innerData);
        reqBody.put("additionalneeds","wi-fi");

        //exbody hazırlama
        JSONObject expBody=new JSONObject();
        expBody.put("bookingid",24);
        expBody.put("booking",reqBody);

        //3.request gönder ve response kaydet
        Response response=given().contentType(ContentType.JSON).when().body(reqBody.toString()).post(url);

        //4.assertion
        JsonPath respJP=response.jsonPath();

        assertEquals(expBody.getJSONObject("booking").get("firstname"),respJP.get("booking.firstname"));
        assertEquals(expBody.getJSONObject("booking").get("lastname"),respJP.get("booking.lastname"));
        assertEquals(expBody.getJSONObject("booking").get("totalprice"),respJP.get("booking.totalprice"));
        assertEquals(expBody.getJSONObject("booking").get("depositpaid"),respJP.get("booking.depositpaid"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),respJP.get("booking.bookingdates.checkin"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),respJP.get("booking.bookingdates.checkout"));
        assertEquals(expBody.getJSONObject("booking").get("additionalneeds"),respJP.get("booking.additionalneeds"));



    }
}
