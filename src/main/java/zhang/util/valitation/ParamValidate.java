package zhang.util.valitation;

public @interface ParamValidate {
	  String name();
	  
	  String regex() default "";
	 
	  boolean required() default false;
	 
	  String errorMsg() default "";
}
