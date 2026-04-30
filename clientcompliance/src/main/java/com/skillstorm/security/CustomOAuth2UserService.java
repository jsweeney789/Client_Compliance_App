package com.skillstorm.security;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.skillstorm.models.User;
import com.skillstorm.repositories.UserRepository;
import com.skillstorm.types.Role;

@Service
public class CustomOAuth2UserService extends OidcUserService {

    private UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest request) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(request);

        // Check if user exists
        userRepository.findByEmail(oidcUser.getEmail()).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(oidcUser.getEmail());
            newUser.setFirstName(oidcUser.getGivenName());
            newUser.setLastName(oidcUser.getFamilyName());
            newUser.setRole(Role.BASIC_USER);
            return userRepository.save(newUser);
        });

        return oidcUser;
    }
}