package app.planner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Profile;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("!test")
public class SecurityConfig {

    @Autowired
    private JwtConverter jwtConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers("/error").permitAll()
        .requestMatchers("/api/services/**").permitAll()
        .requestMatchers("/actuator/**").permitAll()
        .anyRequest().authenticated())
        //.anyRequest().permitAll())
        .oauth2ResourceServer(
            oauth2-> oauth2.jwt(
                jwt -> jwt.jwtAuthenticationConverter(jwtConverter)
            ))
        .sessionManagement(
            session -> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS)
            );
        return http.build();
    }
}
