package com.homework.gupao.designpattern.singleton.innerclass;

/**
 * 关于类加载顺序的测试
 *  顺序是：父类静态属性-》父类静态代码块-》
 *  子类静态变量-》子类静态代码块-》
 *  父类非静态变量-》父类非静态代码块-》
 *  父类构造函数-》子类非静态变量-》
 *  子类非静态代码块-》-》子类构造函数
 *  
 *  这样的加载顺序不是绝对的 因为静态变量和静态代码块跟声明顺序有关
 *  对于如果静态代码块中调用静态变量，那么静态变量必须在静态代码块前面声明；如果静态代码块中没有调用静态变量，那么就跟顺序有关了，谁先声明谁先被加载。说白了还是顺序加载，之所以会出现“如果静态代码块中调用静态变量，那么静态变量必须在静态代码块前面声明”，是因为变量是声明，所以出现编译错误。
 *  
 *  
 * @author dudu
 *
 */
public class InnerClassLoadOrder {
	
	public static class InnerOrder {
		static {
			System.out.println("TestInner Static!内部类静态块！");
		}
		/**
		 * 
		 */
		public final static InnerClassLoadOrder testInstance = new InnerClassLoadOrder(3);
	}
	
	public static final InnerClassLoadOrder getInstance() {
		return InnerOrder.testInstance;
	}
	
	public InnerClassLoadOrder(int i) {
		System.out.println("Test " + i + " Construct!外部类构造器！");
	}
	
	public static int value = 3;
	static {
		System.out.println("Test static 外部类静态块 " + value);
	}
	public static void main(String[] args) {
		//外部类和内部类就是先外部 后内部: 并且是引用到内部类的时候才进行加载
		InnerClassLoadOrder t = new InnerClassLoadOrder(2);
//		InnerClassLoadOrder.getInstance();//注释后并不加载内部类静态块
	}

}
