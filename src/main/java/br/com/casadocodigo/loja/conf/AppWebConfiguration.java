package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
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
	public InternalResourceViewResolver internalResourceViewController() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver; //return the resolver
	}
	@Bean
	public MessageSource messageSource() {
		
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("/WEB-INF/messages");
		//source.setDefaultEncoding("UTF-8");
		source.setCacheSeconds(1);
		
		return source;
		
	}

	@Bean
	public FormattingConversionService mvcConversionService() {
		
		DefaultFormattingConversionService service =
				new DefaultFormattingConversionService(true);
		
		DateFormatterRegistrar register = new DateFormatterRegistrar();
		
		register.setFormatter(new DateFormatter("dd/MM/yyyy"));
		register.registerFormatters(service);
		
		return service;
	}

}
