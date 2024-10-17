package demo.service;

import demo.model.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demo.model.UserEntity;
import demo.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        UserEntity user= userRepo.findByUsername(Username);
        if (user==null){
            System.out.println("UserName not found!!");
            throw new UsernameNotFoundException("User Not found");
        }
        return new UserPrincipal(user);
    }

    public UserEntity addUser(UserEntity userEntity){
        return userRepo.save(userEntity);
    }
    public void deleteUser(int userId){
        userRepo.deleteById(userId);
    }
}
