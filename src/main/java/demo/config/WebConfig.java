package demo.config;

import java.beans.Customizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import lombok.NoArgsConstructor;

@Configuration
@EnableWebSecurity
@NoArgsConstructor
public class WebConfig {

    @Autowired
    UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(auth->auth.requestMatchers("/h2-console/**", "/register", "/fragments/**","/logout", "/images/**", "/css/**", "/js/**")
        .permitAll().anyRequest().authenticated())
        .formLogin(form ->form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
        .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true).clearAuthentication(true).permitAll())
        .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")
        ).headers(headers->headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
