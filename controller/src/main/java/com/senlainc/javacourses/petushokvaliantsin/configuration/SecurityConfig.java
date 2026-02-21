package com.senlainc.javacourses.petushokvaliantsin.configuration;

import com.senlainc.javacourses.petushokvaliantsin.configuration.security.AuthorizationFilter;
import com.senlainc.javacourses.petushokvaliantsin.configuration.security.handler.CustomAccessDeniedHandler;
import com.senlainc.javacourses.petushokvaliantsin.configuration.security.handler.CustomAuthenticationEntryPointHandler;
import com.senlainc.javacourses.petushokvaliantsin.configuration.security.handler.CustomLogoutSuccessHandler;
import com.senlainc.javacourses.petushokvaliantsin.configuration.security.mapper.TokenMapper;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String ADVERTISEMENT_URL = "/advertisements/**";

    @Qualifier("userCredServiceImpl")
    private final UserDetailsService userDetailsService;

    @Value("${securityConfig.tokenHeader:Authorization}")
    private String tokenHeader;
    @Value("${securityConfig.tokenPrefix:Bearer}")
    private String tokenPrefix;
    @Value("${securityConfig.key:key}")
    private String secretKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/sign-up").anonymous()
                        .requestMatchers("/logout").authenticated()
                        .requestMatchers("/admin/**").hasRole(EnumRole.ROLE_ADMIN.getRole())
                        .requestMatchers("/moderator/**").hasAnyRole(
                                EnumRole.ROLE_MODERATOR.getRole(), EnumRole.ROLE_ADMIN.getRole())
                        .requestMatchers("/account/**").hasAnyRole(
                                EnumRole.ROLE_COMMON.getRole(), EnumRole.ROLE_MODERATOR.getRole(), EnumRole.ROLE_ADMIN.getRole())
                        .requestMatchers(HttpMethod.GET, ADVERTISEMENT_URL).permitAll()
                        .requestMatchers(ADVERTISEMENT_URL).authenticated()
                        .anyRequest().permitAll())
                .exceptionHandling(e -> e
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint()))
                .logout(l -> l.logoutSuccessHandler(logoutSuccessHandler()))
                .addFilter(new AuthorizationFilter(authenticationManager(), tokenMapper()))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        final ProviderManager providerManager = new ProviderManager(List.of(authenticationProvider));
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
}
