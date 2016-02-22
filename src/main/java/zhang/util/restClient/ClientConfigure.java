package zhang.util.restClient;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
@ConfigurationProperties(prefix="restClient",ignoreInvalidFields = true)
public class ClientConfigure {
	
	@Setter
	@Getter
	public ClientProperties properties;
	
	@Data
	public static class ClientProperties{
		private String maxConnection;
		private String maxConnectionPerRoute;
		private String md5Seeds;
		private String appSource;
		private String appVersion;
		private int connectTimeout;
		private int readTimeout;
		private String baseUrl;
	}

	
	@Bean(name="requestConfig")
	public RequestConfig requestConfig() {
		return RequestConfig.custom().setConnectTimeout(getProperties().getConnectTimeout()).setSocketTimeout(getProperties().getReadTimeout()).setConnectionRequestTimeout(getProperties().getConnectTimeout()).build();
	}
	
	@Bean(name="ioReactorConfig")
	public IOReactorConfig ioReactorConfig() {
		return IOReactorConfig.custom().setConnectTimeout(getProperties().getConnectTimeout()).setSoTimeout(getProperties().getReadTimeout()).build();
	}
	
	@Bean(name="httpClient")
	public CloseableHttpClient httpClient() {
		return HttpClients.custom().setDefaultRequestConfig(requestConfig()).build();
	}
	
	@Bean(name="httpAsyncClient")
	public CloseableHttpAsyncClient httpAsyncClient() {
		return  HttpAsyncClients.custom().setDefaultIOReactorConfig(ioReactorConfig()).build();
	}
	
	public HttpComponentsClientHttpRequestFactory requestFactory() {
		return new HttpComponentsClientHttpRequestFactory(httpClient());
	}
	
	public HttpComponentsAsyncClientHttpRequestFactory asyncRequestFactory() {
		return new HttpComponentsAsyncClientHttpRequestFactory(httpClient(), httpAsyncClient());
	}
}
