package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.RouteEndPointsConstants.BASE_PATH;

public class SpecBuilder2 {

    //static String access_token = "Bearer BQDVWH8JjCGI0NLubt2pnVQbww6yY01OKhn0fzlMpDVSIEY1lJpHEnULXThxttTGPJpB_B042npNRZkihNQhwu7cQLmgaUWLO4qsGwVuETgg_fQU9avEJHq4HxvjiXwi_nfOv-SFjqJdoWcML2IfX6yf6sdcvLMBLQbkPuvywWCzj-hDpyBezBzZ4Fc6AMSUsSMU8AJ3YLWGWc1YTaTG8u41yrSawtZQg27f-MHqvq_JfZZzwc0nDGP4HxSEqc4cUk7rIHUNMGK-_W2P";


    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com/").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).build();


    }


    public static RequestSpecification getAccountRequestSpec() {

        return new RequestSpecBuilder().
                setBaseUri("https://accounts.spotify.com/").
                setContentType(ContentType.URLENC).
                log(LogDetail.ALL).build();


    }

    public static ResponseSpecification getResponseSpec() {

        return new ResponseSpecBuilder().
                log(LogDetail.ALL).build();

    }

}

