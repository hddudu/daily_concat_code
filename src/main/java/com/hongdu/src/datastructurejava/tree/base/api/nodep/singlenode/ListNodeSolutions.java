package com.hongdu.src.datastructurejava.tree.base.api.nodep.singlenode;

import org.junit.Test;

import java.lang.reflect.Array;

public class ListNodeSolutions {
    public ListNodeSolutions(){}
    /**
     * 因此有人会说，可以直接根据hashcode值判断两个对象是否相等吗？肯定是不可以的，因为不同的对象可能会生成相同的hashcode值。
     * 虽然不能根据hashcode值判断两个对象是否相等，
     * 但是可以直接根据hashcode值判断两个对象不等，如果两个对象的hashcode值不等，则必定是两个不同的对象。如果要判断两个对象是否真正相等，
     * 必须通过equals方法。
     *
     * 也就是说对于两个对象，如果调用equals方法得到的结果为true，则两个对象的hashcode值必定相等；
     *
     * 　　如果equals方法得到的结果为false，则两个对象的hashcode值不一定不同；
     *
     * 　　如果两个对象的hashcode值不等，则equals方法得到的结果必定为false；
     *
     * 　　如果两个对象的hashcode值相等，则equals方法得到的结果未知
     *
     * @param head: the first node of linked list.
     * @return: An integer
     */
    public int countNodes(MySingleLinkNode head) {
        String s = "";
//        s.equals()
        // write your code here
        if(head == null) {
            return 0;
        }
        int count = 0;
        MySingleLinkNode temp = head;
        System.out.println(temp.hashCode());//100403510
        System.out.println(temp);//com.hongdu.src.datastructurejava.tree.base.api.nodep.singlenode.MySingleLinkNode@61bb121f
        while(head != null) {
            head = head.next;
            count++;
            System.out.println(head.hashCode());//100403510
            System.out.println(head);//com.hongdu.src.datastructurejava.tree.base.api.nodep.singlenode.MySingleLinkNode@61bb121f
            if(temp == head) {
                break;
            }
        }
        return count;
    }
    @Test
    public void test() {
        MySingleLinkNode node = new MySingleLinkNode(1);
        MySingleLinkNode node2 = new MySingleLinkNode(2);
        MySingleLinkNode node3 = new MySingleLinkNode(3);
        //执行链接
        node.next = node2;
        node2.next = node3;
        node3.next = node;//形成了循环链表
        System.out.println(countNodes(node));
    }

    /**
     *设计一种方式检查一个链表是否为回文链表。
     * 核心就是找到链表中点，翻转链表，然后判定。
     * O(n)的时间和O(1)的额外空间。
     * ①找到链表中点 ==》 翻转
     * ②比较两链表
     * @param head: A ListNode.
     * @return: A boolean.
     */
    public boolean isPalindrome(MySingleLinkNode head) {
        // write your code here
        // write your code here
        /**
         * 方式①
         *      1:建立快慢指针 快指针一次走两个 慢指针一次走一个
         *      2:此时慢指针在中心，从中间开始反转剩下链表
         *      3:判断从头开始到中间和从末尾开始到中间的这两个链表是否相等，若相等则说明是回文结构
         * 方式②
         * 方式③
         */
        MySingleLinkNode fast = head;//快指针
        MySingleLinkNode slow = head;//慢指针
        //找中间结点: 判断结点都不为空
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //两个结点的情况： slow 是 B; fast 是null
        //辅助结点
        MySingleLinkNode prev = null;
        //翻转 中间结点后面的链表 ==> 形成两个链表 ==> 创建一个新的链表指针 指向新结点
        while (slow != null) {
            MySingleLinkNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        MySingleLinkNode compareHead = head;
        while (prev != null) {
            if(prev.val == compareHead.val) {
                compareHead = compareHead.next;
                prev = prev.next;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 翻转一个链表 : 给出一个链表1->2->3->null，这个翻转后的链表为3->2->1->null
     * 挑战
     * 在原地一次翻转完成
         * 用IF排除所有难处理的case
         * 初始化前两个节点开始循环
         * 初始化的时候别忘了头节点无法进入循环, 所以要手动指向null
         * 以后类似题都要注意无法进入循环的节点提前处理好
     * @param head: n
     * @return: The new head of reversed linked list.
     */
    public MySingleLinkNode reverse(MySingleLinkNode head) {
        // write your code here
        if(head == null) {
            return null;
        }
        if(head.next == null) {
            return head;
        }
        /**
         * 翻转链表： 一个当前的结点 + 当前结点的下一个结点 ==》 需要辅助的变量
         * 头结点无法进入循环 ==》 手动指向null
         * 思路:
         * ①使用三个额外的指针记录
         * ②
         * 一个一个的把输入链表的节点放到一个新的链表头部。所以思路就是建立一个新的链表头，
         * 每次遍历输入链表的节点都把他放到新链表的头部，这样遍历完成后就获得了反转的链表。详细代码注释见下。
         */
//        MySingleLinkNode newHead = head;//建立新表头==>这不叫建立新表头
//        MySingleLinkNode reverseHead = null;//这个其实就是新建立尾巴结点 建立一个新的节点用来存放结果
//        //将遍历的结点插入到表头之前
//        while (newHead != null) {
//            //加入结点: 如果在第三方加入结点呢 : 不要直接加入 通过引用转移加入
//            MySingleLinkNode secode = newHead.next; //先处理第一个节点newHead，所以需要一个指针来存储newHead的后继
//            newHead.next = reverseHead;//头指针指向null :  将newHead放到新链表头节点的头部
//            reverseHead = newHead;//转移新建结点的指向 : /移动新链表的头指针，让它始终指向新链表头部
//            newHead = secode;//指针移动下一个位置 : 继续处理原链表的节点，即之前指针存放的后继，循环往复
//        }
//        return reverseHead;
//        MySingleLinkNode cur = head;
//        MySingleLinkNode curNext = head.next;
//        MySingleLinkNode curNextNext = head.next.next;
//        while(curNextNext != null) {
//            //反向
//            curNext.next = cur;
//            //三个指针后移动
//            cur = curNext;
//            curNext = curNextNext;
//            curNextNext = curNextNext;
//        }
        //移动完毕之后
//        curNext.next = cur;
//        cur.next = null;//断裂头结点指向
        //因为没有对null 头的反向做处理
        MySingleLinkNode cur = head;// 方式三：MySingleLinkNode cur = null;
        MySingleLinkNode reverseHead = null;
        while (cur != null) {   // 方式三：head != null
            //先存储临时值
            MySingleLinkNode temp = cur.next; // 方式三： cur = head.next;
            //反向: 头结点也要反向 ==> 反向后cur就是头结点指针 : 就是后面要链接指向的指针
            cur.next = reverseHead;//
            //保存反向的链表 ==> 避免死循环
            reverseHead = cur;//保存已经反向的链表
//            temp.next = cur;
            //再存储临时值
            cur = temp;//循环下一个链表 // 方式三：head = cur;
        }
        return reverseHead;
//            temp.next = cur;//反向
//            //存储下次反向的临时变量
//            temp = cur.next;
//            cur = temp;//2 --> 1
//            cur.next = null;//

//            cur.next.next = cur;
//            cur = temp;
//            MySingleLinkNode temp = head.next;//保存当前的下一个结点
//            temp.next = cur;//cur 还是指向下一个结点呢 ：
//            cur.next = null;
//            cur = temp;//当前结点又是 前一个结点的下一个结点 ： 然后下一个temp又是当前结点的下一个结点
//        }
//        return curNext;//返回的是最后一个结点为null的前继指针
    }

    /**
     * 递归调==》 其实就是先压栈 然后弹栈的过程 >返回值是先返回的
     * @param head
     * @return
     */
    public MySingleLinkNode reverseByDigui(MySingleLinkNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        /**
         * 递归思路：
         */
        MySingleLinkNode second = head.next;//将下一个的输入 值 ==》 存在second里面
        MySingleLinkNode reverseHead = reverseByDigui(second);//将调用后的结果存储，这个结果就是最终结果。之后利用递归，调用刚才存好的输入
        second.next = head;//反转
        //上面一步的调用已经完成以second为首的链表的反转，所以现在second变成了反转完成后的尾节点
        //把这个尾节点的next指向一开始输入的前驱，即head，完成整个链表反转
        //最开始的头节点要变成尾节点，即在后面补null使链表终结
        head.next = null;
        return reverseHead;//这个是取返回的 翻转的链表
    }

    @Test
    public void test02() {
        MySingleLinkNode node = new MySingleLinkNode(1);
        MySingleLinkNode node2 = new MySingleLinkNode(2);
        MySingleLinkNode node3 = new MySingleLinkNode(3);
        //执行链接
        node.next = node2;
        node2.next = node3;
        MySingleLinkNode.printMySingleLinkNode(node);//打印单链表
        System.out.println("翻转之后: ");
        MySingleLinkNode.printMySingleLinkNode(reverse(node));
    }

    /**
     * 给点两个有序链表的公共部分：
     * 给定两个头指针 head1 head2
     *  如果head1的值小于head2 ，head1往下移动
     *  如果head2的值小于head1，head2往下移动
     *  如果head1的值与head2的值相对，则打印这个值，然后head1与head2都往下移动
     *  head1或head2有任何一个移动到null，整个过程停止
     */
    public MySingleLinkNode getCommonPart(MySingleLinkNode head1, MySingleLinkNode head2) {
        MySingleLinkNode retNode = null;//retNode 一直都是头指针
        boolean flag = true;
        while(head1 != null && head2 != null) {
            if(head1.val == head2.val) {
                if(flag) {
                    retNode = new MySingleLinkNode(head1.val);
                    flag = false;
                } else {
                    if(retNode != null) {//拼接结点:在结点的尾部拼接结点==》 但是只有头结点
//                        MySingleLinkNode.insertLast(retNode,new MySingleLinkNode(head1.val));//直接拼接原来的链表头指针 ==》 容易造成死循环
                        //找到最后一个结点： 就是最后一个结点next == null
                        MySingleLinkNode temp = retNode;//指定临时指针 保证本身链表不变
                        while (temp.next != null) {
                            temp = temp.next;
                        }
                        temp.next =  new MySingleLinkNode(head1.val);
                    }
                }
                //新建结点 拼接到返回的结点之后
                head1 = head1.next;
                head2 = head2.next;
            } else if(head1.val > head2.val) {
                head2 = head2.next;
            } else {
                head1 = head1.next;
            }
        }
        return retNode;
    }
    @Test
    public void test03() {
        MySingleLinkNode node = new MySingleLinkNode(1);
        MySingleLinkNode node2 = new MySingleLinkNode(2);
        MySingleLinkNode node3 = new MySingleLinkNode(3);
        MySingleLinkNode node5 = new MySingleLinkNode(5);
        //执行链接
        node.next = node2;
        node2.next = node3;
        MySingleLinkNode.printMySingleLinkNode(node);//打印单链表
        //拼接结点
//        MySingleLinkNode.insertLast(node,node5);
//        System.out.println("拼接node5之后: ");
//        MySingleLinkNode.printMySingleLinkNode(node);//打印单链表
        MySingleLinkNode n = new MySingleLinkNode(1);
        MySingleLinkNode n2 = new MySingleLinkNode(2);
        MySingleLinkNode n3 = new MySingleLinkNode(3);
        MySingleLinkNode n4 = new MySingleLinkNode(4);
        //执行链接
        n.next = n2;
        n2.next = n3;
        n3.next = n4;
        MySingleLinkNode.printMySingleLinkNode(n);//打印单链表

        MySingleLinkNode comLink = getCommonPart(node, n);
        System.out.println("寻找到的公共结点: ");
        MySingleLinkNode.printMySingleLinkNode(comLink);//打印单链表
    }

    /**
     * 翻转单向链表 和 双向链表 : 最好的解法 ： 不借助外部临时变量
     */
    public MySingleLinkNode reverseSingleList(MySingleLinkNode head) {
        MySingleLinkNode pre = null;
        MySingleLinkNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;//翻转头结点
            pre = head;//保存翻转的链表
            head = next;// 因为此时的 head.next = pre = null 因为head.next已经保存在了next中
//            head = next;//此处的next = head.next 所以如果写: head = head.next 是有问题
        }
        return pre;
    }

    public MySingleLinkNode generateMySingleLinkNode() {
        MySingleLinkNode n = new MySingleLinkNode(1);
        MySingleLinkNode n2 = new MySingleLinkNode(2);
        MySingleLinkNode n3 = new MySingleLinkNode(3);
        MySingleLinkNode n4 = new MySingleLinkNode(4);
        MySingleLinkNode n5 = new MySingleLinkNode(5);
        //执行链接
        n.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        return n;
    }
    public MySingleLinkNode generateMySingleLinkNodeCf() {
        MySingleLinkNode n = new MySingleLinkNode(0);
        MySingleLinkNode n2 = new MySingleLinkNode(0);
        MySingleLinkNode n3 = new MySingleLinkNode(0);
        MySingleLinkNode n4 = new MySingleLinkNode(9);
        MySingleLinkNode n5 = new MySingleLinkNode(9);
        //执行链接
        n.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        return n;
    }
    public MySingleLinkNode generateMySingleLinkNodeCfTbks() {
        MySingleLinkNode n = new MySingleLinkNode(-15);
        MySingleLinkNode n2 = new MySingleLinkNode(-15);
        MySingleLinkNode n3 = new MySingleLinkNode(-14);
        MySingleLinkNode n4 = new MySingleLinkNode(-14);
        MySingleLinkNode n5 = new MySingleLinkNode(5);
        MySingleLinkNode n6 = new MySingleLinkNode(5);
        //执行链接
        n.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        return n;
    }
    public MySingleLinkNode generateMySingleLinkNodeXun() {
        MySingleLinkNode n = new MySingleLinkNode(-15);
        MySingleLinkNode cur = n;
        //执行链接
        int count = 0;
        while (count++ < 30) {
            MySingleLinkNode temp = new MySingleLinkNode(-15);
            cur.next = temp;
            cur = temp;
        }
        count = 0;
        while (count++ < 30) {
            MySingleLinkNode temp = new MySingleLinkNode(-14);
            cur.next = temp;
            cur = temp;
        }
        count = 0;
        while (count++ < 60) {
            MySingleLinkNode temp = new MySingleLinkNode(-13);
            cur.next = temp;
            cur = temp;
        }
        count = 0;
        while (count++ < 50) {
            MySingleLinkNode temp = new MySingleLinkNode(8);
            cur.next = temp;
            cur = temp;
        }
        count = 0;
        while (count++ < 10) {
            MySingleLinkNode temp = new MySingleLinkNode(13);
            cur.next = temp;
            cur = temp;
        }
        return n.next;
    }
    public MySingleLinkNode generateMySingleLinkNodeCfTbzj() {//-13->-12->-10->-3->-3->1->2->2->6->15->null
        MySingleLinkNode n = new MySingleLinkNode(-15);
        MySingleLinkNode n2 = new MySingleLinkNode(-14);
        MySingleLinkNode n3 = new MySingleLinkNode(-14);
        MySingleLinkNode n4 = new MySingleLinkNode(-10);
        MySingleLinkNode n5 = new MySingleLinkNode(-10);
        MySingleLinkNode n6 = new MySingleLinkNode(-10);
        MySingleLinkNode n7 = new MySingleLinkNode(-9);
        MySingleLinkNode n8 = new MySingleLinkNode(-9);
        MySingleLinkNode n9 = new MySingleLinkNode(-1);
        MySingleLinkNode n10 = new MySingleLinkNode(9);
        //执行链接
        n.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n10;
        return n;
    }
    public MySingleLinkNode generateMySingleLinkNode2() {
        //3760->2881->7595->3904->5069->
        // 4421->8560->8879->8488->5040->
        // 5792->56->1007->2270->3403->6062->null
        MySingleLinkNode n = new MySingleLinkNode(3760);
        MySingleLinkNode n2 = new MySingleLinkNode(2881);
        MySingleLinkNode n3 = new MySingleLinkNode(7595);
        MySingleLinkNode n4 = new MySingleLinkNode(3904);
        MySingleLinkNode n5 = new MySingleLinkNode(5069);
        //执行链接
        n.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        MySingleLinkNode n6 = new MySingleLinkNode(4421);
        MySingleLinkNode n7 = new MySingleLinkNode(8560);
        MySingleLinkNode n8 = new MySingleLinkNode(8879);
        MySingleLinkNode n9 = new MySingleLinkNode(8488);
        MySingleLinkNode n10 = new MySingleLinkNode(5040);
        //执行链接
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n10;

        MySingleLinkNode n11 = new MySingleLinkNode(5792);
        MySingleLinkNode n12 = new MySingleLinkNode(56);
        MySingleLinkNode n13 = new MySingleLinkNode(1007);
        MySingleLinkNode n14 = new MySingleLinkNode(2270);
        MySingleLinkNode n15 = new MySingleLinkNode(3403);
        MySingleLinkNode n16 = new MySingleLinkNode(6062);
        MySingleLinkNode n17 = null;
        n10.next = n11;
        n11.next = n12;
        n12.next = n13;
        n13.next = n14;
        n14.next = n15;
        n15.next = n16;
        n16.next = n17;
        return n;
    }

    @Test
    public void testReverseSingleList() {
        MySingleLinkNode n = generateMySingleLinkNode();
        MySingleLinkNode.printMySingleLinkNode(n);//打印单链表
        MySingleLinkNode.printMySingleLinkNode(reverseSingleList(n));//打印单链表
    }
    @Test
    public void testPartReverse() {
        MySingleLinkNode n = generateMySingleLinkNode();
        MySingleLinkNode.printMySingleLinkNode(n);//打印单链表
        MySingleLinkNode ntest = MySingleLinkNode.reversePartSingleList2(n,2,4);
        MySingleLinkNode.printMySingleLinkNode(ntest);//打印单链表
    }
    @Test
    public void testGenerate() {
        MySingleLinkNode n = generateMySingleLinkNode2();
        MySingleLinkNode.printMySingleLinkNode(n);//打印单链表
    }

    /**
     * 给定一个单链表和数值x，划分链表使得所有小于x的节点排在大于等于x的节点之前。
     * 你应该保留两部分内链表节点原有的相对顺序。
     * 样例
     * 给定链表 1->4->3->2->5->2->null，并且 x=3
     * 返回 1->2->2->4->3->5->null
     */
    /**
     * ①遍历： 找到比x小的结点就移除当前位置 并插入到最后一个比x小的元素后面
     * ②遍历： 找到比x大的结点就移除当前位置 并插入到第一个比x大的元素前面
     */
    @Test
    public void testReversePart() {
        MySingleLinkNode n = generateMySingleLinkNode();
        MySingleLinkNode.printMySingleLinkNode(n);
        MySingleLinkNode node = MySingleLinkNode.reverseKGroup(n,3);
        MySingleLinkNode.printMySingleLinkNode(node);
    }

    /**
     * @param head: head is the head of the linked list
     *            while(node != null && node.next != null){
     * if(node.val == node.next.val){
     * node.next = node.next.next;
     * }else{
     * node = node.next;
     * }
     * @return: head of linked list
     */
    public MySingleLinkNode deleteDuplicates(MySingleLinkNode head) {
        // write your code here
        MySingleLinkNode cur = head;
        MySingleLinkNode next = null;
        while (cur != null) {
            //循环匹配与当前值的结点值
            next = cur.next;
            while (next != null) {
                if(cur.val == next.val) {
                    //保存下当前的结点
                    MySingleLinkNode pre = cur;//保存最近的一个结点
                    cur.next = next.next;//删除下一个
                    next = cur.next;//循环下一个
                    continue;
                }
                next = next.next;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 前提是已排序 ：==》 那么相邻的必然有序 相等就删除==>ok
     * @param head
     * @return
     */
    public MySingleLinkNode deleteDuplicates2(MySingleLinkNode head) {
        // write your code here
        MySingleLinkNode node = head;
        while(node != null && node.next != null){
            if(node.val == node.next.val){
                node.next = node.next.next;
            }else{
                node = node.next;
            }
        }
       return head;
    }

    /**
     * 删除头结点
     * @param head
     */
    public static MySingleLinkNode removeHead(MySingleLinkNode head) {
        MySingleLinkNode cur = head;
        cur = cur.next;//指针移位
        head = cur;
        return head;
    }

    /**
     * 给定一个排序链表，删除所有重复的元素只留下原链表中没有重复的元素。
     *
     * 样例 : 已排序: 那么就先找出重复的value ==》然后删除当前两个结点 ==》 判断下一次的值是否等于当前value
     * 给出 1->2->3->3->4->4->5->null，返回 1->2->5->null
     *
     * 给出 1->1->1->2->3->null，返回 2->3->null
     * @param head: head is the head of the linked list
     * @return: head of the linked list
     */
    /**
     * 链表操作心得： ①拼接 ==》 是拼接整个链表 ==》 因为链表是带指针 及指针地址的
     *              ②head = head.next ==》 这个操作既是移位操作 也是删除操作 ==》 相当于指针指向下一个地址位置 而当前位置就不被指向 消失了
     *                      始终注意 ： 这个操作终究是指针移位操作 ===》 并非链表指针断裂操作
     *                      断裂操作如下： head.next = head.next.next ==》必须是指针的指向 next的方向改变 并且是在左边改变
     *              ③堆中的链表对象是初始化了，不变的，然后head和pre是两个指针 ，，可以改变； 如果他们没有互相指向，那么他们也是独立的
     * @param head
     * @return
     */
    public MySingleLinkNode deleteDuplicatesII(MySingleLinkNode head) {
        // write your code here
        //我应该定义一个 返回的链表
        MySingleLinkNode temp = null;
        MySingleLinkNode pre = null;
        int recordVal = Integer.MAX_VALUE;
        while ( head != null && head.next != null) {
            if(recordVal != Integer.MAX_VALUE && recordVal == head.val) {
//                //删除头结点
//                head = head.next;//移位 ：指针移位 其实就是删除 5
                temp.next = head.next;
//                pre.next = head;//指针断裂 4 5 这怎么是删除头结点呢  ?==> 不对 应该是删除head的头结点 然后接上pre
            } else if(head.val == head.next.val) {
                recordVal = head.val;
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                head = head.next;// 4 5
                //开始断裂
                if(temp == null){
                    temp = head;
                } else {
                    temp.next = head;
                }
                if(pre == null || head  == null) {
                    pre = head;
                }
            } else {
                if(pre == null) {
                    pre = head;//pre 还是等于4 5了
                }
                // 定义临时指针断裂操作
                temp = head;
                head = head.next;
            }
        }
        return pre;
//        MySingleLinkNode node = head;
//        MySingleLinkNode pre = null;
//        int recordVal = Integer.MAX_VALUE;
//        while (node.next != null) {
//            if(recordVal != Integer.MAX_VALUE && recordVal == node.val) {
//                //删除头结点
//                node = removeHead(node);
//                pre = node;//保存后一个结点
//            } else if(node.val == node.next.val) {
//                recordVal = node.val;
//                //删除node 以及node.next
//                if(pre == null) {//从头部开始的相等处
//                    //只要相等 一直删除
//
//                    while (node.next != null && node.val == node.next.val) {
//                        node = node.next;
//                    }
//                    node = removeHead(removeHead(node));
//                    pre = node;
//                } else {
//                    while (node.next != null && node.val == node.next.val) {
//                        node = node.next;
//                    }
//                    pre.next = node;
////                    node = pre.next; //保存后两个结点
//                }
//            } else {
//                pre = node;
//                node = node.next;
//            }
//        }
//        return node;
//        int recordVal = -1;//首先记录第一个值 ==> 1
//        MySingleLinkNode node = head;
//        MySingleLinkNode pre = head;//前一个结点
//        while (node != null && node.next != null) {
//            if(recordVal != -1 && recordVal == node.val) {
//                //删除当前值 : 其实就是删除当前结点
////                MySingleLinkNode temp = node;
////                temp = temp.next;
////                node = temp;//不用移位
//                node = node.next;
//                head = node;
//                continue;
//            }
//            if(node.val == node.next.val) {
//                //删除这个结点 并记录下这个值
//                recordVal = node.val;
//                //删除 两个
////                MySingleLinkNode temp = node;
//                if(pre == head) {
//                    pre.next = node.next.next;
//                    head = pre;
//                } else {
//                    node = node.next.next;//头结点的情况
//                    head = node;
//                }
//            } else {
//                pre = node;
//                node = node.next;
////                MySingleLinkNode temp = node;
////                temp = temp.next;
////                node = temp;
//            }
//        }
//        return head;
//        while (node != null && node.next != null) {
//            if(recordVal == node.next.val) {
//                //删除当前两个结点:
//                MySingleLinkNode temp = node;//保存当前结点: 删除当前结点 及 后面的结点
//                node.next.next = null;//删除下一个结点 ==》 断裂结点 ==> 1->1-> 1->1 ->4->5
//                node.next = null;// 1->1->1
//                node = temp.next.next;
//            } else {
//                node = node.next;
//            }
////            if(node.val == node.next.val) {
////                recordVal = node.val;
////                node.next = node.next.next;//删除当前结点
////            } else {
////                node = node.next;
////            }
//        }
//        return node;
    }

    /**
     * 这道题其实是之前的删除链表中重复数字的一个升级版
     * 1.我们先创建一个temp，赋一个链表中不会出现的值给它。并且创建一个新的链表头节点dummy，用来存放链表中没有重复的数字。
     * 2.遍历给定的链表中的每一个节点，如果该节点的val中的值等于它下一个节点的val的值（也就是该数值重复），那么我们就把这个重复的
     * 值重新赋给temp。如果它与下一个值不相等，我们就判断一下它是否跟temp中的值相等，相等则说明它是重复数值的最后一个，不做处理，
     * 不想等则说明它是一个不重复的数值，把它放进新的链表dummy中。然后进行下一个节点的操作。
     * 3.由于我们的循环条件是head!=NULL && head->next!=NULL，也就是这个循环只会进行到倒数第二个节点。所以最后我们要对最后一个节点
     * 特判一下。
     * 4.这一条是专门吐槽ww的，做什么题都让我用数组！就不用！啦啦啦~
     * @param head
     * @return
     */
    public MySingleLinkNode deleteDuplicatesII2(MySingleLinkNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        MySingleLinkNode dummy = new MySingleLinkNode(Integer.MAX_VALUE);
        MySingleLinkNode temp = dummy;
        while (head != null && head.next != null) {
            if(head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                //移位 : 同时也是删除自己本身
                head = head.next;
            } else {
                temp.next = head;//拼接上哨兵结点
                temp = temp.next;//记录
                head = head.next;//其实就是删除head自己
            }
        }
        temp.next = head;
        return dummy.next;
    }
    @Test
    public void test04() {
       MySingleLinkNode n = generateMySingleLinkNodeXun();
//       MySingleLinkNode n = generateMySingleLinkNodeCfTbks();
//       MySingleLinkNode n = generateMySingleLinkNodeCf();
//       MySingleLinkNode n = generateMySingleLinkNodeCfTbzj();
        MySingleLinkNode.printMySingleLinkNode((n));//删除头结点
//        MySingleLinkNode cur = n;
//        cur.next = cur.next.next;
//        cur.next = cur.next.next;
//        cur = cur.next;//删除头
//        cur = cur.next.next;//删除头
//        n = cur;
//        MySingleLinkNode.printMySingleLinkNode((removeHead(n)));//删除头结点
        MySingleLinkNode t = deleteDuplicatesII(n);
        MySingleLinkNode.printMySingleLinkNode(t);
//        MySingleLinkNode tt = deleteDuplicates2(n);
    }

    /**
     * fun2方法的参数是一个对象，当把ss传给参数s时，s得到的是ss的拷贝，所以s和ss指向的是同一个对象，因此，使用s操作对象，ss也会受影响。
     * fun3方法的参数虽然也是一个对象，当ss传给参数s时，s得到的是ss的拷贝，但是，在fun3中改变了s指向的对象，给s重新赋值后，s与ss已经
     * 没有关系，它和ss指向了不同的对象，所以不管对s做什么操作，ss都不会受影响
     */

    /**
     * 将两个排序链表合并为一个新的排序链表
     */
    /**
     * @param l1: ListNode l1 is the head of the linked list
     * @param l2: ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public MySingleLinkNode mergeTwoLists(MySingleLinkNode l1, MySingleLinkNode l2) {
        // write your code here
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        MySingleLinkNode linkNodes = new MySingleLinkNode(-1);
        MySingleLinkNode linkNode = linkNodes;
        while (l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                linkNode.next = l1;
                l1 = l1.next;
            } else {
                linkNode.next = l2;
                l2 = l2.next;
            }
            linkNode = linkNode.next;
//            if(l1.val < l2.val) {
//                linkNode.next = l1;
//                linkNode = linkNode.next;
//                l1 = l1.next;
//            } else if (l1.val == l2.val) {
//                linkNode.next = l1;
//                linkNode = linkNode.next;
//                linkNode.next = l1;
//                linkNode = linkNode.next;
//                l1 = l1.next;
//                l2 = l2.next;
//            } else {
//                linkNode.next = l2;
//                linkNode = linkNode.next;
//                l2 = l2.next;
//            }
        }
        while (l1 != null) {
            linkNode.next = l1;
            l1 = l1.next;
            linkNode = linkNode.next;
        }
        while (l2 != null) {
            linkNode.next = l2;
            l2 = l2.next;
            linkNode = linkNode.next;
        }
        return linkNodes.next;
    }
    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int[] c = new int[A.length + B.length];
        int i = 0, j = 0;
        int m = 0;
        while (i < A.length && j < B.length) {
            if(A[i] <= B[j]) {
                c[m++] = A[i++];
            } else {
                c[m++] = B[j++];
            }
        }
        while (i < A.length) {
            c[m++] = A[i++];
        }
        while (j < B.length) {
            c[m++] = B[j++];
        }
        return c;
    }

    /*
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        int[] res = new int[A.length + B.length];
        //使用归并排序
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < A.length && j < B.length) {
            if(A[i] < B[j]) {
                res[index++] = A[i++];
            } else {
                res[index++] = B[j++];
            }
        }
        while (i < A.length) {
            res[index++] = A[i++];
        }
        while (j < B.length) {
            res[index++] = B[j++];
        }
        //赋值到原数组
        System.arraycopy(A,0,res,0,res.length);
    }
    /**
    采用归并排序的方法，首先开辟一个额外空间，大小是两个数组之和，存放排序后的数组。
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray2(int[] A, int m, int[] B, int n) {
        // write your code here
        int[] res = new int[m + n];
        //使用归并排序
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m && j < n) {
            if(A[i] < B[j]) {
                res[index++] = A[i++];
            } else {
                res[index++] = B[j++];
            }
        }
        while (i < m) {
            res[index++] = A[i++];
        }
        while (j < n) {
            res[index++] = B[j++];
        }
        //赋值到原数组
//        System.arraycopy(A,0,res,0,m+n);
        for(int k=0;k<m+n;k++)
            A[k]=res[k];
    }
    /**
     * 用处 ： 主要是完成数组替换：
     * 如果原数组和目标数组是一个相同的数组，那么它会先将需要复制的内容复制到一个临时数组中，然后再将临时数组复制该数组中，中间增加一个临时变量。
     * 以下是异常相关：
     * 如果目标数组为null将会抛出NullPointerException
     * 如果原数组为null将会抛出NullPointerException，目标数组不会修改。
     * 以下条件满足将会抛出ArrayStoreException，且目标数组不会改变：
     * 1、原数组或目标数组引用的对象不是数组类型
     * 2、原数组和目标数组的原生类型不一样，比如原数组是int，目标数组是double类型
     * 3、原数组引用的是原生类型，而目标数组引用是引用类型，比如原数组是int 目标数组是某个对象的引用， 反之也不行
     * 以下条件满足将会抛出IndexOutOfBoundsException，且目标数组不会改变：
     * 1、srcPos或destPos或length 为负数
     * 2、srcPos+length 大于 src.length ，或者 destPos+length 大于 dest.length
     * ---------------------
     * 作者：往前的娘娘
     * 来源：CSDN
     * 原文：https://blog.csdn.net/m0_37355951/article/details/77309654
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     * src:源数组；
     * srcPos:源数组要复制的起始位置；
     * dest:目的数组；
     * destPos:目的数组放置的起始位置；
     * length:复制的长度。
     * 注意：src and dest都必须是同类型或者可以进行转换类型的数组．
     */
    @Test
    public void tesetArrayCopy() {
        String src[] = new String[] { "hello", "huang", "bao", "kang" };
        String dest[] = new String [5];
        System.arraycopy(src,0,dest,0,4);
        for(String s : dest) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("=========华丽的分割线=========");
        System.arraycopy(src,0,dest,0,3);//复制的长度。
        for(String s : dest) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("=========华丽的分割线=========");
        System.arraycopy(src,0,src,1,3);//复制的长度。 ==>注意值改变了
        // 将0~2位置上的值 复制到了 1到3位置上
        //"hello", "huang", "bao", "kang" 成了下面这样
        //"hello","hello", "huang", "bao"
        for(String s : src) {
            System.out.print(s + " ");
        }
    }
    public static Object copyOf(Object a,int newLength){
        Class cl = a.getClass();
        if(!cl.isArray()) return null;
        Class componentType =cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType,newLength);// 实例化
        System.arraycopy(a,0,newArray,0,Math.min(length,newLength));
        return newArray;
    }

    /**
     * 设计一种方法，将一个字符串中的所有空格替换成 %20 。你可以假设该字符串有足够的空间来加入新的字符，且你得到的是“真实的”字符长度。
     * 你的程序还需要返回被替换后的字符串的长度。
     * 样例
     * 对于字符串"Mr John Smith", 长度为 13
     * 替换空格之后，参数中的字符串需要变为"Mr%20John%20Smith"，并且把新长度 17 作为结果返回。
     * 挑战
     * 在原字符串(字符数组)中完成替换，不适用额外空间
     * 注意事项
     * 如果使用 Java 或 Python, 程序中请用字符数组表示字符串。
     * 思路：①求出空格数
     * ②扩展数组长度
     * ③从后往前复制原字符串 ： 遇到空格复制三个 否则原样复制
     * @param string: An array of Char
     * @param length: The true length of the string
     * @return: The true length of new string
     */
    public int replaceBlank(char[] string, int length) {
        // write your code here
        int n = 0;
        for(int i = 0; i < length; i++) {
            if(string[i] == ' ') {
                n++;
            }
        }
        int newlength = length + 2 * n;
        for( int i = length - 1; i >= 0; i--) {
            if(string[i] != ' ') {
                string[--newlength] = string[i];
            } else {
                string[--newlength] = '0';
                string[--newlength] = '2';
                string[--newlength] = '%';
            }
        }
        return length + 2 * n;
    }
    /**
     * 给定一个链表，旋转链表，使得每个节点向右移动k个位置，其中k是一个非负数
     * 样例
     * 给出链表1->2->3->4->5->null和k=2
     * 返回4->5->1->2->3->null
     * 思路： ①k： 移动最后k个位置 ，
     *          ②最后k个位置上的链表加上 之前的
     *          1->2->3->4->5 ==> 1 ==> 5->1->2->3->4
     *          1->2->3->4->5 ==> 2 ==> 4->5->1->2->3
     *          1->2->3->4->5 ==> 3 ==> 3->4->5->1->2
     *          1->2->3->4->5 ==> 4 ==> 2->3->4->5->1
     *          1->2->3->4->5 ==> 5 ==> 1->2->3->4->5
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public MySingleLinkNode rotateRight(MySingleLinkNode node, int k) {
        // write your code here
        MySingleLinkNode head = node;
        if(k <= 0) {
            return head;
        }

        int total = countNodes(head);
        //求出链表的长度 ==》 取余 k
        int moveCounts = total % k;
        //前半段:
        int count = 0;
        while (count++ < (total - moveCounts)) {
            head = head.next;//此时是在count == (5 - 2) = 3位置 :: 断裂 3 后面的位置
        }
        //先指向循环位置 再断裂结点
        MySingleLinkNode temp = head;
        head = head.next;
        temp.next = null;
        //
        while (head != null) {
            head = head.next;
        }
        head.next = node;
        //求出余数 k ： 最后k个位置上面的链表
        /**
         * 思路： 逆转 + 求出长度 + 再逆转 == k位置上面的结点
         */
//        MySingleLinkNode cur = null;
//        MySingleLinkNode pre = null;
//        while (head != null) {
//            cur = head.next;
//            //翻转
//            head.next = pre;
//            pre = head;
//            head = cur;
//        }
        //翻转后是pre
//        int count = 0;
//        while (count < k) {
//
//        }
        return head;
    }

    public MySingleLinkNode rotateRight2(MySingleLinkNode head, int k) {
        if(head==null)  return null;
        int len = 1;
        MySingleLinkNode pNode = head;
        while (pNode.next != null) {
            len++;
            pNode = pNode.next;
        }// 此时指向最后一个元素
        if(k % len == 0) return head;
        k = k % len;
        //找到倒数第k个结点,即正数第len-k+1个
        MySingleLinkNode cur = head;
        MySingleLinkNode pre = null;
        int index = len - k;
        while (index-- > 0) {
            pre = cur;
            cur = cur.next;
        }
        //pre 是新链表最后一个元素 cur 是新链表头
        pre.next = null;
        pNode.next = head;
        return cur;
    }

    /**
     * 在 O(n log n) 时间复杂度和常数级的空间复杂度下给链表排序。
     * 样例
     * 给出 1->3->2->null，给它排序变成 1->2->3->null.
     * 分别用归并排序和快速排序做一遍。
     *
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list, using constant space complexity.
     */
    public MySingleLinkNode sortList(MySingleLinkNode head) {
        // write your code here
        if(head == null || head.next == null) return head;
        MySingleLinkNode mid = findMid(head);

        MySingleLinkNode temp = mid.next;//后半段 的结点
        mid.next = null;//断裂
        MySingleLinkNode headLeft = sortList(head);
        MySingleLinkNode headRight = sortList(temp);
        return merge(headLeft,headRight);
    }

    //只有在合并的时候才进行排序
    public static MySingleLinkNode merge(MySingleLinkNode left, MySingleLinkNode right) {
        if(left == null || right == null) return null;
        MySingleLinkNode node = new MySingleLinkNode(0);
        MySingleLinkNode dummy = node;
        while (left != null && right != null) {
            if(left.val < right.val) {
                MySingleLinkNode temp = left;
                left = left.next;
                dummy.next = temp;
                dummy = dummy.next;
            } else {
                MySingleLinkNode temp = right;
                right = right.next;
                dummy.next = temp;
                dummy = dummy.next;
            }
        }
        if(left != null) {
            dummy.next = left;
        } else {
            dummy.next = right;
        }
        return node.next;
    }

    //快慢指针寻找 中间结点
    public static MySingleLinkNode findMid(MySingleLinkNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        MySingleLinkNode slow = head;
        MySingleLinkNode fast = head;
        //1 2 3 4 ==> 2
        //1 2 3 ==> 2
        //1 2 3 4 5 ==> 3
        //1 2 3 4 5 6 ==> 3 ==> 还是3
        //1 2 3 4 5 6 7==> 4 ==> 还是4
        //1 2 3 4 5 6 7 8==> 4 ==> 还是4
        //偶数 ==》 找到的是 total / 2
        //奇数 ==》 找到的是 total / 2 + 1
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    //合并两个有序数组

}
