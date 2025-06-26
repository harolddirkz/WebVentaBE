package com.webventas;

import com.webventas.filter.JwtRequestFilter;
import com.webventas.infraestructure.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilita CSRF (importante para APIs stateless)
                // Habilita la configuración CORS personalizada ANTES de cualquier autorización
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Usa el bean de CorsConfigurationSource

                .authorizeHttpRequests(authorize -> authorize
                        // Permitir acceso público a /api/auth/login y /usuario/create para el registro
                        // Es crucial que estos endpoints no requieran JWT para funcionar
                        .requestMatchers("/api/auth/login", "/usuario/create", "/error").permitAll()
                        // Permitir todas las peticiones OPTIONS sin autenticación (importante para CORS pre-flight)
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll() // <-- AÑADIR ESTO
                        // Todas las demás peticiones DEBEN estar autenticadas
                        .anyRequest().authenticated()
                )
                // Configurar Spring Security para no crear ni usar sesiones (STATELESS)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Añadir el filtro JWT antes del filtro de autenticación de usuario/contraseña de Spring
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Bean para la configuración CORS. Prefiero usar CorsConfigurationSource directamente.
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000")); // Añade todas tus orígenes frontend
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "Cookie")); // Asegúrate de incluir "Authorization"
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        source.registerCorsConfiguration("/**", config); // Aplica a todas las rutas
        return source;
    }
}