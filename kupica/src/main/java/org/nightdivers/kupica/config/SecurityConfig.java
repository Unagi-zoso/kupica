package org.nightdivers.kupica.config;

import static org.nightdivers.kupica.domain.member.UserRole.ANONYMOUS;
import static org.nightdivers.kupica.domain.member.UserRole.MEMBER;
import static org.nightdivers.kupica.domain.member.UserRole.SIGNING_UP;

import lombok.RequiredArgsConstructor;
import org.nightdivers.kupica.handler.OAuth2AuthenticationSuccessHandler;
import org.nightdivers.kupica.service.CustomOAuth2UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .anonymous(anonymous -> anonymous
                        .authorities(ANONYMOUS.getDescription())
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .loginPage("/login")
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/oauth2/**", "/login/**").hasAnyAuthority(ANONYMOUS.getDescription(), SIGNING_UP.getDescription())
                        .requestMatchers("/register/**", "/api/v1/auth/register").hasAuthority(SIGNING_UP.getDescription())
                        .requestMatchers("/logout").hasAuthority(MEMBER.getDescription())
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .build();
    }
}

@Configuration
class WebSecurityConfig {

    @Bean
    public DefaultOAuth2UserService defaultOAuth2UserService() {
        return new DefaultOAuth2UserService();
    }
}
