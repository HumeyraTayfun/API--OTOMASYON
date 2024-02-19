import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C11_Get_ExpectedDataOlusturma {

    /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request yolladigimizda donen response body’sinin
     asagida verilen ile ayni oldugunu test ediniz
  Response body :bu response body bizim expected datamızdır
{
    "userId": 3,
    "id": 22,
    "title": "dolor sint quo a velit explicabo quia nam",
    "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
            um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
}
     */
    @Test
    public void expectedBodyTesti(){

        //1. endpoint ve reqbody hazırlama
        String url="https://jsonplaceholder.typicode.com/posts/22";
        // expected body hazırlama
        JSONObject exBody=new JSONObject();
        exBody.put("userId", 3);
        exBody.put("id", 22);
        exBody.put("title", "dolor sint quo a velit explicabo quia nam");
        exBody.put("body", "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        //3.request gonder response kaydet

        Response response=given().when().get(url);

        // 4.assertion
        //şimdiye kadar kullandıgımız assertThat() methodu ile yapılan testlerdeki gibi assertion yapılabilir
        //frameworrkumuzu gelıştırmek için dinamık bir yapi kazandırabiliriz.bunun için de hard assert kullanabiliriz bunun için JUnit kullanacagız
        JsonPath respJsonPath=response.jsonPath();//cevabı al jsonpathe kaydet demek
        Assert.assertEquals(exBody.get("userId"),respJsonPath.get("userId"));
        Assert.assertEquals(exBody.get("id"),respJsonPath.get("id"));
        Assert.assertEquals(exBody.get("title"),respJsonPath.get("title"));
        Assert.assertEquals(exBody.get("body"),respJsonPath.get("body"));

    }


}
