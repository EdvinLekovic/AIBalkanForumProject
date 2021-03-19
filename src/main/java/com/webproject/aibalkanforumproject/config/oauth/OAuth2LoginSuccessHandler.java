package com.webproject.aibalkanforumproject.config.oauth;

import com.webproject.aibalkanforumproject.model.enumerations.Provider;
import com.webproject.aibalkanforumproject.repository.UserRepository;
import com.webproject.aibalkanforumproject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public OAuth2LoginSuccessHandler(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        CustomOAuth2User user = new CustomOAuth2User(oauthUser);
        String email = user.getEmail();
        String name = user.getName();
        request.getSession().setAttribute("name", name);
        Provider provider;
        String email1;
        if (user.getAttributes().containsKey("sub")) {
            provider = Provider.GOOGLE;
            email1 = String.format("%s (%s)", email, provider);
            this.userService.registerWithGoogle(email1, name);
        } else {
            provider = Provider.FACEBOOK;
            email1 = String.format("%s (%s)", email, provider);
            this.userService.registerWithFacebook(email1, name);
        }

        request.getSession().setAttribute("email", email1);
        response.sendRedirect("/home");
    }
}
