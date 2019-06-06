package com.hongdu.src.gupao.vip.timu.simple;

public class TestCountJiaJia {

	public static void main(String[] args) {
		int count = 0;
		for (int i = 0; i < 100; i++) {
//			count = count ++;
			count = ++ count;
			System.out.println(count);
		}
		System.out.println(count);
		System.out.println(1 / 0.0);//无限大 Infinity
//		System.out.println(1 / 0);//无限大 Infinity
		final long max = 24 * 60 * 60 * 1000 * 1000;
		final long max2 = 24 * 60 * 60 * 1000 * 10;
		final long max3 = 24 * 60 * 60 * 1000 * 100;
		final long min = 24 * 60 * 60 * 1000;
		System.out.println(max / min);//5 奇怪
		System.out.println(max2 / min);//5 奇怪
		System.out.println(max3 / min);//5 奇怪
	}

}
