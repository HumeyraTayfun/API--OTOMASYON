import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C2_Get_ResponseBilgileriAssertion {

    /*
   https://restful-booker.herokuapp.com/booking/83 url'ine bir GET request
   gonderdigimizde donen Response'un,
   status code'unun 200,
   ve content type'inin application/json; charset=utf-8, ve Server isimli Header'in degerinin Cowboy,
   ve status Line'in HTTP/1.1 200 OK olduğunu assert ediniz.
*/

    @Test
    public void get01(){

        //API testlerinde genelde işlemler 4 aşamada gerçekleşir
        //1. endpoint belrlenerek hazırlanır
        String url=" https://restful-booker.herokuapp.com/booking/1";

       // 2.gerekli ise expected data hazırlanır
       // 3.actual data kaydedilir
        Response response=given().when().get(url);
       // 4.assertion işlemi yapılır
        response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8").header("Server","Cowboy").statusLine("HTTP/1.1 200 OK");



    }
}
