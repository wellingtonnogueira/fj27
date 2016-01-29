package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProductDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses={
		HomeController.class
		,ServletSpringMVC.class
		, ProductDAO.class})
public class AppWebConfiguration {
	
	@Bean
	private InternalResourceViewResolver internalResourceViewController() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver; //return the resolver
	}
	
	@Bean
	private MessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("/WEB-INF/messages");
		//source.setDefaultEncoding("UTF-8");
		source.setCacheSeconds(1);
		
		return source;
		
	}

}
