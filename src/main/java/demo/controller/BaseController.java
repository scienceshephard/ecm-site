package demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {
    // private static final Logger logger= LoggerFactory.getLogger(BaseController.class);
    public static final String title= "Gadget Store";
    
    @ModelAttribute
    public void addUsernameToModel(Model model){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if(authentication!=null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails= (UserDetails) authentication.getPrincipal();
            username= userDetails.getUsername();
            
        }else if (authentication != null){
            username = authentication.getName();
        }
        if(username != null){
            model.addAttribute("username", username);
        }
        model.addAttribute("title", title);
    }
}
