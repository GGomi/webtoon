package com.essri.webtoon.config.security;

import com.essri.webtoon.database.entity.Users;
import com.essri.webtoon.database.repository.UserRepository;
import com.essri.webtoon.user.model.RoleType;
import com.essri.webtoon.user.model.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public KakaoUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Users user = userRepository.findByUserId(Long.valueOf(userId))
                .orElseThrow(() -> new UsernameNotFoundException(userId));

        List<RoleType> grantedAuthorities = new ArrayList<>();
        if (user.getUserType() == UserType.U) {
            grantedAuthorities.add(RoleType.USER);
        } else if (user.getUserType() == UserType.A){
            grantedAuthorities.add(RoleType.ADMIN);
        }



        return KakaoUserDetails.create(user, grantedAuthorities);
    }
}
