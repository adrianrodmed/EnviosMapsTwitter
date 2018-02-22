package com.basico.viajes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.basico.viajes.authentication.MyDBAuthenticationService;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
   @Autowired
   MyDBAuthenticationService myDBAauthenticationService;
 
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(myDBAauthenticationService);
       }
 
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable();
 
       http.authorizeRequests().antMatchers("/compraList","/compra", "cuentaInfo")//
               .access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");
 
       // For MANAGER only.
       http.authorizeRequests().antMatchers("/destino").access("hasRole('ROLE_MANAGER')");
 
       // When the user has logged in as XX.
       // But access a page that requires role YY,
       // AccessDeniedException will throw.
       http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
       // Config for Login Form
       http.authorizeRequests().and().formLogin()//
               // Submit URL of login page.
               .loginProcessingUrl("/j_spring_security_check") // Submit URL
               .loginPage("/pages/registro.jsp")// MIRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa
               .defaultSuccessUrl("/cuentaInfo")//
               .failureUrl("/registro?error=true")//
               .usernameParameter("nombre")//
               .passwordParameter("pass")
               // Config for Logout Page
               // (Go to home page).
               .and().logout().logoutUrl("/cierreSesion").logoutSuccessUrl("/");
       }
   }