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
public class HomeController extends BaseController{

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
    @GetMapping("/login")
    String login(){
        return "Admin/login";
    }
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
    
    @Autowired
    ProductService productService;

    @GetMapping
    public String Home(Model model){
        return "index";
    }

    @GetMapping("/admin")
    public String Admin(){
        return ("Admin/admin");
    }
    
    @GetMapping("/contact")
    public String ContactPage(){
        return "contact";
    }
    @GetMapping("/about")
    public String About(){
        return "about";
    }
    @GetMapping("/userid")
    public String account(){
        return "account";
    }
}
