package zhang.repository;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import zhang.model.UserDetails;

public interface UserDetailsRepository extends PagingAndSortingRepository<UserDetails, Serializable>{

}
