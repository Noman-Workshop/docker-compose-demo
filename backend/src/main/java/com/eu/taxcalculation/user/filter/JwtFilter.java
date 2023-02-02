package com.eu.taxcalculation.user.filter;

import com.eu.taxcalculation.user.repository.UserRepository;
import com.eu.taxcalculation.user.service.UserServiceImplemented;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Autowired
    UserServiceImplemented userService;

    public JwtFilter(UserServiceImplemented userService) {
        this.userService = userService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                throw new ServletException("Please provide a valid token.");
                //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please provide a valid token.");
            }
        }

        final String token = authHeader.substring(7);

        //

        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        request.setAttribute("claims", claims);
        //request.setAttribute("blog", servletRequest.getParameter("id"));
        System.out.println(claims);

        String username = (String) claims.get("sub");
        System.out.println("======");
        System.out.println(username);


//        UserDetails userDetails = (UserDetails) this.userService.getUserByUsername(username);

//        // authentication process build
//        UsernamePasswordAuthenticationToken authentication =
//                new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );

//        // source
//        authentication.setDetails(
//                new WebAuthenticationDetailsSource().buildDetails(request)
//        );
//
//        // security context holder
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // return to next
        filterChain.doFilter(request, response);
    }

}
