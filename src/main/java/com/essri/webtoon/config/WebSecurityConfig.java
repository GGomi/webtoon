package com.essri.webtoon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProvider authProvider;

    public WebSecurityConfig(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(
//                "/",
//                "/main",
//                "/resources/**",
//                "/login",
//                "/join"
//        );
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/**").access("ROLE_USER")
//                .antMatchers("/admin/**").access("ROLE_ADMIN")
//                .antMatchers("/**").authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/main")
//                    .usernameParameter("userId")
//                    .passwordParameter("password")
//                .and()
//                    .logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/login")
//                    .invalidateHttpSession(true)
                .and()
//                    .authenticationProvider(authProvider)
                .csrf().disable();
        ;
    }
}
