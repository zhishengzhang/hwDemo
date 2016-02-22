package zhang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration  
@EnableGlobalMethodSecurity(proxyTargetClass = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService customUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.userDetailsService(customUserDetailsService)
			.csrf().disable()
			.authorizeRequests().anyRequest().fullyAuthenticated() 
			.antMatchers("/sec/","/admin/**").fullyAuthenticated()
			.and()
			.formLogin()
			.loginPage("/user-login.html")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/success-login.html")
			.failureUrl("/error-login.html").permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/index.html")
			;
		//spring securiy session 的管理
	     http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired").maxSessionsPreventsLogin(false).sessionRegistry(sessionRegistry())
         .and()
         .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
         .invalidSessionUrl("/");
	}
	
	/**
	 * 防止一个用户只能在session登录
	 * @return
	 */
	 @Bean
	 public SessionRegistry sessionRegistry() {
	     SessionRegistry sessionRegistry = new SessionRegistryImpl();
	     return sessionRegistry;
	 }

}
