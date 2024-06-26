package com.travelbnb.travelbnb.config;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.repository.AppUserRepository;
import com.travelbnb.travelbnb.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService ;
    private AppUserRepository appUserRepository ;//

    public JWTRequestFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;//
    }


    @Override
    protected void doFilterInternal
            (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        System.out.println(tokenHeader);

        if(tokenHeader !=null && tokenHeader.startsWith("Bearer ")){

      String token =  tokenHeader.substring(8,tokenHeader.length() -1);
      System.out.println(token);
        String userName = jwtService.getUserName(token);
            Optional<AppUser> opUsername = appUserRepository.findByUsername(userName);//
            AppUser appUser = opUsername.get();
            System.out.println(appUser);
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(appUser,null, Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));
            authenticationToken.setDetails(new WebAuthenticationDetails(request));//
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);//
        }

        filterChain.doFilter(request,response);
    }
}
