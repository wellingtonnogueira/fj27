package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class,ServletSpringMVC.class})
public class AppWebConfiguration {
	
	@Bean
	private InternalResourceViewResolver internalResourceViewController() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver; //return the resolver
	}

}
