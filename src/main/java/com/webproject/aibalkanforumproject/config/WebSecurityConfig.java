package com.webproject.aibalkanforumproject.config;

import com.webproject.aibalkanforumproject.config.oauth.CustomOAuth2User;
import com.webproject.aibalkanforumproject.config.oauth.CustomOAuth2UserService;
import com.webproject.aibalkanforumproject.config.oauth.OAuth2LoginSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider, CustomOAuth2UserService customOAuth2UserService, OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler) {
        this.passwordEncoder = passwordEncoder;
        this.customUsernamePasswordAuthenticationProvider = customUsernamePasswordAuthenticationProvider;
        this.customOAuth2UserService = customOAuth2UserService;
        this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //'jobs/info/{id}/image'
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/",
                        "/home",
                        "/api/**",
                        "/researches/**",
                        "/forum/**",
                        "/events/**",
                        "/jobs",
                        "/jobs/info/**",
                        "/jobs/image/**",
                        "/jobs/filter",
                        "/myArticles/image/**",
                        "/login",
                        "/img/**",
                        "/css/**",
                        "/icons/**",
                        "/js/**",
                        "/static/**",
                        "/register",
                        "/oauth/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/home", true)
                .and()
                .oauth2Login() // enable OAuth2
                .loginPage("/login")
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2LoginSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied")
                .and()
                .csrf()
                .disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customUsernamePasswordAuthenticationProvider);
        //Admin
//        auth.inMemoryAuthentication()
//                .withUser("edvin12")
//                .password(passwordEncoder.encode("edvin"))
//                .authorities("ROLE_ADMIN")
//                .and()
//                .withUser("filip12")
//                .password(passwordEncoder.encode("filip"))
//                .authorities("ROLE_ADMIN");
    }

}
