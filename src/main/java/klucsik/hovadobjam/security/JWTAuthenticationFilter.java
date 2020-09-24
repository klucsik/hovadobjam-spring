package klucsik.hovadobjam.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import klucsik.hovadobjam.exceptionhandling.InvalidInputException;
import klucsik.hovadobjam.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static klucsik.hovadobjam.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/login");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException,InvalidInputException{
        try{
            User creds = new ObjectMapper().readValue(req.getInputStream(),User.class);
            return  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e){
            log.debug("attemptAuthentication failed, throwing exception:");
            log.debug(e.getMessage());
            log.debug(e.getCause().toString());
            throw new InvalidInputException(e); //I cannot catch dis with my handler
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException{
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
