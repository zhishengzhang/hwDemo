package zhang.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhang.model.AdminUser;
import zhang.repository.AdminUserRepository;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminUserRepository adminUserRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		AdminUser domainUser = adminUserRepository.findTopByLoginOrderByIdDesc(login);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		log.info("XXX User name " + domainUser.getLogin());
		log.info("XXX User Role " + domainUser.getRole().getRole());
		log.debug("XXX User Role ID " + domainUser.getRole().getId());
		User user = new User(
				domainUser.getLogin(), 
				domainUser.getPassword(), 
				enabled, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked,
				getAuthorities(domainUser.getRole().getId()));
		return user;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	public List<String> getRoles(Integer role) {

		List<String> roles = new ArrayList<String>();

		if (role.intValue() == 111111) {
			roles.add("ROLE_MODERATOR");
			roles.add("ROLE_ADMIN");
		} else if (role.intValue() == 222222) {
			roles.add("ROLE_MODERATOR");
		}
		log.debug("XXX User Roles Size " + roles.size());
		return roles;
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
