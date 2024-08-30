package demo.Controller;

import demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController{
    private String title="ecm-site";
    @GetMapping("/login")
    String login(Model model){
        model.addAttribute("title", title);
        return "Admin/login";
    }
    @Autowired
    ProductService productService;

    @GetMapping
    public String Home(Model model){
        model.addAttribute("title", productService.getTitle());
        return "index";
    }

    @GetMapping("/admin")
    public String Admin(Model model){
        model.addAttribute("title", productService.getTitle());
        return ("Admin/admin");
    }

    @GetMapping("/sign-up")
    public String SignUpPage(Model model){
        model.addAttribute("title", productService.getTitle());
        return "signUp";
    }
    @GetMapping("/contact")
    public String ContactPage(Model model){
        model.addAttribute("title", productService.getTitle());
        return "contact";
    }
    @GetMapping("/about")
    public String About(Model model){
        model.addAttribute("title", productService.getTitle());
        return "about";
    }
}
