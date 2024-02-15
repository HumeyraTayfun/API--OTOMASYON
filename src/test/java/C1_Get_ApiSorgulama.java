import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C1_Get_ApiSorgulama {

    /*
    https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
     gonderdigimizde donen Response’un,
  status code’unun 200,
 ve content type’inin application/json; charset=utf-8,
 ve Server isimli Header’in degerinin Cowboy,
 ve status Line’in HTTP/1.1 200 OK
 ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.


     API testlerinde genelde işlemler 4 aşamada gerçekleşir
     1. endpoint belrlenerek hazırlanır
     2.gerekli ise expected data hazırlanır
     3.actual data kaydedilir
     4.assertion işlemi yapılır
     */

    @Test
    public void get01(){
        // 1. endpoint belrlenerek hazırlanır
        String url="https://restful-booker.herokuapp.com/booking/10";

        //2.gerekli ise expected data hazırlanır
        //3.actual data kaydedilir

        Response response=given().when().get(url);

      //  response.prettyPrint();

        System.out.println("status code:" + response.getStatusCode());
        System.out.println("content type:"+ response.getContentType());
        System.out.println("status line" + response.getStatusLine());
        System.out.println("header/server:" + response.getHeader("Server"));
        System.out.println("test time:" + response.getTime()+"ms");
    }
}
