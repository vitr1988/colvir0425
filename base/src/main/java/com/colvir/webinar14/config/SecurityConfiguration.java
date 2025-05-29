package com.colvir.webinar14.config;

import com.colvir.webinar14.config.filter.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration /*extends AbstractHttpConfigurer<SecurityConfiguration, HttpSecurity>*/ {

    private final JwtHelper jwtHelper;

//    private final UserDetailsService userDetailsService;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
//                .securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/jwt", "/api/notifications").permitAll()
//                        .requestMatchers("/api/employees").authenticated()
                        .requestMatchers("/api/employees/**").hasRole("ADMIN")
//                        .requestMatchers("/api/departments").authenticated()
                        .requestMatchers("/api/departments/**").hasAuthority("ROLE_GUEST")
                        .anyRequest().authenticated()
                )
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
                .addFilterBefore(new TokenAuthenticationFilter(jwtHelper), BasicAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        ;

        return http.build();
    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/genres", "/api/genres").authenticated()
//                        .requestMatchers("/genre/**", "/api/genres/*").hasAuthority("ROLE_ADMIN")
//                        .requestMatchers("/books", "/api/books").authenticated()
//                        .requestMatchers("/book/**", "/api/books/*").hasAuthority("ROLE_ADMIN")
//                        .requestMatchers("/styles/**", "/webjars/**", "/images/**").permitAll()
//                        .requestMatchers("/**").permitAll()
//                        .anyRequest().permitAll())
//                .formLogin(Customizer.withDefaults());
//    }

//    @Bean
//    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults());
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("ADMIN").build());
//        return manager;
//    }
}

