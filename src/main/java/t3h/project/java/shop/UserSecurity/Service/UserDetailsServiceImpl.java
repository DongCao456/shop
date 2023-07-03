package t3h.project.java.shop.UserSecurity.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import t3h.project.java.shop.User.Model.Role;
import t3h.project.java.shop.User.Model.User;
import t3h.project.java.shop.User.Repository.UserRepository;
import t3h.project.java.shop.User.Service.UserService;
import t3h.project.java.shop.UserSecurity.Dto.UserDetailsDto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser=service.findByUsername(username);
        if (!optionalUser.isPresent())
            throw new UsernameNotFoundException("Username is invalid");

        Set<GrantedAuthority> authorities=getAuthorities(optionalUser.get().getRoles());

        return new UserDetailsDto(optionalUser.get().getUsername(),optionalUser.get().getPassword(),authorities);
    }

    public Set<GrantedAuthority> getAuthorities(Set<Role> roles){
        Set<GrantedAuthority> authorities=new HashSet<>();
        Iterator<Role> iterator=roles.iterator();

        while (iterator.hasNext()){
            authorities.add(new SimpleGrantedAuthority(iterator.next().getName()));

        }
        return authorities;
    }
}
