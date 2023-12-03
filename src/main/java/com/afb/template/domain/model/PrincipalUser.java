package com.afb.template.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PrincipalUser implements UserDetails {
    Users user;
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority>authorities;

    public PrincipalUser(String username, String name, String lastname, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public static PrincipalUser build(Users userAccount){
        List<GrantedAuthority> authorities=
                userAccount.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                        .getName().name())).collect(Collectors.toList());
        return new PrincipalUser(userAccount.getEmail(), userAccount.getFirstname(), userAccount.getLastname(), userAccount.getEmail(), userAccount.getPassword(), authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
