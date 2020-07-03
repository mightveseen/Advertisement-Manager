package com.senlainc.javacourses.petushokvaliantsin.configuration;

import com.senlainc.javacourses.petushokvaliantsin.configuration.security.AuthorizationFilter;
import com.senlainc.javacourses.petushokvaliantsin.configuration.security.handler.CustomAccessDeniedHandler;
import com.senlainc.javacourses.petushokvaliantsin.configuration.security.handler.CustomAuthenticationEntryPointHandler;
import com.senlainc.javacourses.petushokvaliantsin.configuration.security.handler.CustomAuthenticationSuccessHandler;
import com.senlainc.javacourses.petushokvaliantsin.configuration.security.handler.CustomLogoutSuccessHandler;
import com.senlainc.javacourses.petushokvaliantsin.configuration.security.mapper.TokenMapper;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@PropertySource(value = "classpath:/properties/security.properties", ignoreResourceNotFound = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADVERTISEMENT_URL = "/advertisements/**";
    private final UserDetailsService userDetailsService;
    @Value("${SECURITY_CONFIG.TOKEN_HEADER:Authorization}")
    private String tokenHeader;
    @Value("${SECURITY_CONFIG.TOKEN_PREFIX:Bearer}")
    private String tokenPrefix;
    @Value("${SECURITY_CONFIG.KEY:key}")
    private String secretKey;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/sign-up").anonymous()
                .antMatchers("/logout").authenticated()
                .antMatchers("/admin/*").hasRole(EnumRole.ROLE_ADMIN.getRole())
                .antMatchers("/moderator/*").hasAnyRole(EnumRole.ROLE_MODERATOR.getRole(), EnumRole.ROLE_ADMIN.getRole())
                .antMatchers("/account/**").hasAnyRole(EnumRole.ROLE_COMMON.getRole(), EnumRole.ROLE_ADMIN.getRole(), EnumRole.ROLE_ADMIN.getRole())
                .antMatchers(HttpMethod.PUT, ADVERTISEMENT_URL).hasAnyRole(EnumRole.ROLE_COMMON.getRole(), EnumRole.ROLE_MODERATOR.getRole(), EnumRole.ROLE_ADMIN.getRole())
                .antMatchers(HttpMethod.POST, ADVERTISEMENT_URL, "/users/{id}").hasAnyRole(EnumRole.ROLE_COMMON.getRole(), EnumRole.ROLE_MODERATOR.getRole(), EnumRole.ROLE_ADMIN.getRole())
                .antMatchers(HttpMethod.DELETE, ADVERTISEMENT_URL).hasAnyRole(EnumRole.ROLE_COMMON.getRole(), EnumRole.ROLE_MODERATOR.getRole(), EnumRole.ROLE_ADMIN.getRole())
                .antMatchers(HttpMethod.GET, ADVERTISEMENT_URL).permitAll()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler())
                .and().logout().logoutSuccessHandler(logoutSuccessHandler())
                .and().formLogin().loginPage("/login").successHandler(authenticationSuccessHandler())
                .and().httpBasic()
                .and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new AuthorizationFilter(authenticationManager(), tokenMapper()));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        final ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public TokenMapper tokenMapper() {
        return new TokenMapper(userDetailsService, tokenPrefix, secretKey, tokenHeader);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPointHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}
