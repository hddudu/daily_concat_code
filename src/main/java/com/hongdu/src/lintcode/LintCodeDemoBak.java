package com.hongdu.src.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LintCodeDemoBak {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b
     */
    public static int aplusb(int a, int b) {
        // write your code here
        if(a == 0) return b;
        if(b == 0) return a;
        int sum, i;
        i = a ^ b;//不考虑进位 采用异或运算 1 ^ 1 = 0 0 ^ 0 = 0 1 ^ 0 = 1 1 ^ 0 = 1
        sum = (a & b) << 1;//考虑进位的情况
        return aplusb(sum, i);
    }
    public static int aplusb2(int a, int b) {
        // write your code here
        while (b != 0) {
            //个位 + 十位
            int ta = a ^ b;
            int tb = (a & b) << 1;
            a = ta;
            b = tb;
        }
        return a;
    }

    /**
     * 3. 统计数字
     * 计算数字k在0到n中的出现的次数，k可能是0~9的一个值
     *
     * 样例
     * 例如n=12，k=1，在 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]，我们发现1出现了5次 (1, 10, 11, 12)
     * @param args
     * 对于数字n，k的出现次数 = 当n的个(十百千万...)位为2其他位数的全排列数量的总和.例如数字为n=302,k=2那么
     * 在n中k出现的次数 = 2**全排列数量 + *2*全排列数量 + **2全排列数量,
     */
    /**
     * @param k: An integer
     * @param n: An integer
     * @return: An integer denote the count of digit k in 1..n
     */
    public static int digitCounts(int k, int n) {
        // write your code here
        int sum = 0;
        for (int i = 0; i <=  n; i++) {
//            sum += digitCountTwo(k, i);
//            sum += digitCountOne(k, i);
            int temp = i;
            if(i == n && k == 0) {
                sum++;
            }
            while(temp > 0) {
                if(temp % 10 == k) {
                    sum++;
                }
                temp /= 10;
            }
        }
        return  sum;
    }
    public static int digitCounts2(int k, int n) {
        // write your code here
        if(k == 0 && n == 0) {
            return  1;
        }
        return  0;
    }
    private  static  int digitCountOne(int target, int sn) {
        int sum = 0;
        String tar = String.valueOf(target);
        String s = String.valueOf(sn);
        while ((s.indexOf(tar)) != -1) {
            ++sum;
            if(s.length() != 1) {
                s = s.substring(s.indexOf(tar) + 1);
            } else {
                break;
            }
        }
        return  sum;
    }
    private  static  int digitCountTwo(int target, int sn) {
        int sum = 0;
        if(target == sn && sn == 0) {
            sum++;
        }
        while(sn > 0) {
            if(sn % 10 == target) {
                sum++;
            }
            sn /= 10;
        }
        return  sum;
//        while(sn >= 10) {
//            /**
//             * 111 / 10 = 11
//             * 11 % 10 = 1
//             * 1111 / 10 = 111
//             * 111 % 10 = 1
//             */
//            if(sn % 10 == target) {
//                sum++;
//            }
//            sn /= 10;
//        }
//        if(target == sn) {
//            sum++;
//        }
    }

    /**
     * 素因子: 一般是在 [2,n的开平方根]里面的枚举值
     * 设计一个算法，找出只含素因子2，3，5 的第 n 小的数。
     * 符合条件的数如：1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
     * 样例
     * 如果n = 9， 返回 10
     * 素因子 理解一下 ：
     * 挑战
     * 要求时间复杂度为O(nlogn)或者O(n)
     *
     * 设待推断整数位M，M循环除以2直到不能整除。此时接着循环除以3直到不能整除。接着循环除以5直到商为1或者不能整除为止。
     * 商为1且余数为0则为丑数，否则为非丑数
     * @param args
     */
    /**
     * @param n: An integer
     * @return: the nth prime number as description.
     */
    public static int nthUglyNumber(int n) {
        // write your code here
        int m = 0;//从1开始遍历
        int count = 0;
        while (count < n) {
            m++;
            int number = m;
//            if(isUgly(number)) {
//                count++;
//            }
            while(number % 2 == 0) {
                number /= 2;
            }
            while(number % 3 == 0) {
                number /= 3;
            }
            while(number % 5 == 0) {
                number /= 5;
            }
            if(number == 1) {
                count++;
            }
        }
        return  m;
//        int s = (int)Math.sqrt(4);
    }
    public static int nthUglyNumber2(int n) {
        // write your code here
        int[] ugly = new int[n];
        ugly[0] = 1;//最小的丑数
        int num_2 = 0;
        int num_3 = 0;
        int num_5 = 0;
        for (int i = 0; i < n; i++) {
            int ug = min(ugly[num_2] * 2, ugly[num_3] * 3, ugly[num_5] * 5);
            if(ug == ugly[num_2] * 2) {
                num_2 ++;
            }
            if(ug == ugly[num_3] * 3) {
                num_3 ++;
            }
            if(ug == ugly[num_5] * 5) {
                num_5 ++;
            }
            ugly[i] = ug;
        }
        return  ugly[n - 1];
    }

    private  static int min(int a, int b, int c) {
        int min = a;
        if(b < min) min = b;
        if(c < min) min = c;
        return  min;
    }

    /**
     * 比较出三个数的最小值
     * @param two
     * @param three
     * @param five
     * @return
     */
    private  static int compareMin(int two, int three, int five) {
        if(two <= three) {
            if(two <= five) {
                return two;
            } else {
                return  five;
            }
        } else if(three <= five) {
            return three;
        } else {
            return  five;
        }
    }

    /**
     * 找出第n个丑数
     * @param n
     * @return
     */
//    private static int findUgly(int n) {
        /**
         * 初始化数组大小 : 最大为n
         */
//        int[] uglyArray = {1};//将丑数 1 存入数组中 ==> 大小根据右边值确定 为1
//        List<Integer> ugly = new ArrayList<Integer>(n);//默认给n个大小 避免频繁扩容
//        int count = 1; // 数组只有1 计数器为1
//        while(true){
//            int chenTwo = 0;
//            int chenThree = 0;
//            int chenFive = 0;

		/*
			ugly数组中最新的一个丑数为ugly[count-1],
			ugly[count-1]之前的丑数与2相乘,
			求出第一个乘积大于ugly[count-1]的值保存在chenTwo中
		*/
//            for(int i = 0 ; i < count ; i++){
//                if(ugly.get(i) * 2 >ugly.get(count-1)){
//                    chenTwo = ugly.get(i) * 2;
//                    break;
//                }
//            }

		/*
			ugly数组中最新的一个丑数为ugly[count-1],
			ugly[count-1]之前的丑数与3相乘,
			求出第一个乘积大于ugly[count-1]的值保存在chenThree中
		*/
//            for(i = 0 ; i < count ; i++){
//                if(ugly[i]*3 >ugly[count-1]){
//                    chenThree = ugly[i]*3;
//                    break;
//                }
//            }

		/*
			ugly数组中最新的一个丑数为ugly[count-1],
			ugly[count-1]之前的丑数与5相乘,
			求出第一个乘积大于ugly[count-1]的值保存在chenFive中
		*/
//            for(i = 0 ; i < count ; i++){
//                if(ugly[i]*5 >ugly[count-1]){
//                    chenFive = ugly[i]*5;
//                    break;
//                }
//            }
//
//            //chenTwo，chenThree。chenFive的最小值为新的丑数，存入ugly数组中
//            ugly[count]=compareMin( chenTwo, chenThree, chenFive);
//            count++;
//            if(count==N) //第N个丑数
//                return ugly[count-1];
//        }
//        return 0;
//        return '0';
//    }

//    private  static boolean isUgly(int n) {
//        while (n % 2 == 0) {
//            n /= 2;
//        }
//        while (n % 3 == 0) {
//            n /= 3;
//        }
//        while (n %5 == 0) {
//            n /= 5;
//        }
//        if(n == 1) {
//            return  true;
//        } else {
//            return false;
//        }
//    }

        public  static  void  main(String args[]) {
//        int[] ugly = {1};
//        ugly[9] = 1;
//        List<Integer> ugly = new ArrayList<Integer>(4);//默认给n个大小 避免频繁扩容
//        for (Integer i : ugly) {
//            System.out.println(i);
//        }
//        System.out.println(ugly.length);
//        System.out.println(digitCounts(1,1));
//        System.out.println(digitCounts(0,9));
//        System.out.println(digitCounts(2,33322));
//        long start = System.currentTimeMillis();
//        System.out.println(nthUglyNumber2(9));
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//        System.out.println(digitCountTwo(1,121212221));
//        System.out.println(digitCountOne(1,121212221));
//        System.out.println(digitCountTwo(0,0));
//        int a = 1;//10
//        int b = 3;//11
//        System.out.println(a ^ b);
//        System.out.println(a & b);
//        System.out.println(aplusb2(a,b));
        }
}
