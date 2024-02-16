import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.*;

public class C6_Post_ResponseBodyTesti {
    /*
https://jsonplaceholder.typicode.com/posts url'ine asagidaki body ile bir POST request gonderdigimizde
        {
        "title":"API",
        "body":"API ogrenmek ne guzel",
        "userId":10,
        }
        donen Response'un,
        status code'unun 201,
        ve content type'inin application/json; charset=utf-8
        ve Response Body'sindeki,
         "title"'in "API" oldugunu
        "userId" degerinin 100'den kucuk oldugunu
        "body" nin "API" kelimesi icerdigini test edin.
 */

@Test
    public void PostResponseBodyTesti(){

    String url="https://jsonplaceholder.typicode.com/posts";
    JSONObject requestBody=new JSONObject();
    requestBody.put("title","API");
    requestBody.put("body","API ogrenmek ne guzel");
    requestBody.put("userId",10);

    Response response=given().contentType(ContentType.JSON).when().body(requestBody.toString()).post(url);
    response.then().assertThat().statusCode(201).contentType("application/json").body("title",equalTo("API"))
            .body("userId",lessThan(100)).body("body",containsString("API"));
}
}
