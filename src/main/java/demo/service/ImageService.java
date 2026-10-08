package demo.service;

import demo.client.UnsplashClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final UnsplashClient unsplashClient;

    public ImageService(UnsplashClient unsplashClient) {
        this.unsplashClient = unsplashClient;
    }

    public List<Object> getImages(String query, int count){
        if (count < 1 || count > 30) {
            throw new IllegalArgumentException(
                    "count must be between 1 and 30"
            );
        }

        return unsplashClient.getRandomImages(query, count);
    }
}
