package com.spotify.oauth2.Utilties;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
        return "Playlist " + faker.regexify("^[a-zA-Z0-9]{10}$");
    }


    public static String generateDescription(){
        Faker faker = new Faker();
        return "Description " + faker.regexify("^[a-zA-Z0-9]{50}$");
    }
}
