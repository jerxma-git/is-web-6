package com.mysite.todolist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mysite.todolist.interceptor.TimingInterceptor;
import com.mysite.todolist.interceptor.UserInjectorInterceptor;


@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    
    @Autowired
    TimingInterceptor timingInterceptor;
    @Autowired
    UserInjectorInterceptor userInjectorInterceptor;


    @Autowired
    UserDetailsService userDetailsService;
    

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(timingInterceptor);
        interceptorRegistry.addInterceptor(userInjectorInterceptor);
    }


    @Bean
    public PasswordEncoder encoder() {
        // for readability
        return NoOpPasswordEncoder.getInstance();
        // return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // .requestMatchers("/api/tasks/delete").hasRole("ADMIN")
                // .requestMatchers("/api/tasks/create").hasRole("USER")
                // .requestMatchers("/show-tasks").hasRole("USER")
                .requestMatchers("/registration").permitAll()
                .requestMatchers("/api/users/create").permitAll()
                .anyRequest().authenticated()
            )
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
