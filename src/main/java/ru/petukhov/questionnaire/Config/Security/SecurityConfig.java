package ru.petukhov.questionnaire.Config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAfter(tokenAuthenticationFilter(), BearerTokenAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/v1/admin").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .oauth2ResourceServer().jwt();

    }

    @Bean
    protected TokenAuthenticationFilter tokenAuthenticationFilter(){
        return new TokenAuthenticationFilter();
    }
}
