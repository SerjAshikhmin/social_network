package com.senla.cources.config.security;

import com.senla.cources.config.security.filters.JwtBasedAuthenticationFilter;
import com.senla.cources.config.security.filters.JwtBasedAuthorizationFilter;
import com.senla.cources.service.security.TokenService;
import com.senla.cources.service.security.UserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserPrincipalService userPrincipalService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private SimpleUrlAuthenticationSuccessHandler successHandler;
    @Autowired
    private SimpleUrlAuthenticationFailureHandler failureHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/groups/**", "/users/**", "/private-messages/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/").permitAll()
                    .antMatchers(HttpMethod.GET, "/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    //.antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .csrf().disable()
                    .formLogin().disable()
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                /*.loginPage("/login")
                    .defaultSuccessUrl("/")
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .permitAll()*/
                /*.and()
                    .logout()
                    .invalidateHttpSession(true)
                    .permitAll()
                    .logoutSuccessUrl("/login")*/
                .addFilter(new JwtBasedAuthenticationFilter(authenticationManager(), tokenService))
                .addFilterAfter(new JwtBasedAuthorizationFilter(authenticationManager(), tokenService, userPrincipalService), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("user").password("{noop}pass").roles("USER");
        //auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
        auth.userDetailsService(userPrincipalService).passwordEncoder(new BCryptPasswordEncoder());
    }

}