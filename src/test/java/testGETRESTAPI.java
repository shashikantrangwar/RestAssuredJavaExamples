import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.runner.Request;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testGETRESTAPI {


    @Test
    public void testGetReq() {
        RestAssured.baseURI = "http://testurl.in/api/users";
        //Request Object
        RequestSpecification reqSpec = RestAssured.given();
        //Response Object
        Response newResponse = reqSpec.request(Method.GET,"");
        System.out.println("Reponse Body is"+newResponse.getBody().asString());
        System.out.println("Status code="+newResponse.statusCode());
        Assert.assertEquals(newResponse.statusCode(),200);
        String statusLine = newResponse.getStatusLine();

    }


    @Test
    public void testPostReq()
    {
        RestAssured.baseURI = "https://testurl.in";

        //Request Object
        RequestSpecification reqSpec = RestAssured.given();
        //Request Payload
        JSONObject reqParams = new JSONObject();
        reqParams.put("FirstName","King");
        reqParams.put("LastName","Prince");
        reqParams.put("UName","King");
        reqParams.put("pwd","Prince");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.header("Content-Type","application/json");
        reqSpec.body(reqParams);
        Response newResponse = reqSpec.request(Method.POST,"/api/users");
        System.out.println("Reponse Body is"+newResponse.getBody().asString());
        System.out.println("Status code="+newResponse.statusCode());
        Assert.assertEquals(newResponse.statusCode(),201);
        String statusLine = newResponse.getStatusLine();
        String t =  newResponse.jsonPath().get("SuccessCode");
        newResponse.getHeader("Content-Type");
        newResponse.getHeader("Server");
        newResponse.header("Content-Encoding");
         Headers h =  newResponse.headers();
         while(h.iterator().hasNext())
             System.out.println("item"+h.toString());
    }

    @Test
    public void testGetBodyCheck()
    {
        RestAssured.baseURI = "https://testurl.in";

        //Request Object
        RequestSpecification reqSpec = RestAssured.given();
        //Request Payload
        JSONObject reqParams = new JSONObject();
        reqParams.put("FirstName","King");
        reqParams.put("LastName","Prince");
        reqParams.put("UName","King");
        reqParams.put("pwd","Prince");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.header("Content-Type","application/json");
        reqSpec.body(reqParams);
        Response newResponse = reqSpec.request(Method.GET,"/api/users");
        JsonPath j = newResponse.jsonPath();
        Assert.assertEquals(j.get("FirstName"),"King");
    }

    @Test
    public void testGetBodyCheckAuth()
    {
        PreemptiveBasicAuthScheme pAuth = new PreemptiveBasicAuthScheme();
        pAuth.setUserName("abc");
        pAuth.setPassword("password");
        RestAssured.authentication=pAuth;
        RestAssured.baseURI = "https://testurl.in";
        //Request Object
        RequestSpecification reqSpec = RestAssured.given();
        //Request Payload
        Response newResponse = reqSpec.request(Method.GET,"/api/users");

    }

    @Test(dataProvider = "dataSet1")
    public void testDataDrivenTest(String name,String lname)
    {
       System.out.println("name="+name+"  lname="+lname);

    }
    @DataProvider(name="dataSet1")
    Object[][] getData()
    {
        String k[][]={{"King","Prince"},{"Kaka","Chacha"}};
        return k;
    }


}