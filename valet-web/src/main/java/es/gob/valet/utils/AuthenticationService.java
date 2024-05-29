package es.gob.valet.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService {

    /**
     * Authenticate a user in the Spring Security context.
     * @param username the username of the user to authenticate
     */
    public void authenticateUser(String username) {
        // Creating an authentication token with the user's details and role
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                username, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        // Storing the authentication token in the Security Context
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
