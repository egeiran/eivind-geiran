package no.geiran.eivind.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class SpotifyTokenFetcher {

    public static void main(String[] args) throws Exception {
        String clientId = "DIN_CLIENT_ID";
        String clientSecret = "DIN_CLIENT_SECRET";
        String redirectUri = "http://127.0.0.1:8080/spotify/callback";
        String code = "AQA42rzZCc8gJyZx64KJK9niVf8aax00uW-rkReCxY6NNVvkosDoA8HUyTfwoZFlCS7rSyKQQ3n5xI4fO9D1nwynfYbPpP-9qzJUJZOajYMzRIv0v3w3tutHBaDy6VHoXvGPQvtxu1hd9qW_UHsb4HccEojJDPFaKaVqTgeMPM15FvB3I-yWl2HBPsMKFPO1nQ";

        String credentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        String body = "grant_type=authorization_code&code=" + code + "&redirect_uri=" + redirectUri;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .header("Authorization", "Basic " + credentials)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Respons fra Spotify:\n" + response.body());
    }
}
