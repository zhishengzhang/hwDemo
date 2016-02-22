package zhang.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class TestNihao {

	
	static class Foo{
		
		String name;
		
		String code;
		
		public void setName(String newName){
			System.out.println(newName);
		}
		
		public void setName(){
			System.out.println("------void");
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		Method method = Foo.class.getDeclaredMethod("setName", String.class);
		
		Method method2 = Foo.class.getDeclaredMethod("setName");
		
		method.setAccessible(true);
		method2.setAccessible(true);

		method2.invoke(new Foo());
		method.invoke(new Foo(),"qiao");
		
		
		List<String> list = new ArrayList<String>();
		list.add("nihao");
		list.add("nihao1");
		list.add("nihao2");
		list.add("nihao3");
		System.out.println(list.indexOf("nihao1"));
		
		System.out.println(-8 >>> 2);
		System.out.println(-8 >> 2);
		
		System.out.println("新增还原测试");
		
		
	} 
}



