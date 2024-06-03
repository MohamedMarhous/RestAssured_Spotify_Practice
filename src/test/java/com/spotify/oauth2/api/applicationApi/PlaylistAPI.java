package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestAPIsResource;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.TokenManager.getToken;



public class PlaylistAPI {

    //static String access_token = "Bearer BQB3s9lyUsEP5KIdoZibz1r7tp6RN0lVo3MhyzmJxtx9KJsqUyo1sddyFZfDRJs-zyJKrJvT5-3ZAY92EzoF7xR1v8TdfcFBMJwyHmXKhSUQ7S6dQgmTJ-J9ALgd2LZ4jlhWignylmlqFGHC-296Zv3OnugPdTlES1YJ1FzmG8vldZefdm6X_ku2Vq8B23qo4H3vGk-N2h7OopFSPPsSapfnSfBg78b8BXnu6D6LpBBElCIG_VPInOefALMrea1WTD1sY_gTBQYbfXN_";


    public static Response post (Playlist requestPlaylist){
        return RestAPIsResource.post("/users/31daurqyfdtau225xodto45z7mde/playlists" , getToken() , requestPlaylist);

       /* return  given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization" , "Bearer" + access_token).
                when().post("/users/31daurqyfdtau225xodto45z7mde/playlists").
                then().spec(getResponseSpec()).
                extract().response();*/
    }

  public static Response post (String token ,Playlist requestPlaylist){
        return RestAPIsResource.post("/users/31daurqyfdtau225xodto45z7mde/playlists" , token , requestPlaylist);


    }

    public static Response get (String playlistId){

        return RestAPIsResource.get("/playlists/" + playlistId , getToken() );


    }

    public static Response update (String playlistId ,Playlist requestPlaylist){
        return RestAPIsResource.update("/playlists/" +playlistId , getToken()  , requestPlaylist);



    }
}
