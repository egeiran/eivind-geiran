package no.geiran.eivind.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@SuppressWarnings("unchecked")
public class AiDescriptionService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${openai.api.key}")
    private String openaiApiKey;

    public String generateDescription(String artistName) {
        String prompt = "Skriv en kort og kul beskrivelse av artisten " + artistName
                + ". Gjerne med en personlig vri, som hvorfor noen kan like dem. Den skal være rundt 25 ord. ";

        Map<String, Object> body = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(Map.of("role", "user", "content", prompt)),
                "temperature", 0.7);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "https://api.openai.com/v1/chat/completions",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {
                });

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
        if (choices != null && !choices.isEmpty()) {
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            return (String) message.get("content");
        }

        return "Ingen beskrivelse tilgjengelig.";
    }

}
