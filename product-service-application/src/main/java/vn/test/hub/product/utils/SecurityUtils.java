package vn.test.hub.product.utils;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import vn.test.hub.common.exception.UnauthorizedException;

public class SecurityUtils {

    public static String getUserID() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userID = jwt.getClaim("sub");
        if (userID != null)
            return userID;
        else
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
}
