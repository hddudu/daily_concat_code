package com.hongdu.src.lintcode;

import java.util.*;

public class Test {

    public static void main(String args[]) {
//        int n = new Test().findUgly(1);
//        int n = new Test().nthSuperUglyNumber3(394,new int[]{31,151,97,67,353,271,101,37});
//        int n = new Test().nthSuperUglyNumber3(6,new int[]{2,7,13,19});
        int n = new Test().kthLargestElement(5,new int[]{2,7,13,19});
        System.out.println(n);
//        System.out.println(args);
//        System.out.println(args.length);
//        System.out.println(args);
//        System.out.println("hi");
//        List<Integer> ugly = new ArrayList<Integer>(4);//默认给n个大小 避免频繁扩容
//        ugly.add(1);
//        System.out.println(ugly.size());
//        System.out.println("数组越界 : " + ugly.get(1));//数组越界
//        System.out.println(ugly.toString());
//        for (Integer i : ugly) {
//            System.out.println(i);
//        }
    }

    /**
     * 合并两个排序的整数数组A和B变成一个新的数组。
     * 给出A=[1,2,3,4]，B=[2,4,5,6]，返回 [1,2,2,3,4,4,5,6]
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here

        return A;
    }

    /**
     * 快速排序
     * @param array
     * @param lo
     * @param hi
     * @return
     */
    public static int partition(int []array,int lo,int hi){
        //固定切分
        int key = array[lo];
        while (lo < hi) {
            while (array[hi] >= key && hi > lo) {//从后半部分扫描
                hi--;
            }
            array[lo] = array[hi];//后半部分的数小于基准数 : 低位数设为 1： 把后半部分小的数交换到前半部分来
            while (array[lo] <= key && hi > lo) {
                lo++;
            }
            array[hi] = array[lo];//前半部分的数大于基准数 ：                把钱半部分大的数交换到后半部分来
        }
        //交换完毕后
        array[hi] = key;
        return hi;
    }

