package demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/api/images")
public class ImageController {

    @Value("${img.api.access-key}")
    private String accessKey;
    private static final String UNSPLASH_URL = "https://api.unsplash.com/photos/random?client_id=";

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @GetMapping("/random")
    public List<Map<String, Object>> getRandomImages(@RequestParam(defaultValue = "5") int count) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UNSPLASH_URL + accessKey+"&count="+count;

        logger.info("Fetching images from Unsplash API: " + url);
        try{
            List<Map<String, Object>> images= restTemplate.getForObject(url, List.class);
            logger.info("Recieved images: "+ images);
            return images;
        }catch(Exception e){
            logger.error("Error fetching images from Unsplash API: "+e.getMessage());
            throw e;
        }

    }

}
