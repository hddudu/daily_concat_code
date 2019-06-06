package com.homework.gupao.designpattern.prototype.test;

/**
 * ①首先考虑为什么要用到原型模式
 * 答： 
 *  1：如果类的实例化过程很复杂，很消耗时间，那么我们把这个对象的实例化过程考虑使用复制，克隆就是原型模式；
 *  	影分身 ： 就是简单克隆，
 *  	真假猴王 ： 类似深度克隆，有点本事上的差异
 *  2：好处： 简化实例化对象的过程， 不用准备任何东西，比如： 配置什么菜行；
 *                       屏蔽了对象的实例化过程，内部隐私的安全性存在， 但是在客户端，外部看来，这样是不安全的！
 * 
/就是原型模式
BeanUtils.copy()
//
JSON.parseObject();
//Guava
//Copy 工具类
 *   反射调用： 看是否有方法          
   Method [] methods = examPaper.getMethods();
		for(Method m : methods ){
			if(m.getName().startWith("get")){
				Object value = m.invoke(vo,null);
				String targetMethodName = m.getName().replace("get");
				try{
				examPaper.getMethod(targetMethodName,).invoke(value);
				}catch(NosearchMethodException e){
					continue;
				}
			}
		}
		
 * @author dudu
 *
 */
public class Why {

	public static void main(String[] args) {
		
	}
}
