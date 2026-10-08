package demo.client;

import demo.exceptions.ExternalApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component 
public class UnsplashClient {

    private static final String UNSPLASH_API = "https://api.unsplash.com/photos/random";
    private final RestTemplate restTemplate;
    private final String accessKey;

    public UnsplashClient(
        RestTemplate restTemplate,
        @Value("${img.api.access-key}") String aceessKey
    ){
        this.accessKey = aceessKey;
        this.restTemplate = restTemplate;
    }
    public List<Object> getRandomImages (String query, int count){
        String url = UriComponentsBuilder
                .fromHttpUrl(UNSPLASH_API)
                .queryParam("client_id", accessKey)
                .queryParam("query", query)
                .queryParam("count", count)
                .toUriString();
        try {
            ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
            return response.getBody();
        }catch (ResourceAccessException e){
            throw  new ExternalApiException("UNsplash rejectedthe request: ", e);
        }catch (HttpClientErrorException e){
            throw new ExternalApiException(
                    "Unsplash server error: " + e.getStatusCode(),
                    e
            );
        }
    }


}
