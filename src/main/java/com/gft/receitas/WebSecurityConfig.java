package com.gft.receitas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.gft.receitas.services.SecurityDatabaseService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	@Autowired
    private SecurityDatabaseService securityService;
	@SuppressWarnings("deprecation")
	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	            .antMatchers("/").permitAll()
	            .antMatchers("/popular").permitAll()
	            .antMatchers("/login").permitAll()
	            .antMatchers("/register").permitAll()
	            .antMatchers("/receita/manage").hasAnyRole("admin")
	            .antMatchers("/ingrediente/manage").hasAnyRole("admin")
	            .antMatchers("/unidadeMedida/manage").hasAnyRole("admin")
	            .antMatchers("/receita/excluir").hasAnyRole("admin")
	            .antMatchers("/ingrediente/excluir").hasAnyRole("admin")
	            .antMatchers("/unidadeMedida/excluir").hasAnyRole("admin")
	            .antMatchers("/receita").hasAnyRole("user","admin")
	            .antMatchers("/ingrediente").hasAnyRole("admin")
	            .antMatchers("/unidadeMedida").hasAnyRole("admin")
	            .antMatchers("/css/**").permitAll()
	            .antMatchers("/images/**").permitAll()
	            .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
	            .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
	            .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	    
	    http.headers().frameOptions().sameOrigin();
	    
        return http.build();
	}
	
	@Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring();
    }
}
