package no.geiran.eivind.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import no.geiran.eivind.model.ArtistInfo;

@Service
public class SpotifyTopArtistsService {

    private final SpotifyAuthService spotifyAuthService;
    private final RestTemplate restTemplate = new RestTemplate();

    public SpotifyTopArtistsService(SpotifyAuthService spotifyAuthService) {
        this.spotifyAuthService = spotifyAuthService;
    }

    @SuppressWarnings("unchecked")
    public List<ArtistInfo> getTopArtists() {
        String accessToken = spotifyAuthService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me/top/artists?limit=1",
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
                    Map<String, Object> artist = (Map<String, Object>) item;
                    String name = (String) artist.get("name");
                    List<Map<String, Object>> images = (List<Map<String, Object>>) artist.get("images");
                    String imageUrl = (String) images.get(0).get("url");
                    String artistUrl = (String) artist.get("uri");
                    return new ArtistInfo(name, imageUrl, artistUrl);
                })
                .toList();
    }
}
