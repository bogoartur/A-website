// package br.univille.fso2024a.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests(authz -> authz
//                 // Permite acesso a recursos estáticos
//                 .requestMatchers("/webjars/**", "/css/**", "/js/**", "/images/**", "/site.css", "/favicon.ico").permitAll()
//                 // Permite acesso às páginas de login e registro
//                 .requestMatchers("/auth/login", "/auth/registro", "/auth/**").permitAll()
//                 // Restringe acesso à página principal para usuários autenticados
//                 .requestMatchers("/homepage", "/homepage/**").authenticated()
//             )
//             .formLogin(form -> form
//                 .loginPage("/auth/login")                // Define a página de login customizada
//                 .loginProcessingUrl("/auth/login")       // Define a URL de processamento de login
//                 .usernameParameter("arroba")              // Define o parâmetro de nome de usuário
//                 .passwordParameter("senha")               // Define o parâmetro de senha
//                 .defaultSuccessUrl("/homepage", true)     // Redireciona para a homepage após login bem-sucedido
//                 .permitAll()
//             )
//             .logout(logout -> logout
//                 .logoutUrl("/logout")
//                 .logoutSuccessUrl("/auth/login?logout")   // Redireciona para login após logout
//                 .permitAll()
//             );
//             //.csrf().disable(); // Desabilite o CSRF se não estiver usando formulários Thymeleaf corretamente

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }
