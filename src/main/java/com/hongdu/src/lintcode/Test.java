package com.hongdu.src.lintcode;

import java.util.*;

public class Test {

    public static void main(String args[]) {
//        int n = new Test().findUgly(1);
//        int n = new Test().nthSuperUglyNumber3(394,new int[]{31,151,97,67,353,271,101,37});
//        int n = new Test().nthSuperUglyNumber3(6,new int[]{2,7,13,19});
//        int n = new Test().kthLargestElement(5,new int[]{2,7,13,19});
//        System.out.println(n);
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
    public static int kmp(String str, String dest,int[] next){//str文本串  dest 模式串
        for(int i = 0, j = 0; i < str.length(); i++){
            while(j > 0 && str.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if(str.charAt(i) == dest.charAt(j)){
                j++;
            }
            if(j == dest.length()){
                return i-j+1;
            }
        }
        return 0;
    }
    public static int[] kmpnext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;
        for(int i = 1,j = 0; i < dest.length(); i++){
            while(j > 0 && dest.charAt(j) != dest.charAt(i)){
                j = next[j - 1];
            }
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    /**
     * ReetrantLock
     * ArrrayBlockingQueue
     * LinkedBlockingQueue
     * 特性比较
     * 1.阻塞队列的数据结构
     * 2.可重入锁
     * 3.更好的使用可重入锁来操作阻塞队列
     *
     * 可重入： 可以重复拿锁 （在拿到锁的情况下可以重复拿锁）
     *
     */

    /**
     * 合并两个排序的整数数组A和B变成一个新的数组。
     * 给出A=[1,2,3,4]，B=[2,4,5,6]，返回 [1,2,2,3,4,4,5,6]
     * 方法一： 暴力比较法
     *      找到一个数组中最大
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     *
     * LinkedList 是一个继承于AbstractSequentialList的双向链表。它也可以被当作堆栈、队列或双端队列进行操作。
     * LinkedList 实现 List 接口，能对它进行队列操作。
     * LinkedList 实现 Deque 接口，即能将LinkedList当作双端队列使用。
     * LinkedList 实现了Cloneable接口，即覆盖了函数clone()，能克隆。
     * LinkedList 实现java.io.Serializable接口，这意味着LinkedList支持序列化，能通过序列化去传输。
     * LinkedList 是非同步的。
     *
     *LinkedList的本质是双向链表。
     * (01) LinkedList继承于AbstractSequentialList，并且实现了Dequeue接口。
     * (02) LinkedList包含两个重要的成员：header 和 size。
     * 　　header是双向链表的表头，它是双向链表节点所对应的类Entry的实例。Entry中包含成员变量： previous, next, element。其中，previous是该节点的上一个节点，next是该节点的下一个节点，element是该节点所包含的值。
     * 　　size是双向链表中节点的个数。
     *
     * LinkedList实际上是通过双向链表去实现的。既然是双向链表，那么它的顺序访问会非常高效，而随机访问效率比较低。
     *     既然LinkedList是通过双向链表的，但是它也实现了List接口{也就是说，它实现了get(int location)、remove(int location)等“根据索引值来获取、删除节点的函数”}。LinkedList是如何实现List的这些接口的，如何将“双向链表和索引值联系起来的”？
     *     实际原理非常简单，它就是通过一个计数索引值来实现的。例如，当我们调用get(int location)时，首先会比较“location”和“双向链表长度的1/2”；若前者大，则从链表头开始往后查找，直到location位置；否则，从链表末尾开始先前查找，直到location位置。
     *    这就是“双线链表和索引值联系起来”的方法。
     *
     *    总结：
     * (01) LinkedList 实际上是通过双向链表去实现的。
     *         它包含一个非常重要的内部类：Entry。Entry是双向链表节点所对应的数据结构，它包括的属性有：当前节点所包含的值，上一个节点，下一个节点。
     * (02) 从LinkedList的实现方式中可以发现，它不存在LinkedList容量不足的问题。
     * (03) LinkedList的克隆函数，即是将全部元素克隆到一个新的LinkedList对象中。
     * (04) LinkedList实现java.io.Serializable。当写入到输出流时，先写入“容量”，再依次写入“每一个节点保护的值”；当读出输入流时，先读取“容量”，再依次读取“每一个元素”。
     * (05) 由于LinkedList实现了Deque，而Deque接口定义了在双端队列两端访问元素的方法。提供插入、移除和检查元素的方法。每种方法都存在两种形式：一种形式在操作失败时抛出异常，另一种形式返回一个特殊值（null 或 false，具体取决于操作）。
     *
     * 总结起来如下表格：
     *
     *         第一个元素（头部）                 最后一个元素（尾部）
     *         抛出异常        特殊值            抛出异常           特殊值
     * 插入    addFirst(e)    offerFirst(e)    addLast(e)        offerLast(e)
     * 移除    removeFirst()  pollFirst()      removeLast()      pollLast()
     * 检查    getFirst()     peekFirst()      getLast()         peekLast()
     *
     * (06) LinkedList可以作为FIFO(先进先出)的队列，作为FIFO的队列时，下表的方法等价：
     *
     * 队列方法       等效方法
     * add(e)        addLast(e)
     * offer(e)      offerLast(e)
     * remove()      removeFirst()
     * poll()        pollFirst()
     * element()     getFirst()
     * peek()        peekFirst()
     *
     * (07) LinkedList可以作为LIFO(后进先出)的栈，作为LIFO的栈时，下表的方法等价：
     *
     * 栈方法        等效方法
     * push(e)      addFirst(e)
     * pop()        removeFirst()
     * peek()       peekFirst()
     *
     * 无论如何，千万不要通过随机访问去遍历LinkedList！
     *
     *
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        //LinkedList<Integer> arrAsLinked = new LinkedList<>();
        int i=0,j=0,k=0;
        int al = A.length;
        int bl = B.length;
        int[] c = new int[al + bl];
        //优化成
//        for(;i < al; i++) {
            //单层嵌套循环==》似乎走不通 ==> 换成同时循环两个数组的情况
//        }
        while(i < al && j < bl) {
            //两个数组都有值
            if(A[i] < B[j]) {
                c[k++] = A[i++];
            } else if(A[i] == B[j]) {
                c[k++] = A[i++];//同时移位
                c[k++] = B[j++];//同时移位
            } else {
                c[k++] = B[j++];
            }
        }
        //循环比较完毕后： 可能有一边的数组还有剩余的长度  例如： i == al的时候 j = 2呢?
        //拼接剩下的值素组值
        if(i == al) {
            //拼接B数组
            for(;j < bl; j++) {
                c[k++] = B[j];
            }
        }
        if(j == bl) {
            //拼接A数组
            for(;i < al; i++) {
                c[k++] = A[i];
            }
        }
        return c;
    }


    @org.junit.Test
    public void test() {
        int[] c = mergeSortedArray(new int[]{1,2,3,4},new int[]{2,4,5,6});
        for (int i : c) {
            System.out.println(i);
        }
    }

    /**
     * 给定一个字符串和一个偏移量，根据偏移量旋转字符串(从左向右旋转)
     *
     * 样例
     * 对于字符串 "abcdefg".
     *
     * offset=0 => "abcdefg"
     * offset=1 => "gabcdef"
     * offset=2 => "fgabcde"
     * offset=3 => "efgabcd"
     * 挑战
     * 在数组上原地旋转，使用O(1)的额外空间
     * @param str: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        // write your code here
        /**
         * 截取最后的几个字符串 :  the array to be copied ： 被复制的数组的长度 + the length of the copy to be returned 返回的复制的字符数组的长度
         *      public static char[] copyOf(char[] original, int newLength) {
         *         char[] copy = new char[newLength];
         *         System.arraycopy(original, 0, copy, 0,
         *                          Math.min(original.length, newLength));
         *         return copy;
         *
         *          @param   source       the characters being searched.
         *      * @param   sourceOffset offset of the source string.
         *      * @param   sourceCount  count of the source string.
         *      * @param   target       the characters being searched for.
         *      * @param   targetOffset offset of the target string.
         *      * @param   targetCount  count of the target string.
         *      * @param   fromIndex    the index to begin searching from.
         *
         *      static int indexOf ( char[] source, int sourceOffset, int sourceCount,
         *          char[] target, int targetOffset, int targetCount,
         *          int fromIndex)
         */
        String s = String.valueOf(str);
        if(offset == 0 || offset < 0) {
            //不用旋转
            /**
             * public char[] toCharArray() {
             *         // Cannot use Arrays.copyOf because of class initialization order issues
             *         char result[] = new char[value.length];
             *         System.arraycopy(value, 0, result, 0, value.length);  全局value 字符数组 + 返回的数组的复制起始位置 + 返回的数组的字符数组
             *                                          + 复制字符数组的起始位置 + 复制字符数组的数组长度
             *         return result;
             *     }
             */
            str = s.toCharArray();

        }
        if(offset > str.length) {
            throw new IllegalArgumentException("非法的偏移量参数");
        }
        if(offset == str.length) {
            /**
             * public AbstractStringBuilder reverse() {
             *         boolean hasSurrogate = false;
             *         int n = count - 1;
             *         for (int j = (n-1) >> 1; j >= 0; --j) {
             *             char temp = value[j];
             *             char temp2 = value[n - j];
             *             if (!hasSurrogate) {
             *                 hasSurrogate = (temp >= Character.MIN_SURROGATE && temp <= Character.MAX_SURROGATE)
             *                     || (temp2 >= Character.MIN_SURROGATE && temp2 <= Character.MAX_SURROGATE);
             *             }
             *             value[j] = temp2;
             *             value[n - j] = temp;
             *         }
             *         if (hasSurrogate) {
             *             // Reverse back all valid surrogate pairs
             *             for (int i = 0; i < count - 1; i++) {
             *                 char c2 = value[i];
             *                 if (Character.isLowSurrogate(c2)) {
             *                     char c1 = value[i + 1];
             *                     if (Character.isHighSurrogate(c1)) {
             *                         value[i++] = c1;
             *                         value[i] = c2;
             *                     }
             *                 }
             *             }
             *         }
             *         return this;
             *     }
             */
            System.out.println(new StringBuilder(s).reverse().toString());
        }
        /**
         * 开始旋转： 截取为两段分开的字符串 ： 局部逆序方法===========》 abcd==》 dcba  =====字符串交换位置 不是翻转吧？==》就是翻转
         */
        /**
         * original the array from which a range is to be copied=====》
         * from the initial index of the range to be copied, inclusive======》
         *  to the final index of the range to be copied, exclusive.========》
         *
         * public static char[] copyOfRange(char[] original, int from, int to) {
         *         int newLength = to - from;
         *         if (newLength < 0)
         *             throw new IllegalArgumentException(from + " > " + to);
         *         char[] copy = new char[newLength];
         *         System.arraycopy(original, from, copy, 0,
         *                          Math.min(original.length - from, newLength));
         *         return copy;
         *     }
         */
        String s1 = s.substring(0,str.length - offset);//不包含后面的位置
        String s2 = s.substring(str.length - offset);
//        System.out.println(s1 + " : " + s2);//abcde==> dcba : e ==> dcbae: eabcd
        String s3 = new StringBuilder(partReverse(s1) + partReverse(s2)).reverse().toString();
        System.out.println(s + " 旋转后的字符串: " + s3);
    }

    /**
     * 特殊情况：①字符串为""的情况②offset=0的情况③offset远大于字符串长度的情况
     * 前两种情况，如果想到了直接return就好。第三种情况难以想到，想到的话也好处理，因为如果偏移量offset为字符串长度的整数倍，
     * 那么偏移之后的结果其实就是源字符串，所以真正的偏移量应该为offset%（字符串的长度）。
     * @param str
     * @param offset
     */
    public void rotateString2(char[] str, int offset) {
        // write your code here
        char temp;
        if(offset == 0)
            return;
        if(str.length==0)
            return;
        int len = str.length;
        //abcde 1==> temp = str[4] = 'e';
        //j = 3;
        //只要3>=0
        //3--
        //交换的规律: abcde : e abcd 最后的是交换到前面 前面的是平移到后面: ==>转成平移 + 交换的操作
        for(int i = 1;i <= offset % len; i++){
            temp = str[len-1];//5-1=4
            int j = len-2;//从 被交换的位置的前一个位置开始平移
            while( j>= 0){
                str[j + 1] = str[j];
                j--;
            }
            str[0] = temp;//eabcd ==>再进行一次循环==> deabc ==>还是平移 + 交换最后一个位置 ==》就是循环交换最后一个位置到最前面 然后平移 offset 的前面的所有的字符
        }
    }
    public void rotateString3(char[] str, int offset) {
        if(str.length == 0)
            return;
        if(offset == 0)
            return;
        int len = str.length;
        char temp;
        //从第1个索引位置开始 ： 第0个索引被最后一个字符替代了
        for(int j = 1;j <= offset % len;j++) {
            temp = str[len - 1];//最后一个字符
            int k = len - 2;//最后一个字符的前面的字符串长度
            while (k >= 0) {//第0个位置的字符就是首位置也需要后移一位
                str[k + 1] = str[k];//后移位操作: 把1放到2位置的意思
                k--;
            }
            //每移动完一次 ： 都将temp值交换到首位置
            str[0] = temp;
        }
    }

    /**
     * 局部逆转字符串
     * @param s
     * @return
     */
    private String partReverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    @org.junit.Test
    public void test02() {
        char[] s = new char[]{'a','b','c','d','e'};
        new Test().rotateString(s,1);//abcd : e
        new Test().rotateString(s,2);//abcd : e
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
