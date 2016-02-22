package zhang.util.restClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.io.BaseEncoding;

/**
 * 封装RestTemplate
 * @author zhangzs
 *
 */
@Component
public class RestClient extends RestTemplate{
	
	@Autowired
	private ClientConfigure clientConfigure;
	
	@Autowired
	SecurityProperties securityProperties;
	
	/**
	 * 重写URL地址
	 */
	@Override
	public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod)throws IOException {
		URI absoluteURI = uri;
		if(!uri.isAbsolute()){
			try {
				absoluteURI = new URI(clientConfigure.getProperties().getBaseUrl()+ uri.getPath());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}			
		}
		ClientHttpRequest clientHttpRequest =  clientConfigure.requestFactory().createRequest(absoluteURI, httpMethod);
		String authorization = securityProperties.getUser().getName() + ":" + securityProperties.getUser().getPassword();
		clientHttpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION,"Basic "+BaseEncoding.base64().encode(authorization.getBytes()));
		return clientHttpRequest;
	}
	
}
