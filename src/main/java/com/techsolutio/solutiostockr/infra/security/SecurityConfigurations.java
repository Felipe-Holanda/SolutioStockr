package com.techsolutio.solutiostockr.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebMvcConfigurationSupport{

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(customAuthenticationProvider);
    }
    
    @Bean
    public SecurityFilterChain secutiryFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .csrf(cfsr -> cfsr.disable())
        .cors(cors -> cors.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
            .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/api/vendors/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/vendors/**").hasAnyRole("ADMIN", "USER")
            .requestMatchers(HttpMethod.PATCH, "/api/vendors/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/vendors/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(false).allowedMethods("GET","POST","PUT","DELETE");
        super.addCorsMappings(registry);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("Authorization", "Content-Type");
            }
        };
    }

}