package demo.controller;

import java.util.List;

import demo.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @GetMapping("/gadgets")
    public ResponseEntity<List<Object>>getGadgetsImages(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int count
    ){
        return ResponseEntity.ok(imageService.getImages(query, count));
    }

}
