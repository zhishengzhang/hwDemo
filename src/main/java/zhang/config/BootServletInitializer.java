/**
 * 
 */
package zhang.config;

/**
 * @author himanshu
 *
 */
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import zhang.SecApplication;

@Slf4j
public class BootServletInitializer extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		log.info("XXX Application booted ");
		return application.sources(SecApplication.class, SecurityConfig.class,WebAppConfig.class,OracleConfiguration.class);
	}
	
//    @Override
//    public void onStartup(ServletContext container) {
//        // Create the 'root' Spring application context
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(Application.class, SecurityConfig.class, WebAppConfig.class);
// 
//        // Manage the lifecycle of the root application context
//        container.addListener(new ContextLoaderListener(rootContext));
// 
//        // Create the dispatcher servlet's Spring application context
//        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
//        dispatcherServlet.register(WebAppConfig.class);
//             
//        // Register and map the dispatcher servlet
//        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//    }
	
}