package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    static String access_token = "Bearer BQDPfntbYRRqLuaTzD_okw7O4CAj9zzyNiYydFOLwC5lh9_m5fhvazfiGAPFihsvNpj8l2uj7gts5PPVNwAtAp65Oyc9ts8OcnduBHkSbSiPe5p6nIJW4KrSK30nEVTx8gL-i-O2xtA8XJM2pmso1lX7xZ7jiqrEYXcxJaXPUFvrQWGgUXiB8FgSqs2nnP64c4ih5pIZ3dT3Kez1o9hUy51b2BD7RefyLjhbKdnKYh7hWdsjOW530W0R2YFIKJZC6V539-EOJM22nUL4";


    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com/").
                setBasePath("/v1").
                addHeader("Authorization", "Bearer" + access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).build();


    }

    public static ResponseSpecification getResponseSpec (){

        return new ResponseSpecBuilder().
                log(LogDetail.ALL).build();

    }

}

