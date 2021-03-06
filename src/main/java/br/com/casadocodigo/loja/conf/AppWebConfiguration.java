package br.com.casadocodigo.loja.conf;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.viewresolver.JsonViewResolver;
import br.com.casadocodigo.loja.viewresolver.XMLViewResolver;

import com.google.common.cache.CacheBuilder;

@EnableWebMvc
@ComponentScan(basePackageClasses={
		HomeController.class
		,ServletSpringMVC.class
		,ProductDAO.class
		,FileSaver.class
		,ShoppingCart.class
		})
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewController() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		resolver.setExposedContextBeanNames("shoppingCart");
		
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
	
	@Bean
	public MultipartResolver multipartResolver() {
		MultipartResolver resolver = new StandardServletMultipartResolver();
		return resolver;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		
		List<ViewResolver> resolvers = Arrays.asList(
				internalResourceViewController() 
				,new JsonViewResolver()
				,new XMLViewResolver()
			);
		
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		
		
		return resolver;
	}
	
	@Bean
	public CacheManager cacheManager() {
		//CacheManager cm = new ConcurrentMapCacheManager();
		CacheBuilder<Object, Object> builder = 
				CacheBuilder
					.newBuilder()
					.maximumSize(100)
					.expireAfterAccess(5, TimeUnit.SECONDS)
					.expireAfterWrite(10, TimeUnit.SECONDS);
		GuavaCacheManager gcm = new GuavaCacheManager();
		gcm.setCacheBuilder(builder);
		return gcm;
	}
	
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		
		configurer.enable();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}

}
