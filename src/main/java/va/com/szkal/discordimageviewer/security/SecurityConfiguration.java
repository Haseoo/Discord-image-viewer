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
        return  authentication -> {
            if (authentication.getPrincipal() == null) {
                authentication.setAuthenticated(false);

            } else {
                String token = (String)authentication.getPrincipal();
                authentication.setAuthenticated(jwtService.isTokenValid(token));
            }
            return authentication;
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        var filter = new CookieAuthFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers("api/*")
                .hasIpAddress("127.0.0.1")
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .antMatchers("/viewer")
                .authenticated();
    }
}