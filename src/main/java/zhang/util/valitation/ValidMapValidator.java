package zhang.util.valitation;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT) 
public class ValidMapValidator implements ConstraintValidator<ValidMap, Map<?, ?>> {

	@Override
	public void initialize(final ValidMap annotation) {
		return;
	}

	@Override
	public boolean isValid(final Map<?, ?> map,final ConstraintValidatorContext context) {
		if (map == null || map.size() == 0)
			return true;
		System.out.println("校验map集合------"+map);
		
		// Iterate each map entry and validate
		
		return true;
	}
	
	
}
