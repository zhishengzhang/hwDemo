package zhang.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import zhang.model.UserDetails;
import zhang.repository.UserDetailsRepository;
import zhang.util.valitation.ParamValidate;
import zhang.util.valitation.ParamsValidate;
import zhang.util.valitation.ValidMap;

@Service
@Transactional
@Validated
public class UserDetailsServiceImpl {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public void addUser(@Valid UserDetails userDetails) {
		System.out.println("---------------");
		userDetailsRepository.save(userDetails);
	}

	public void addUserbyMap(@ValidMap Map<String, Object> paramsMap) {
		System.out.println("213213213"+paramsMap);
	}

	@ParamsValidate(
			value={@ParamValidate(name = "uuid", required = true,regex="nihao"),
				   @ParamValidate(name = "email", required = true,regex="REG|FORGET")
			      },
			postToken=true,
		    iCode=true)
	public void addUserForParamsCheck(UserDetails userDetails) {
		System.out.println("ddddd");
	}

}
