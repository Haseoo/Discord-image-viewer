package va.com.szkal.discordimageviewer.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import va.com.szkal.discordimageviewer.api.AuthData;

import java.util.Collection;
import java.util.Collections;

public class ServerAuthentication implements Authentication {

    private boolean authenticated;
    private final AuthData authData;

    public ServerAuthentication(AuthData authData) {
        this.authData = authData;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(Long.toString(authData.getServerId())));
    }

    @Override
    public AuthData getCredentials() {
        return authData;
    }

    @Override
    public Long getDetails() {
        return authData.getServerId();
    }

    @Override
    public AuthData getPrincipal() {
        return authData;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return authData.getServerName();
    }
}
