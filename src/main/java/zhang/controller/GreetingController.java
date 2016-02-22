package zhang.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import zhang.model.Greeting;
import zhang.util.restClient.AsyncRestClient;
//import zhang.util.restClient.AsyncRestClient;
import zhang.util.restClient.RestClient;

@Controller
@Slf4j
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private RestClient restClient;
    
    @Autowired
    private AsyncRestClient asyncRestClient;
    
    @RequestMapping("/greeting")
    public @ResponseBody Greeting greeting(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    @RequestMapping("/testRestTemplate")
    public @ResponseBody String testRestTemplate() {
    	return restClient.getForObject("cancle/cancleTask", String.class);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/testRestTemplate2")
    public @ResponseBody String testRestTemplate2() {
    	Map<String,Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("custId", "eb13c6df-be73-4f7f-aa29-b28287b88488");
    	paramsMap.put("id", "eb13c6df-be73-4f7f-aa29-b28287b88488");
    	paramsMap.put("busyWorkSize", "偏大");
    	paramsMap.put("busyWorkSizeComplex",  "偏难");
    	Greeting greeting = new Greeting(12, "你好");
    	try {
    		
        	String response1 = restClient.postForObject("student/stuEvaluateHw", paramsMap, String.class, paramsMap);
        	String response2 = restClient.postForObject("cancle/cancleTask", paramsMap, String.class);
        	Map<String,Object> response3 = restClient.postForObject("cancle/cancleTask1", greeting, Map.class,greeting);
        	List<String> response4 = restClient.postForObject("cancle/cancleTask2", null, List.class);
        	Greeting response5 = restClient.postForObject("cancle/cancleTask3", null, Greeting.class);
        	log.info("-=-=-=-=-=-=-=-=-=-"+response1);
        	log.info("-=-=-=-=-=-=-=-=-=-"+response2);
        	log.info("-=-=-=-=-=-=-=-=-=-"+response3);
        	log.info("-=-=-=-=-=-=-=-=-=-"+response4);
        	log.info("-=-=-=-=-=-=-=-=-=-"+response5);

        	
        	//异步请求数据封装
        	ListenableFuture<ResponseEntity<List>> response6 = asyncRestClient.postForEntity("cancle/cancleTask4", new HttpEntity<List<Greeting>>(Arrays.asList(new Greeting(161616161,"166666666666666"),new Greeting(26262626,"266666666666666"))), List.class);
        	ListenableFuture<ResponseEntity<List>> response7 = asyncRestClient.postForEntity("cancle/cancleTask5", new HttpEntity<List<Greeting>>(Arrays.asList(new Greeting(161616161,"166666666666666"),new Greeting(26262626,"266666666666666"))), List.class);
        	List<Greeting> repEnt6 = response6.get().getBody();
        	List<Greeting> repEnt7 = response7.get().getBody();
        	log.info("-=-=-=-=-=-=-=-=-=-"+repEnt6);
        	log.info("-=-=-=-=-=-=-=-=-=-"+repEnt7);
        	
        	
		} catch (Exception e) {
			e.printStackTrace();	
		}
    	return "";
    }
    
    
}