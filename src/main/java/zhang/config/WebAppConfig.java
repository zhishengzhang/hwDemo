package zhang.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;
import javax.validation.Validator;

import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import zhang.SecApplication;

@Configuration
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {
	
	private final ErrorPage errorPage = new ErrorPage(Throwable.class, "/WEB-INF/error/error.jsp");
	private final ErrorPage denyPage = new ErrorPage(HttpStatus.FORBIDDEN, "/WEB-INF/error/403.jsp");
	private final ErrorPage losePage = new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/error/404.jsp");
	private final ErrorPage notAllowedPage = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/WEB-INF/error/405.jsp");
	private final ErrorPage serverErrorPage = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/WEB-INF/error/error.jsp");
	
	/**
	 * 自定义各个状态码对应的页面配置
	 * @return
	 */
    @Bean 
    public EmbeddedServletContainerCustomizer containerCustomizer() {
            return new EmbeddedServletContainerCustomizer() {
				@Override
				public void customize(ConfigurableEmbeddedServletContainer container) {
					container.addErrorPages(errorPage,denyPage,losePage,notAllowedPage,serverErrorPage);
				}
				
            };
    }
	
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	/**
	 * 相当于
	 */
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Bean
	public HttpSessionListener httpSessionListener(){
	    return new HttpSessionListener() {
			
			@Override
			public void sessionDestroyed(HttpSessionEvent se) {
				
			}
			
			@Override
			public void sessionCreated(HttpSessionEvent se) {
				
			}
		};
	}
	
}
