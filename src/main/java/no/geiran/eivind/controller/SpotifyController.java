package no.geiran.eivind.controller;

import no.geiran.eivind.model.ArtistInfo;
import no.geiran.eivind.model.TrackInfo;
import no.geiran.eivind.service.AiDescriptionService;
import no.geiran.eivind.service.SpotifyAuthService;
import no.geiran.eivind.service.SpotifyTopTracksService;
import no.geiran.eivind.service.SpotifyTopArtistsService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotifyController {

    private final SpotifyAuthService spotifyAuthService;
    private final SpotifyTopTracksService tracksService;
    private final SpotifyTopArtistsService artistsService;
    private final AiDescriptionService aiDescriptionService;

    public SpotifyController(SpotifyAuthService spotifyAuthService, SpotifyTopTracksService spotifyTopTracksService,
            SpotifyTopArtistsService spotifyTopArtistsService, AiDescriptionService aiDescriptionService) {
        this.spotifyAuthService = spotifyAuthService;
        this.tracksService = spotifyTopTracksService;
        this.artistsService = spotifyTopArtistsService;
        this.aiDescriptionService = aiDescriptionService;
    }

    @GetMapping("/test-token")
    public String getToken() {
        return spotifyAuthService.getAccessToken();
    }

    @GetMapping("/api/top-tracks")
    public List<TrackInfo> getTopTracks() {
        return tracksService.getTopTracks();
    }

    @GetMapping("/api/top-artists")
    public List<ArtistInfo> getTopArtists() {

        return artistsService.getTopArtists();
    }

    @GetMapping("/api/top-artist-description")
    public Map<String, String> getTopArtistDescription() {
        List<ArtistInfo> topArtists = artistsService.getTopArtists();
        if (topArtists.isEmpty()) {
            return Map.of("description", "Fant ingen artist.");
        }

        String artistName = topArtists.get(0).name();
        String description = aiDescriptionService.generateDescription(artistName);
        return Map.of("artist", artistName, "description", description);
    }
}
