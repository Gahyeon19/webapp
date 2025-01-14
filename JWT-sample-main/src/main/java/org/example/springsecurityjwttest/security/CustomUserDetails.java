package org.example.springsecurityjwttest.security;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.springsecurityjwttest.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class CustomUserDetails implements UserDetails {

    @Getter
    private final User user;

    public CustomUserDetails(User user){
        this.user= user;
        log.info("user ={}" , user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("getAuthorities .....");
        Collection<GrantedAuthority> collection = new ArrayList<>();

       /* collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });*/

        collection.add(()-> user.getRole().toString());

        return collection;
    }

    @Override
    public String getPassword() {
        log.info("getPassword .....");
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        log.info("getUsername...");
        return user.getUserid(); //id
    }

    @Override
    public boolean isAccountNonExpired() {
        log.info("isAccountNonExpired...");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        log.info("isAccountNonLocked...");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        log.info("isCredentialsNonExpired...");
        return true;
    }

    @Override
    public boolean isEnabled() {
        log.info("isEnabled...");
        return true;
    }
}
