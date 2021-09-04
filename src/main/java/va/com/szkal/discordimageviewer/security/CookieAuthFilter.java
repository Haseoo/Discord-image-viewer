package va.com.szkal.discordimageviewer.security;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    public static final String COOKIE_TOKEN = "IMG_TOKEN";


    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return "N/A";
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(COOKIE_TOKEN))
                .findAny().map(Cookie::getValue).orElse(null);
    }

}
