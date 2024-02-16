package com.ldbbank.hrrecruitmentapi.security.JWT;

import com.ldbbank.hrrecruitmentapi.security.service.CustomUserDetailsService;
import com.ldbbank.hrrecruitmentapi.security.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private HandlerExceptionResolver handlerExceptionResolver;
    private  final JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customerUserDetailsService;
    public JwtAuthenticationFilter(
            JwtService jwtService,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String token = getJWTFromRequest(request);

            if (StringUtils.hasText(token) && jwtService.validateToken(token)) {

                // get username from token
                String username = jwtService.getUsernameFromJwt(token);
                // load user associated with token
                UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // set spring security
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }


            filterChain.doFilter(request,response);


        } catch (ExpiredJwtException exception ) {
            System.out.println("expired"+exception);
            final String expiredMsg = exception.getMessage();
            final String msg = (expiredMsg != null) ? expiredMsg : "Unauthorized";
            handlerExceptionResolver.resolveException(request, response, null, exception);

            if (msg!=null){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,expiredMsg);
            }else{
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid Login details");
            }
        }
    }



    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return bearerToken;
    }

}
