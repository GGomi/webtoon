package com.essri.webtoon.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class JwtUser implements UserDetails {
    private final Long userId;

    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
            Long userId,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.userId = userId;
        this.authorities = authorities;
    }

//    @Override
//    public String toString() {
//        return "JwtUser = {" +
//                "\"userId\": \"" + StringMaskingUtils.getMasktedName(userId.toString()) + '\"' +
//                ", \"userName\": \"" + StringMaskingUtils.getMasktedName(userName) + '\"' +
//                ", \"realName\": \"" + StringMaskingUtils.getMasktedName(realName) + '\"' +
//                ", \"nickName\": \"" + StringMaskingUtils.getMasktedName(nickName) + '\"' +
//                ", \"password\": \"" + StringMaskingUtils.getMasktedName(password) + '\"' +
//                ", \"email\": \"" + StringMaskingUtils.getMaskedEmail(email) + '\"' +
//                ", \"userState\": \"" + userState + '\"' +
//                ", \"blockLogin\": \"" + blockLogin + '\"' +
//                ", \"authorities\": \"" + authorities + '\"' +
//                '}';
//    }

    @Override
    public String getUsername() {
        return String.valueOf(this.userId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
//        return (this.userState.equals(UserState.A) && this.blockLogin.equals(EnableYN.N));
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
//        return (this.userState.equals(UserState.A) && this.blockLogin.equals(EnableYN.N));
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
//        return (this.userState.equals(UserState.A) && this.blockLogin.equals(EnableYN.N));
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
//        return (this.userState.equals(UserState.A) && this.blockLogin.equals(EnableYN.N));
    }
}
