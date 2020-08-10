package com.codegym.configuration.security;

<<<<<<< HEAD
=======
import com.codegym.configuration.security.AuthEntryPointJwt;
import com.codegym.configuration.security.AuthTokenFilter;
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
import com.codegym.service.serviceImpl.UserDetailsServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
<<<<<<< HEAD
import org.springframework.security.config.BeanIds;
=======
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
<<<<<<< HEAD
    @Autowired(required = false)
    UserDetailsServiceimpl userDetailService;
    @Autowired
    TokenJWTFilter tokenJWTFilter;
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
=======
    @Autowired
    UserDetailsServiceimpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

<<<<<<< HEAD
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().permitAll()
                .and().cors();
        http.addFilterBefore(tokenJWTFilter, UsernamePasswordAuthenticationFilter.class);
//        http.csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login").permitAll().and().
//                authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')").and().
////                authorizeRequests().antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')").and().
//        authorizeRequests().antMatchers("/member").access("hasRole('ROLE_MEMBER')").and().
//                authorizeRequests().antMatchers("/user").access("hasRole('ROLE_USER')")
//                .anyRequest().authenticated()
//                .and().cors();
//        http.addFilterBefore(tokenJWTFilter, UsernamePasswordAuthenticationFilter.class);
=======
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/v1/auth/**", "/api/v1/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
    }

}

<<<<<<< HEAD
//.and().
//    authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')").and().
//        authorizeRequests().antMatchers("/user").access("hasRole('ROLE_USER')")
=======
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
