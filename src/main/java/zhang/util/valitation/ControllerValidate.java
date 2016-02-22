package zhang.util.valitation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import zhang.util.exception.DemoException;

@Component
@Aspect
@Slf4j
public class ControllerValidate {
	 
	  private static Map<String, ParamsValidate> validateMap = new HashMap<String, ParamsValidate>();
	 
	  @Before(value="execution(@zhang.util.valitation.ParamsValidate public * zhang.service.*.*(..))")
	  public void validate(JoinPoint joinPoint) throws DemoException {
	    HttpServletRequest request = null;
	    Object[] args = joinPoint.getArgs();
	    if(args!=null){
	      for (Object object : args) {
	    	  
	        if(object instanceof HttpServletRequest){
	          request = (HttpServletRequest) object;//获取请求对象
	          break;
	        }
	        
	        
	        
	      }
	    }
//	    if(request!=null){
	      //获取校验对象
	      Signature sign = joinPoint.getSignature();
	      String LongStr = sign.toLongString();
	      ParamsValidate paramsValidate = validateMap.get(LongStr);
	      if(paramsValidate == null){
	        MethodSignature msign = (MethodSignature) sign;
	        Method method = msign.getMethod();
	        paramsValidate = method.getAnnotation(ParamsValidate.class);
	        validateMap.put(LongStr, paramsValidate);
	      }
	      if(paramsValidate!=null){
	        //开始校验
//	        HttpSession session = request.getSession();
/*	        if(paramsValidate.iCode()){//校验验证码
	          Object code = session.getAttribute(IdentifyingCode.ICODE);
	          String icode = request.getParameter(IdentifyingCode.ICODE.toLowerCase());
	          if(icode==null || code==null || !icode.equalsIgnoreCase((String)code)){
	            Object count = session.getAttribute(IdentifyingCode.ICODE+"_count");//验证码比较次数
	            int c = 1;
	            String errorMsg = "验证码错误"; 
	            if(count instanceof Integer){
	              c = (Integer) count;
	              if(c<5){
//	                session.setAttribute(IdentifyingCode.ICODE+"_count", ++c);
	              }else{//验证码比较 5次及以上
//	                session.removeAttribute(IdentifyingCode.ICODE);
//	                session.removeAttribute(IdentifyingCode.ICODE+"_count");
	                errorMsg="验证码错误，请刷新验证码后再输入！";
	              }
	            }else{
//	              session.setAttribute(IdentifyingCode.ICODE+"_count", c);
	            }
	            new DemoException("VERIFICATION CODE ERROR", "VERIFICATION CODE ERROR");
	            throw new DemoException("VERIFICATION CODE ERROR", errorMsg);
	          }else{
//	            session.removeAttribute(IdentifyingCode.ICODE);
//	            session.removeAttribute(IdentifyingCode.ICODE+"_count");
	          }
	        }*/
/*	        if(paramsValidate.postToken()){//校验重复提交
	          String postToken = request.getParameter("postToken");
	          if(postToken==null || !postToken.equals(session.getAttribute(BaseController.POST_TOKEN))){
	            throw new DemoException("REPEAT SUBMIT", "请勿重复提交");
	          }else{
//	            session.setAttribute(BaseController.POST_TOKEN,BaseUtil.getRandomStr(32));
	          }
	        }*/
	           
	        ParamValidate[] pvs = paramsValidate.value();
	        if(pvs!=null){
	          for (ParamValidate pv : pvs) {
	            String name = pv.name();
	            String errorMsg = pv.errorMsg();
//	            String value = request.getParameter(name);
	            String value = getValue(args,name);
	            String sp = request.getServletPath();
	            if(value!=null){
	              String regex = pv.regex();
	              if(!"".equals(regex)){//需要正则验证
	                if(!value.matches(regex)){
	                  log.warn(sp+"参数格式错误"+name+"="+value);
	                  throw new DemoException("PARAMETER FORMAT ERROR", "".equals(errorMsg)?("参数格式错误"+name+"="+value):errorMsg);
	                }
	              }
	            }else if(pv.required()){
	            	log.warn(sp+"缺少必要参数："+name);
	              throw new DemoException("MISSING PARAMETER",  "".equals(errorMsg)?("缺少必要参数："+name):errorMsg);
	            }
	          }
	        }else{
	        	log.warn("ParamValidate is empty");
	        }
	      }else{
	    	  log.warn("Failed to get request ParamsValidate");
	      }
//	    }else{
//	    	log.warn("ControllerValidate.validate Failed to get request");
//	    }
	  }
//私有方法区域---------------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	private String getValue(Object[] args,String name) throws DemoException {
		Object object = args[0];
		if(object instanceof Map)
			return (String) ((Map<String,String>) object).get(name);
		return null;
	}
}
