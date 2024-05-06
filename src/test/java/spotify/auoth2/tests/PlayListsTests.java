package spotify.auoth2.tests;

import io.qameta.allure.*;
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

@Epic("Web interface")
@Feature("Essential features")

public class PlayListsTests {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    String access_token = "Bearer BQDKSO5EZ8U_C7oP4_ash8CIl9OtuoZ5_qn9Pp2Ca6XgMfojqLMrVHYQghd5D-0dd7Bx2NXT3U4xSYVcFgEZAo_nnw6mk3zm_eOXbViHnAIu2LXZ2wnv7WjHlAMR6Ojadybgq0qKh_dNEOnb73B7JgoIG8jwJ1G2_z78jUjMnvfzPaqzoiRJ0hg85__FUGLEScGUHdJplWWzmyjRE_Ncbuq8UcJ9Fx4I0bQeA3AUAHzNbiOvfv4-Ci-JDIcxWnPHI1biy5mr_qCjBUa8";

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

    @Story("Authentication")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    @Description ("1st Spotify Practice Testcase" )
    @Test (description = "Validate create A Playlist Sucessfully")
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

     @Story("Authentication")
     @Description ("2nd Spotify Practice Testcase" )
     @Test(description = "Validate Get A Playlist by ID Successfully")
    public void ValidateGetAPlaylistSuccessfully(){

        given(requestSpecification).
                when().get("/playlists/0dEXBYqwCLCFuabjusmAn5").
                then().spec(responseSpecification).
                assertThat().statusCode(200).body("name" , equalTo("Updated Playlist Name"),"description" , equalTo("Updated playlist description"));;
    }


    @Story("Authentication")
    @Description ("3rd Spotify Practice Testcase" )
    @Test(description = "Validate Update Playlist Name & Description Sucessfully")
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


    @Story("Authentication")
    @Description ("4th Spotify Practice Testcase" )
    @Test (description = "Validate receiving error 400 for creating a Playlist without name")
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
