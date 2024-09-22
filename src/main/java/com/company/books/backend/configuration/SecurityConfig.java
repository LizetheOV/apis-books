
package com.company.books.backend.configuration;

import com.company.books.backend.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

/**
 * Project: repaso-spring
 * Package: com.company.books.backend.configuration
 * <p>
 * User: LOvandoV
 * Date: 15/9/2024
 * Time: 10:14
 * <p>
 */

@Configuration
public class SecurityConfig {

  @Autowired
  @Lazy
  private JwtRequestFilter jwtRequestFilter;

  @Bean
  public UserDetailsManager userDetailsManager(DataSource dataSource) {

    return new JdbcUserDetailsManager(dataSource);

  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configure -> {

      configure.requestMatchers(HttpMethod.GET, "/api/v1/libros").hasRole("LECTURA")
      .requestMatchers(HttpMethod.GET, "/api/v1/libros/**").hasRole("LECTURA")
      .requestMatchers(HttpMethod.POST, "/api/v1/libros").hasRole("ADMINISTRADOR")
      .requestMatchers(HttpMethod.PUT, "/api/v1/libros/**").hasRole("ADMINISTRADOR")
      .requestMatchers(HttpMethod.DELETE, "/api/v1/libros/**").hasRole("ADMINISTRADOR")

//      .requestMatchers(HttpMethod.GET, "/api/v1/categorias").hasRole("LECTURA")
      .requestMatchers(HttpMethod.GET, "/api/v1/categorias/**").hasRole("LECTURA")
      .requestMatchers(HttpMethod.POST, "/api/v1/categorias").hasRole("ADMINISTRADOR")
      .requestMatchers(HttpMethod.PUT, "/api/v1/categorias/**").hasRole("ADMINISTRADOR")
      .requestMatchers(HttpMethod.DELETE, "/api/v1/categorias/**").hasRole("ADMINISTRADOR")

      .requestMatchers(
          "/api/v1/categorias",
          "/api/v1/authenticate",
          "/v3/api-docs/**",
          "/swagger-ui/**",
          "swagger-ui.html"
          ).permitAll();

    })
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement(session -> {
          session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

    http.httpBasic(Customizer.withDefaults()); //Habita Autentificación basica
    http.csrf(csrf -> csrf.disable());

    return http.build();

  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /* Registrar usuarios en memoria por defecto */
  /*@Bean
  public InMemoryUserDetailsManager userDetailsManager() {

    UserDetails admin = User.builder()
        .username("admin")
        .password("{noop}admin123")
        .roles("ADMINISTRADOR", "LECTURA")
        .build();

    UserDetails lectura = User.builder()
        .username("lectura")
        .password("{noop}lectura123")
        .roles("LECTURA")
        .build();

    return new InMemoryUserDetailsManager(admin, lectura);

  }*/


}
