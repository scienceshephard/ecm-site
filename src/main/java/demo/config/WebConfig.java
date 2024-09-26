package demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class WebConfig {

    @Autowired
    UserDetailsService userDetailsService;
    
    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(auth->auth.requestMatchers("/h2-console/**", "/register", "/fragments/**", "/images/**", "/css/**", "/js/**")
        .permitAll().anyRequest().authenticated()
        ).formLogin(form ->form.loginPage("/login").permitAll()
        ).logout(logout ->logout.permitAll()).csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")
        ).headers(headers->headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
