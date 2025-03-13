package be.kdg.programming5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity security) throws Exception {
        return security
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/", "/login", "/css/**", "/js/**", "/images/**", "/webjars/**", "/api/public", "/hotels").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/guests", "/api/guests/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mainmenu", "/hotels/hotel/{id}", "/guests/guest/{id}","/guests").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/guests/addguest", "/hotels/addhotel").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/mainmenu", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll())
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling.authenticationEntryPoint((request, response, authException) -> {
                            if (request.getRequestURI().startsWith("/api")) {
                                response.setStatus(HttpStatus.FORBIDDEN.value());
                            } else {
                                response.sendRedirect("/login");
                            }
                        }))
                .formLogin(login -> login.loginPage("/login").permitAll())
//                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

