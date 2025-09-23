package com.vedavyaas.summarizer.Service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AIService {
    private final String envoke =   "You are an expert summarizer. Read the text below and produce:\n" +
            "\n" +
            "1. **Summary**: Exactly 1 sentence (max 25–30 words) that captures the main idea clearly.\n" +
            "\n" +
            "Rules:\n" +
            "- Be extremely concise and readable.\n" +
            "- Include all critical points; omit all unnecessary details.\n" +
            "- Do not add opinions or information not present in the text.\n" +
            "- Bullets must be short, scannable, and user-friendly.\n";
    public String getGroq(String messages){
        String prompt = envoke + messages;

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String apiKey = dotenv.get("GROQ_API_KEY", System.getenv("GROQ_API_KEY"));
        String apiUrl = dotenv.get("GROQ_API_URL", System.getenv("GROQ_API_URL"));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "openai/gpt-oss-20b");
        body.put("messages", List.of(Map.of("role", "user", "content", prompt)));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);

        assert response.getBody() != null;
        List choices = (List) response.getBody().get("choices");
        Map choice = (Map) choices.get(0);
        Map message = (Map) choice.get("message");
        return (String) message.get("content");
    }
}
