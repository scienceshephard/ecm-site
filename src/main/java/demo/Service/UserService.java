package demo.Service;

import demo.Model.UserPrincipal;
import demo.Model.Users;
import demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        Users user= userRepo.findByUserName(Username);
        if (user==null){
            System.out.println("UserName not found!!");
            throw new UsernameNotFoundException("User Not found");
        }
        return new UserPrincipal(user);
    }
}
