
package com.spotify.oauth2.api;


import com.spotify.oauth2.Utilties.ConfigLoader;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;



public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken() {

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
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());

        Response response = RestAPIsResource.postAccount(formParams);

        if (response.statusCode() != 200) {
            throw new RuntimeException("Renew Token Process Failed");
        }

        return response;

    }


}

