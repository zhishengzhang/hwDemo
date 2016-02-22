package zhang.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import zhang.model.UserDetails;
import zhang.service.UserDetailsServiceImpl;
import zhang.util.exception.DemoException;

@RestController
@Slf4j
public class UserDetailsController {
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@RequestMapping(value="/addUser",method = RequestMethod.POST)
	public @ResponseBody UserDetails addUser(@RequestBody UserDetails userDatails) {
		userDetailsService.addUser(userDatails);
		return userDatails;
	}
	
	@RequestMapping(value="/addUserByMap",method = RequestMethod.POST)
	public void addUser(@RequestBody Map<String,Object> paramsMap){
		userDetailsService.addUserbyMap(paramsMap);
	}
	
	@RequestMapping("/addUserForParamsCheck")
	public void sendTelCode(@RequestBody UserDetails userDetails)throws DemoException {
		userDetailsService.addUserForParamsCheck(userDetails);
	}

	
}