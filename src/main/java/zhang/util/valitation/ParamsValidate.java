package zhang.util.valitation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ParamsValidate {
	 ParamValidate[] value();
	  /**
	   * 是否验证图片验证码
	   */
	  boolean iCode() default false;
	   
	  /**
	   * 是否验证重复提交
	   */
	  boolean postToken() default false;
}
