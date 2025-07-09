/*
package com.webApp.blog.config;

import com.webApp.blog.repository.UserRepository;
import com.webApp.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

*/
/*@Configuration
@EnableWebSecurity*//*

public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole()) // ✅ assumes DB has "POSTER", "ADMIN", "VIEWER"
                    .build();
        };
    }
*/

//    @Bean
  //  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http
  //              .authorizeHttpRequests(auth -> auth
         //               .requestMatchers("/", "/home", "/about", "/signup", "/register", "/contact", "/css/**", "/js/**", "/images/**").permitAll()
   //                     .requestMatchers("/posts", "/posts/**").hasAnyRole("VIEWER", "POSTER", "ADMIN") // add VIEWER here
     //                   .requestMatchers("/add", "/save", "/edit/**", "/delete/**", "/bookmark/**", "/like/**", "/posts/**/comment").hasAnyRole("POSTER", "ADMIN")
       //                 .anyRequest().authenticated()
/*

                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // ✅ disable CSRF for simplicity

        return http.build();
    }
}
*/
