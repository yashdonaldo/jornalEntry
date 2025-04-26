package yd.demo.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import yd.demo.Entry.UserEntry;
import yd.demo.Respository.UserRepo;

@Component
public class UserDetailsImp implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user = userRepo.findByUsername(username);
        if(user != null){
           UserDetails userDetails = User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getRole().toArray(new String[0])).build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User Not Found " + username);
    
    }
    
}
