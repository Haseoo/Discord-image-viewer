package va.com.szkal.discordimageviewer.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class ServerAuthentication implements Authentication {

    private boolean authenticated;
    private final String serverName;
    private final String token;

    public ServerAuthentication(String serverName, String token) {
        this.serverName = serverName;
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(serverName));
    }

    @Override
    public Object getCredentials() {
        return serverName;
    }

    @Override
    public Object getDetails() {
        return serverName;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return serverName;
    }
}
