package va.com.szkal.discordimageviewer.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtService jwtService;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() {
        return authentication -> {
            String token = (String) authentication.getCredentials();
            if (jwtService.isTokenValid(token)) {
                var auth = new ServerAuthentication(jwtService.getAuthFromJWT(token));
                auth.setAuthenticated(true);
                return auth;
            }
            return null;
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        var filter = new CookieAuthFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**", "GET", "POST", "PUT", "PATCH")
                .hasIpAddress("127.0.0.1")
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .antMatchers("/")
                .fullyAuthenticated();
    }
}