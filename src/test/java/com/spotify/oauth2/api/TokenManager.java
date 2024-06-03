
package com.spotify.oauth2.api;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public static String getToken() {

        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) {

                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is Valid");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed To Get Token");
        }

        return access_token;


    }


    public static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", "6477452cd59141098d116d7f53b0a0e0");
        formParams.put("client_secret", "47e6c80e1220452fbd601912b15491b1");
        formParams.put("grant_type", "refresh_token");
        formParams.put("refresh_token", "AQBzZVvrC29j2DddKueTIsFRwDVSzis9mwsVSivTAL0Hh-TxQ0fLNZgrrhajJSDSRSsFn9NeQ3jzRz3RKcORDl9Oi4PrKlGIXmiz4N8ZQSSF5CFKVN7TUo3zrLGIwYXUX14");

        Response response = given().
                baseUri("https://accounts.spotify.com").
                formParams(formParams).contentType(ContentType.URLENC).
                log().all().
                when().post("/api/token").
                then().spec(getResponseSpec()).
                extract().
                response();

        if (response.statusCode() != 200) {
            throw new RuntimeException("Renew Token Process Failed");
        }

        return response;

    }


}

