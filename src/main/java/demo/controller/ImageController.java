package demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Value("${img.api.access-key}")
    private String accessKey;
    private static final String UNSPLASH_URL = "https://api.unsplash.com/photos/random?client_id=%s";
    private final RestTemplate restTemplate;
    
    ImageController(RestTemplate restTemplate){ 
        this.restTemplate=restTemplate;
    }

    @GetMapping("/gadgets")
    public ResponseEntity<List<Object>> getRandomGadgetsImages(@RequestParam(defaultValue = "8") int count) {        
        String url = String.format(UNSPLASH_URL+ "&query=gadgets&count=%d",accessKey, count);
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/gadgets/laptop")
    public ResponseEntity<List<Object>> getRandomLaptopImages(@RequestParam(defaultValue = "8") int count) {        
        String url = String.format(UNSPLASH_URL+ "&query=laptop&count=%d",accessKey, count);
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return ResponseEntity.ok(response.getBody());
    }
    
    @GetMapping("/gadgets/watch")
    public ResponseEntity<List<Object>> getRandomWatchImages(@RequestParam(defaultValue = "8") int count) {        
        String url = String.format(UNSPLASH_URL+ "&query=watch&count=%d",accessKey, count);
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return ResponseEntity.ok(response.getBody());
    }
    @GetMapping("/gadgets/headphones")
    public ResponseEntity<List<Object>> getRandomWatchHeadphonesImages(@RequestParam(defaultValue = "8") int count) {        
        String url = String.format(UNSPLASH_URL+ "&query=headphones&count=%d",accessKey, count);
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return ResponseEntity.ok(response.getBody());
    }
    @GetMapping("/gadgets/gaming")
    public ResponseEntity<List<Object>> getRandomGamingImages(@RequestParam(defaultValue = "8") int count) {        
        String url = String.format(UNSPLASH_URL+ "&query=games&count=%d",accessKey, count);
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/gadgets/phone")
    public ResponseEntity<List<Object>> getRandomPhoneImages(@RequestParam(defaultValue = "8") int count) {        
        String url = String.format(UNSPLASH_URL+ "&query=phone&count=%d",accessKey, count);
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/gadgets/camera")
    public ResponseEntity<List<Object>> getRandomCameraImages(@RequestParam(defaultValue = "8") int count) {        
        String url = String.format(UNSPLASH_URL+ "&query=camera&count=%d",accessKey, count);
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return ResponseEntity.ok(response.getBody());
    }
}
