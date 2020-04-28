package com.essri.webtoon.config.security;

import com.essri.webtoon.database.entity.Users;
import com.essri.webtoon.user.model.RoleType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class KakaoUserDetails implements UserDetails {
    private Long userId;

    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    public static KakaoUserDetails create(Users user, List<RoleType> authoritiesList) {
        List<GrantedAuthority> authorities = authoritiesList.stream().map(role -> new SimpleGrantedAuthority(role.getValue())).collect(Collectors.toList());
        return KakaoUserDetails.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .authorities(authorities)
                .build();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return String.valueOf(userId);
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
