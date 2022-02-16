import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllBears {

    @BeforeTest
    public static void setup() {
        RestAssured.baseURI = "https://api.punkapi.com/v2/beers";
    }

    @Test
    public void ShouldGetAllBears() {
        assertApiResponce(given().when().get().then().statusCode(200));
    }
    @Test
    public void ShouldGetAllBearsBeforeDate(){
        assertApiResponce(given()
                .when()
                .get("?brewed_before=<10-2011>")
                .then()
                .statusCode(200)
                .log().body());
    }
    @Test

    public void ShouldGetAllBeersAbvGreater(){
        assertApiResponce(given()
                .when()
                .get("?abv_gt=6")
                .then()
                .statusCode(200)
                .log().body());
    }
@Test
    public void ShouldVerifyPagenation(){
        assertApiResponce(given()
                .when().get("?page=2&per_page=5").then().statusCode(200)
                .body("", Matchers.hasSize(5)).log().body());
}
private ValidatableResponse assertApiResponce(ValidatableResponse body){
        return body
                .body("id",Matchers.notNullValue())
                .body("name",Matchers.notNullValue())
                .body("description",Matchers.notNullValue())
                .body("abv",Matchers.notNullValue());
}
}