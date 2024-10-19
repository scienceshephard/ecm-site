package demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import demo.service.ImageService;
import demo.service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


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

    @Autowired
    HttpSession session;

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp, Model model) {
        SecurityContextHolder.clearContext();
        if(SecurityContextHolder.getContext().getAuthentication()== null){
            model.addAttribute("logout", "You have logged out Successfully!");
            Logger logger= LoggerFactory.getLogger(getClass());
            logger.info(model.getAttribute("username").toString());
        }
        session.invalidate();
        Cookie[] cookies =  req.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies){
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
            
        }

        return "Admin/login";
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
