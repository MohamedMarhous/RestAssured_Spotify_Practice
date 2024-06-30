package com.spotify.oauth2.api;


import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.RouteEndPointsConstants.API;
import static com.spotify.oauth2.api.RouteEndPointsConstants.TOKEN;


import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestAPIsResource {



    public static Response post (String path , String token , Object requestPlaylist){

        return  given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization" , "Bearer " + token).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response postAccount(HashMap<String , String> formParams){

        return  given(getAccountRequestSpec()).
                formParams(formParams).
                log().all().
                when().post(API + TOKEN).
                then().spec(getResponseSpec()).
                extract().
                response();


    }


    public static Response get(String path , String token){

        return given(getRequestSpec()).
                header("Authorization" , "Bearer " + token).
                when().get(path).
                then().spec(getResponseSpec()).extract().response();

    }

    public static Response update (String path,String token ,Object requestPlaylist){


       return given(getRequestSpec()).
                body(requestPlaylist).
               header("Authorization" , "Bearer " + token).
                when().put(path).
                then().spec(getResponseSpec()).extract().response();

    }
}
