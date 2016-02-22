package zhang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhang.model.AdminRole;
import zhang.repository.AdminRoleRepository;

@Service
@Transactional
public class AdminRoleServiceImpl {
	
	@Autowired
	private AdminRoleRepository roleDAO;
	
	public AdminRole getRole(int id) {
		return roleDAO.findOne(String.valueOf(id));
	}

}
