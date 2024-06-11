package com.example.flatsharing.user.config;

import com.example.flatsharing.user.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setExposedHeaders(Arrays.asList("Authorization"));
                    configuration.setAllowedOrigins(Arrays.asList("*"));
                    configuration.setAllowedMethods(Arrays.asList("*"));
                    configuration.setAllowedHeaders(Arrays.asList("*"));
                    configuration.applyPermitDefaultValues();
                    return configuration;
                }))
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/find/**").permitAll()

                        .requestMatchers("/ws/**").permitAll()
                        .requestMatchers("/chat/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/advertisements")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/advertisements/{id}")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/comments")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/comments/{id}")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/comments/parent/{id}")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/{id}")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/email/{email}")
                        .permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger/**", "/api-docs.json/**", "/login/**", "/register/**")
                        .permitAll()
                        .anyRequest().authenticated()
                ).userDetailsService(userDetailsService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
