package no.geiran.eivind.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import no.geiran.eivind.model.TrackInfo;

@Service
public class SpotifyTopTracksService {

    private final SpotifyAuthService spotifyAuthService;
    private final RestTemplate restTemplate = new RestTemplate();

    public SpotifyTopTracksService(SpotifyAuthService spotifyAuthService) {
        this.spotifyAuthService = spotifyAuthService;
    }

    @SuppressWarnings("unchecked")
    public List<TrackInfo> getTopTracks() {
        String accessToken = spotifyAuthService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me/top/tracks?limit=5&time_range=short_term",
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {
                });

        Object rawItems = response.getBody().get("items");
        if (!(rawItems instanceof List<?> genericList))
            return Collections.emptyList();

        return genericList.stream()
                .filter(item -> item instanceof Map)
                .map(item -> {
                    Map<String, Object> track = (Map<String, Object>) item;
                    String name = (String) track.get("name");
                    List<Map<String, Object>> artists = (List<Map<String, Object>>) track.get("artists");
                    String artistName = (String) artists.get(0).get("name");
                    Map<String, Object> album = (Map<String, Object>) track.get("album");
                    List<Map<String, Object>> images = (List<Map<String, Object>>) album.get("images");
                    String imageUrl = (String) images.get(0).get("url");
                    String albumUrl = (String) track.get("uri");
                    return new TrackInfo(name, artistName, imageUrl, albumUrl);
                })
                .toList();
    }

}