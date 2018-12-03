package com.hongdu.src.datastructurejava.tree.base.api.nodep.singlenode;

import org.junit.Test;

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


}
