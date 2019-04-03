/*
 * Copyright 2001,2017 (c) Point Of Sale Solutions (POSS) of Sabre Inc. All
 * rights reserved.
 * 
 * This software and documentation is the confidential and proprietary
 * information of Sabre Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Sabre Inc.
 */
package jazekOSK.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tomasz Rutyna (SG0301553)
 * @since Mar 08, 2018
 */
@Component
public class SuccessLoginHandler extends SimpleUrlAuthenticationSuccessHandler
{

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException
    {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> authorities =
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());

        if (authorities.contains("ROLE_STUDENT")) {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/panelStudent");
        } else if (authorities.contains("ROLE_ADMIN")) {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/panelAdmin");
        } else if (authorities.contains("ROLE_INSTRUCTOR")) {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/panelInstructor");
        } else {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/main");
        }
    }
}