    /**
     * 在数组中找到第k大的元素 : (你可以交换数组中的元素的位置)
     * 给出数组 [9,3,2,4,8]，第三大的元素是 4
     *
     * 给出数组 [1,2,3,4,5]，第一大的元素是 5，第二大的元素是 4，第三大的元素是 3，以此类推
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        // write your code here
        Arrays.sort(nums);
        int count = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if(n == count) {
                return nums[i];
            }
            count++;
        }
        return 0;
    }
    public int nthSuperUglyNumber4(int n, int[] primes) {
        PriorityQueue<Long> pq = new PriorityQueue<>(n, new Comparator<Long>(){
            public int compare(Long o1, Long o2) {
                return o1 < o2 ? -1 : 1;
            }
        });
        HashSet<Long> hash = new HashSet<>();
        hash.add(1L);
        pq.offer(1L);
        for (int prime : primes) {
            hash.add((long)prime);
            pq.offer((long)prime);
        }
        long min = primes[0];
        for (int i = 0; i < n; i++) {
            min = pq.poll();
            for (int prime : primes) {
                if (!hash.contains(min * prime)) {
                    hash.add(min * prime);
                    pq.offer(min * prime);
                }
            }
        }
        return (int)min;
    }
    public int nthSuperUglyNumber3(int n, int[] primes) {
        if (n <= 0 || primes == null || primes.length == 0)
            return -1;
        int lengthOfPrime = primes.length;
        /**
         * 初始化为 0 : 质因子数组
         */
        int[] primeLocation = new int[lengthOfPrime];
        for (int i = 0; i < lengthOfPrime; i++)
            primeLocation[i] = 0;
        /**
         * 存储第n个丑数的数组
         */
        int[] uglyNum = new int[n];
        uglyNum[0] = 1;//默认第一个丑数为1
        int counter = 1;
        while(counter < n) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < lengthOfPrime; i++) {
                // primeLocation[i]代表每个丑数
                // 比如丑数2题目的2，3，5，
                // primeLocation[0]代表2的下标
                // primeLocation[1]代表3的下标
                // primeLocation[2]代表5的下标
                int temp = uglyNum[primeLocation[i]] * primes[i];// primeLocation[0,1,2,3] = 0 uglyNum[0] = 1
                min = min < temp ? min : temp;//min 一定大 所以 min = 2 7 13 19 ===>2
            }
            // 如果min和 uglyNum[primeLocation[i]] * primes[i]相等，
            // 则其对应的下标++
            for (int i = 0; i < primes.length; i++) {
                if (min == uglyNum[primeLocation[i]] * primes[i]) {
                    primeLocation[i]++;//primeLocation[0]=1
                }
            }
            uglyNum[counter] = min;
            counter++;
        }
        return uglyNum[n - 1];
    }
    public int nthSuperUglyNumber2(int n, int[] primes) {
        if (n <= 0 || primes == null || primes.length == 0)
            return -1;
        int lengthOfPrime = primes.length;
        /**
         * 初始化为 0 : 质因子数组
         */
        int[] primeLocation = new int[lengthOfPrime];
        for (int i = 0; i < lengthOfPrime; i++)
            primeLocation[i] = 0;
        /**
         * 存储第n个丑数的数组
         */
        int[] uglyNum = new int[n];
        uglyNum[0] = 1;//默认第一个丑数为1
        int min = uglyNum[primeLocation[0]] * primes[0];//uglyNum[0] * primes[0]=  1 * 2 = 2
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < lengthOfPrime; j++) {//4 u[1] * 2 = 0
                if(uglyNum[primeLocation[j]] * primes[i] > min) {//primes[j]  7 13 19 * uglyNum[1,2,3]
                    min = uglyNum[primeLocation[j]] * primes[i];
                }
            }
            for (int j = 0; j < lengthOfPrime; j++) {
                if(uglyNum[primeLocation[j]] == min) {
                    primeLocation[j] += 1;
                }
            }
            uglyNum[i] = min;
        }
        return uglyNum[n - 1];
    }
    /**
     * 超级丑数的定义是
     *  正整数并且所有的
     *      质数因子都在所给定的一个大小为 k 的质数集合内
     *      质数 :
     *      质数因子 :[2, 7, 13, 19]
     *      [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32]
     *      [1,2]
     *      ① 1 永远都是超级丑数不管给的质数集合是什么。
     *      ② 给你的质数集合已经按照升序排列。===============>已经升序排列
     *      ③ 0 < k ≤ 100, 0 < n ≤ 10^6, 0 < primes[i] < 1000
     * @param n: a positive integer
     * @param primes: the given prime list
     * @return: the nth super ugly number
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        // write your code here
        List<Integer> uglyArray = new ArrayList<>();
        uglyArray.add(1);//  1 永远都是超级丑数不管给的质数集合是什么。
        if(n == 1) {
            return 1;
        }
        while(true) {
//            int[] addUgly = new int[primes.length];//默认为0
                List<Integer> addUgly = new ArrayList<>();
            for(int j = 0, len = uglyArray.size(); j < len; j++) {// 0 < 2
                for(int i = 0; i < primes.length; i++) {//4
                    if(uglyArray.get(j) * primes[i] > uglyArray.get(len - 1)) {//2
                        addUgly.add(uglyArray.get(j) * primes[i]);//[1,2] primes[] = [2]
                    }
                }
            }
//            Arrays.sort(addUgly);
            Collections.sort(addUgly);
            uglyArray.add(addUgly.get(0));//存入2 : 1,2  1,2,4     ,8 (7)
            if(uglyArray.size() == n){
                return uglyArray.get(uglyArray.size() - 1);
            }
        }
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
     * unreachable statements 永远不会执行的语句
     * @param n
     * @return
     */
    private int findUgly(int n) {
    /**
     * 初始化数组大小 : 最大为n
     */
        int[] uglyArray = {1};//将丑数 1 存入数组中 ==> 大小根据右边值确定 为1
        List<Integer> ugly = new ArrayList<Integer>(n);//默认给n个大小 避免频繁扩容
        ugly.add(1);//新增1 为最小的丑数
        int count = 1; // 数组只有1 计数器为1
        if(n == 1) {
            return 1;
        }
        while(true){
            int chenTwo = 0;
            int chenThree = 0;
            int chenFive = 0;

		/*
			ugly数组中最新的一个丑数为ugly[count-1],
			ugly[count-1]之前的丑数与2相乘,
			求出第一个乘积大于ugly[count-1]的值保存在chenTwo中
		*/
            for(int i = 0 ; i < count ; i++){
                if(ugly.get(i) * 2 > ugly.get(count-1)){
                    chenTwo = ugly.get(i) * 2;
                    break;
                }
            }

		/*
			ugly数组中最新的一个丑数为ugly[count-1],
			ugly[count-1]之前的丑数与3相乘,
			求出第一个乘积大于ugly[count-1]的值保存在chenThree中
		*/
            for(int i = 0 ; i < count ; i++){
                if(ugly.get(i) * 3 > ugly.get(count-1)){
                    chenThree = ugly.get(i) * 3;
                    break;
                }
            }

		/*
			ugly数组中最新的一个丑数为ugly[count-1],
			ugly[count-1]之前的丑数与5相乘,
			求出第一个乘积大于ugly[count-1]的值保存在chenFive中
		*/
            for(int i = 0 ; i < count ; i++){
                if(ugly.get(i) * 5 > ugly.get(count - 1)){
                    chenFive = ugly.get(i) * 5;
                    break;
                }
            }
            //chenTwo，chenThree。chenFive的最小值为新的丑数，存入ugly数组中
            ugly.add(compareMin( chenTwo, chenThree, chenFive));//存入2
            count++;//可以不用 可以直接用 ugly.size()
            if(ugly.size()== n) {//第N个丑数
                return ugly.get(n - 1);
            }
        }
    }
}
