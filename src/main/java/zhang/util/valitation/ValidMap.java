package zhang.util.valitation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 自定义校验Map类
 * @author zzs
 *
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE,PARAMETER,CONSTRUCTOR })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ValidMapValidator.class)

public @interface ValidMap {

	 String message() default "valid.map";

	 Class<?>[] groups() default {};

	 Class<? extends Payload>[] payload() default {};
	 
	/**
	 * @return array of {@code Flag}s considered when resolving the regular expression
	 */
	Flag[] flags() default { };
	

	/**
	 * Possible Regexp flags.
	 */
	public static enum Flag {

		PHONE( "12" ),
		
		EMAIL( "12" );

		//JDK flag value
		private final String value;

		private Flag(String value) {
			this.value = value;
		}

		/**
		 * @return flag value as defined in {@link java.util.regex.Pattern}
		 */
		public String getValue() {
			return value;
		}
	}
}
