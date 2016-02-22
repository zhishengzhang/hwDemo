package zhang.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import zhang.model.AdminRole;

@Repository
public interface AdminRoleRepository extends PagingAndSortingRepository<AdminRole, String>{
}
