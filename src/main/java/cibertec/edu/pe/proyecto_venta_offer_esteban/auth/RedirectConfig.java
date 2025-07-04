package cibertec.edu.pe.proyecto_venta_offer_esteban.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;
@Component
public class RedirectConfig implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UrlPathHelper urlPathHelper = new UrlPathHelper();

        String path = urlPathHelper.getContextPath(request);

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            switch (authority.getAuthority()) {
                case "ROLE_ADMIN":
                    response.sendRedirect(path + "/administrador");
                    break;
                case "ROLE_USER":
                    response.sendRedirect(path + "/operario");
                    break;
            }
        }
    }
}
