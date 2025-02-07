package fcamara.desafio.estacionamento.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // Permite acesso sem autenticação
                        .anyRequest().authenticated() // Exige autenticação para outros endpoints
                )
                .httpBasic(withDefaults()) // Habilita autenticação básica
                .csrf(csrf -> csrf.disable()); // Desabilita o CSRF

        return http.build();
    }
}