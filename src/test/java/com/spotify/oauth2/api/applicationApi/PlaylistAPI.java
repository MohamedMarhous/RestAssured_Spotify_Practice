package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.Utilties.ConfigLoader;
import com.spotify.oauth2.api.RestAPIsResource;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.RouteEndPointsConstants.PLAYLISTS;
import static com.spotify.oauth2.api.RouteEndPointsConstants.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;


public class PlaylistAPI {


    public static Response post(Playlist requestPlaylist) {
        return RestAPIsResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, getToken(), requestPlaylist);

    }

    public static Response post(String token, Playlist requestPlaylist) {
        return RestAPIsResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() +  PLAYLISTS, token, requestPlaylist);


    }

    public static Response get(String playlistId) {

        return RestAPIsResource.get(PLAYLISTS + "/" + playlistId, getToken());


    }

    public static Response update(String playlistId, Playlist requestPlaylist) {
        return RestAPIsResource.update(PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);


    }
}
