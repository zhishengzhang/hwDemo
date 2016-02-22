package zhang.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import zhang.service.AdminUserServiceImpl;

@Controller
public class LinkNavigation {
	
	private AdminUserServiceImpl adminUserService;
	
    @Autowired
    public LinkNavigation(AdminUserServiceImpl adminUserService) {
        this.adminUserService = adminUserService;
    }
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexPage() {
		return new ModelAndView("home","list",Arrays.asList("你好1","你好2"));
	}
	
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
//    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
	@RequestMapping(value="/sec/moderation", method=RequestMethod.GET)
	public ModelAndView moderatorPage() {
		return new ModelAndView("moderation");
	}
	
	@RequestMapping(value="/admin/first", method=RequestMethod.GET)
	public ModelAndView firstAdminPage() {
		return new ModelAndView("admin-first","users",adminUserService.getListUser());
	}
	
	@RequestMapping(value="/admin/second", method=RequestMethod.GET)
	public ModelAndView secondAdminPage() {
		return new ModelAndView("admin-second","users",adminUserService.getListUser());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/sec/getMsg", method=RequestMethod.GET)
	public @ResponseBody  String getMsg() {
		return "hello authorized is success!";
	}

}
