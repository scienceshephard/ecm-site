package demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ImageService {

    private final RestTemplate restTemplate;
    private static final String API_URL="https//www.unsplash.com/";
    private static final String API_KEY="MPmSi6iZ-Ec_cIs51O-0_ClhKQ0U6vlS0U8q6hNvZ_s";


    ImageService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    // public byte[] getImagFromApi(String imgUrl){
    //     ResponseEntity<byte[]> rep= restTemplate.getForEntity(imgUrl, byte[].class);
    //     return rep.getBody();
    // }
    // public String getImageuRl(String img){
    //     return "api";
    // }

    
    public String fetchRandomImages(String query){
        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization", API_KEY);

        HttpEntity <String> entity= new HttpEntity<>(headers);
        ResponseEntity<Map> response= restTemplate.exchange(API_URL+"?query="+query+"&per_page=1", HttpMethod.GET, entity, Map.class);

        if(response.getStatusCode().is2xxSuccessful()){
            Map<String, Object> photo=(Map<String, Object>) ((Map<String, Object>) response.getBody()).get("photos");
            if(photo !=null && !photo.isEmpty() ){
                Map<String, String> src=(Map<String, String>) ((Map<String, Object>) photo.get(0)).get("src");
                return src.get("medium");
            }
        }

        return null;
    }

}
