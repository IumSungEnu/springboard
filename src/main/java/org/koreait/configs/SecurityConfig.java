package org.koreait.configs;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.build();
    }

    //정적(css, js, images, errors) 관리
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return w ->w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/errors/**");
    }
}