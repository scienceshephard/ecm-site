package demo.controller;

import java.nio.file.attribute.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import demo.model.UserEntity;
import demo.model.Users;
import demo.service.UserService;
import jakarta.validation.Valid;


@Controller
public class UserController extends BaseController{

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    String title=HomeController.title;
    @Autowired
    UserService userService;
    public UserController(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService){
        this.passwordEncoder=passwordEncoder;
        this.userDetailsService=userDetailsService;
    }


    @GetMapping("/register")
    public String SignUpPage(Model model){
        model.addAttribute("user", new Users());
        return "register";
    }
    @PostMapping("/register")
    public String submitSignupPage (@Valid @ModelAttribute("user")Users users, BindingResult result, Model model){
        model.addAttribute("title",title); 
        
        if(result.hasErrors()){
            return "register";
        }if (!users.isPasswordMatching()) {
            result.rejectValue("ConfirmPassword", "error.user", "Passwords does not match");
            return "register";
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(users.getUsername());
        userEntity.setEmail(users.getEmail());
        userEntity.setPassword(passwordEncoder.encode(users.getPassword()));
        model.addAttribute("success", "Form submitted successully");
        userService.addUser(userEntity);
        authenticatedUser(userEntity);
        
        return "redirect:/";
    }
    private void authenticatedUser(UserEntity uEntity){
        UserDetails userDetails= userDetailsService.loadUserByUsername(uEntity.getUsername());
        UsernamePasswordAuthenticationToken auToken = new UsernamePasswordAuthenticationToken( userDetails, uEntity.getPassword() , userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auToken);
    }
}
