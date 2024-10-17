package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import demo.service.ImageService;
import demo.service.ProductService;


@Controller
@RequestMapping("/")
public class HomeController{

    @Autowired
    private ImageService imageService;



    @GetMapping("/images")
    public String getImage(@RequestParam(defaultValue = "nature") String query, Model model){
        String imageurl=imageService.fetchRandomImages(query);
        if(imageurl!=null){
            model.addAttribute("imageUrl", imageurl);
        }else {
            model.addAttribute("error", "Could not fetch please try again");
        }
        return "TemporaryImageTemplate";
    }
    public static final String title= "Gadget Store";
    @GetMapping("/login")
    String login(Model model){
        model.addAttribute("title", title );
        return "Admin/login";
    }
    @Autowired
    ProductService productService;

    @GetMapping
    public String Home(Model model){
        model.addAttribute("title", title);
        return "index";
    }

    @GetMapping("/admin")
    public String Admin(Model model){
        model.addAttribute("title", title);
        return ("Admin/admin");
    }
    
    @GetMapping("/contact")
    public String ContactPage(Model model){
        model.addAttribute("title", title);
        return "contact";
    }
    @GetMapping("/about")
    public String About(Model model){
        model.addAttribute("title", title);
        return "about";
    }
}
