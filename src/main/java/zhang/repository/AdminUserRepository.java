package zhang.repository;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import zhang.model.AdminUser;

public interface AdminUserRepository extends PagingAndSortingRepository<AdminUser, Serializable>{
	
	AdminUser findTopByLoginOrderByIdDesc(String login);

}
