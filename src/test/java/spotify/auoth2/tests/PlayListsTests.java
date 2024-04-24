package spotify.auoth2.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PlayListsTests {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    String access_token = "Bearer BQB9DoynM7JWG7rKhfx_emRIlZLV3bVduGEnMIQJnbGNST0HbtiTK54LZTSkxdfT3JzwOh88sHfqEbIL5Txs_b39-7PPiKUg9jebG1UQVkWw6NM2wcogd0pz6lOsjRdF7Y4FhywCmyG2Rfm_FYlo0YoMiZGl9G4VYlJwUi8cpwBpEn9mFKxnlcUBefcatYqVOgO2U3MJ4PfZtBqLHRL7D-H0inipFT2hJjZ5qDUCONxQLZt1qeZrsg3_stwLogyILifWpYRwM7hOOeJK";

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com/").
                setBasePath("/v1").
                addHeader("Authorization" , "Bearer" + access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void ValidateCreateAPlaylistSuccessfully(){
      String payload = "{\n" +
              "    \"name\": \"New Playlist 2\",\n" +
              "    \"description\": \"New playlist description\",\n" +
              "    \"public\": false\n" +
              "}";
        given(requestSpecification).
                body(payload).
                    when().post("/users/31daurqyfdtau225xodto45z7mde/playlists").
                then().spec(responseSpecification).
                assertThat().statusCode(201).body("name" , equalTo("New Playlist 2"),"description" , equalTo("New playlist description"));;

    }


     @Test
    public void ValidateGetAPlaylistSuccessfully(){

        given(requestSpecification).
                when().get("/playlists/0dEXBYqwCLCFuabjusmAn5").
                then().spec(responseSpecification).
                assertThat().statusCode(200).body("name" , equalTo("Updated Playlist Name"),"description" , equalTo("Updated playlist description"));;
    }


    @Test
    public void ValidateUpdateAPlaylistSuccessfully(){
     String payload = "{\n" +
        "    \"name\": \"Updated Playlist Name\",\n" +
        "    \"description\": \"Updated playlist description\",\n" +
        "    \"public\": false\n" +
        "}";
        given(requestSpecification).
                body(payload).
                when().put("/playlists/2oEkFhFeybxPs6fb1xvMmK").
                then().spec(responseSpecification).
                assertThat().statusCode(200) ;;
    }


    @Test
    public void Validate_Error_When_Create_APlaylist_Without_Name(){
        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given(requestSpecification).
                body(payload).
                when().post("/users/31daurqyfdtau225xodto45z7mde/playlists").
                then().spec(responseSpecification).
                assertThat().statusCode(400).
                body("error.message" , equalTo("Missing required field: name"));;

    }


}
