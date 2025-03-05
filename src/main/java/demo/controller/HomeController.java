package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class HomeController extends BaseController{

    
    @GetMapping("/login")
    String login(){
        return "Admin/login";
    }


    @Autowired
    HttpSession session;
    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        SecurityContextHolder.clearContext();
        session.invalidate();
        Cookie[] cookies =  req.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies){
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        return "redirect:/login?logout";
    }
    
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
