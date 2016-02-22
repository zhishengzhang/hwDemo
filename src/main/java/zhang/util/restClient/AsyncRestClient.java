package zhang.util.restClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;

import com.google.common.io.BaseEncoding;

/**
 * 封装AsyncRestTemplate的方法
 * @author zhangzs
 *
 */
@Component
public class AsyncRestClient extends AsyncRestTemplate{
	
	@Autowired
	private ClientConfigure clientConfigure;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * 重写URL地址
	 */
	@Override
	protected AsyncClientHttpRequest createAsyncRequest(URI uri, HttpMethod method)throws IOException {
		URI absoluteURI = uri;
		if(!uri.isAbsolute()){
			try {
				absoluteURI = new URI(clientConfigure.getProperties().getBaseUrl()+ uri.getPath());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}			
		}
		AsyncClientHttpRequest clientHttpRequest =  clientConfigure.asyncRequestFactory().createAsyncRequest(absoluteURI, method);
		String authorization = securityProperties.getUser().getName() + ":" + securityProperties.getUser().getPassword();
		clientHttpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION,"Basic "+BaseEncoding.base64().encode(authorization.getBytes()));
		return clientHttpRequest;
	}
	
}
