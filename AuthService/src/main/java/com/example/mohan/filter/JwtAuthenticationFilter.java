package com.example.mohan.filter;

import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.example.mohan.utils.JwtUtil;
import com.example.mohan.services.jwt.UserService;

import org.apache.commons.lang3.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
		
    private final JwtUtil jwtUtil;
    private final UserService userService;

    
    
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, 
            @NonNull HttpServletResponse response, 
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Validate Authorization header
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

     
        jwt = authHeader.substring(7);

        userEmail = jwtUtil.extractUserName(jwt);

      
       if(StringUtils.isNotEmpty(userEmail)
    		   && SecurityContextHolder.getContext().getAuthentication()==null)
       {
    	   UserDetails userDetails=userService.userDetailsService().loadUserByUsername(userEmail);
    	   
    	   if(jwtUtil.isTokenValid(jwt, userDetails))
    	   {
    		   SecurityContext context=SecurityContextHolder.createEmptyContext();
    		   UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    		   authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    		   context.setAuthentication(authToken);
    		   SecurityContextHolder.setContext(context);
    	   }
       }
       filterChain.doFilter(request, response);
}
}
