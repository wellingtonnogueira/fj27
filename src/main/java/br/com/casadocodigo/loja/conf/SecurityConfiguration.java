package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.casadocodigo.loja.daos.UserDAO;

@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/products/form").hasRole("ADMIN")
		.antMatchers("/shopping/**").hasRole("COMPRADOR")
		.antMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
		.antMatchers("/products/**").permitAll()
		.antMatchers("/resources/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDAO)
			.passwordEncoder(new BCryptPasswordEncoder());
//		AuthenticationProvider authenticationProvider = new AuthenticationProvider() {
//			
//			@Override
//			public boolean supports(Class<?> authentication) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//			
//			@Override
//			public Authentication authenticate(Authentication authentication)
//					throws AuthenticationException {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//		auth.authenticationProvider(authenticationProvider);
	}
	
}
