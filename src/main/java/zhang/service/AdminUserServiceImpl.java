package zhang.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhang.model.AdminUser;
import zhang.repository.AdminUserRepository;

@Service
@Transactional
public class AdminUserServiceImpl{
	
	@Autowired
	private AdminUserRepository adminUserRepository;

	public AdminUser getUser(String login) {
		return adminUserRepository.findTopByLoginOrderByIdDesc(login);
	}
	
	/**
	 * ROLE_ prefix may be removed (some additional conf):
	 * @return
	 */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Iterable<AdminUser> getListUser() {
		return adminUserRepository.findAll();
	}

}
