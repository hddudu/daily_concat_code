package com.hongdu.src.datastructurejava.tree.base.api.nodep.singlenode;

import org.junit.Test;

/**
 * lintcode 单链表
 */
public class MySingleLinkNode {
    int val;
    MySingleLinkNode next;
    MySingleLinkNode(int val) {
        this.val = val;
        this.next = null;//默认为null
    }
    /**-- 单链表api 设计 ： 增 删 改 查 ==》 插入： 头+尾 ; 删除 ： 修改 查找(根据值 + 根据位置 ) --**/
    /** -- 返回值一般是结点 -- **/

    public static MySingleLinkNode getNodeAtWz(MySingleLinkNode head,int index) {
        int len = 0;
        MySingleLinkNode ret = null;
        while (head != null) {
            len++;
            if(len == index) {
                ret = head;
                break;
            }
            head = head.next;
        }
        return ret;
    }

    /**
     * @param head 头结点
     * @param insertNode 待插入的结点
     */
    public static void insertLast(MySingleLinkNode head,MySingleLinkNode insertNode) {
        if(head == null) {
            head = insertNode;
        }
        //找到最后一个结点： 就是最后一个结点next == null
        while (head.next != null) {
            head = head.next;
        }
        head.next = insertNode;
    }

    /**
     * 遍历单链表
     */
    public static void printMySingleLinkNode(MySingleLinkNode head) {
        if(head == null) {
            System.out.println("链表为空!");
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
            if(head != null) {
               sb.append("->");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * 计算链表长度
     * @param head
     * @return
     */
    public static int countNodes(MySingleLinkNode head) {
        String s = "";
//        s.equals()
        // write your code here
        if(head == null) {
            return 0;
        }
        int count = 0;
        MySingleLinkNode temp = head;
//        System.out.println(temp.hashCode());//100403510
//        System.out.println(temp);//com.hongdu.src.datastructurejava.tree.base.api.nodep.singlenode.MySingleLinkNode@61bb121f
        while(head != null) {
//            System.out.println(head.hashCode());//100403510
//            System.out.println(head);//com.hongdu.src.datastructurejava.tree.base.api.nodep.singlenode.MySingleLinkNode@61bb121f
            head = head.next;
            count++;
            if(temp == head) {
                break;
            }
        }
        return count;
    }

    /**
     * 全部翻转链表基本api
     * @param head
     * @return
     */
    public static MySingleLinkNode reverseSingleList(MySingleLinkNode head) {
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

    /**
     * 部分翻转 单向链表 : 在单向链表上把第from 个结点到 to 个结点这一部分进行翻转
     * A->B->C->D->E->G->F->H->I->J  from 3 to 5
     *       C->D->E ==> E->D-C
     * A->B->E->D->C->G->F->H->I->J
     * 方式一: 分段链接
     * 方式二: 局部翻转
     *          求出翻转的链表 : 然后将断裂处进行拼接(链接)
     *          步骤一： 找到from-1个结点 fpre 和 第 to+1个结点 tpos
     *                  fPre就是要翻转部分的前一个结点 tpos就是翻转部分的后一个结点
     *         涉及api ： 其实就是寻找某个位置的结点:
     * @param head
     * @param from
     * @param to
     * @return
     */
    public static MySingleLinkNode reversePartSingleList(MySingleLinkNode head, int from, int to) {
        if(from < 0 || from > to || head == null) {//1位
            return head;
        }
        int len = countNodes(head);
        if(to > len ) {
            return head;
        }
        int count = 0;
        MySingleLinkNode firstPart = head;// 找到了fPre
        MySingleLinkNode threePart = head;// 找到了fPre
        while (firstPart != null && threePart != null) {
            count++;//先加加 再判断 当到from-1时 就不获取下一个了 ==> from-1的firstPart
            if( count < from - 1) {
                firstPart = firstPart.next;
                threePart = threePart.next;
            } else if(count < (to + 2)){// 寻找 tpos 结点 : to + 1
                if(threePart.next != null) {
                    threePart = threePart.next;
                }
            } else {
                break;//跳出循环
            }
        }
        //翻转 from 到 to的部分 : from = firstPart.next 结尾 ： to - from + 1
        MySingleLinkNode fromNode = firstPart.next;//head

        MySingleLinkNode pre = null;
        MySingleLinkNode next = null;
        int fzCount = 0;
        while (fzCount < (to - from + 1) && fromNode != null) {
            next = fromNode.next;
            fromNode.next = pre;//fan z
            pre = fromNode;//b c
            // 移位
            fromNode = next;
            fzCount++;
        }
        firstPart.next = pre;
        pre.next = threePart;
        return firstPart;
    }

    public static MySingleLinkNode reversePartSingleList2(MySingleLinkNode head, int from, int to) {
        int len = 0;
        MySingleLinkNode node1 = head;
        MySingleLinkNode fPre = null;
        MySingleLinkNode tPos = null;
        //求取特点位置的结点
        while (node1 != null) {
            len++;
            fPre = (len == from - 1) ? node1 : fPre;
            tPos = (len == to + 1) ? node1 : tPos;
            node1 = node1.next;
        }
        //len 也在一次循环中求出来了
        if(from > to || from < 1 || to > len) {
            return head;
        }
        node1 = fPre == null ? head : fPre.next;//判断首结点是否是头结点 A 首结点 head
        MySingleLinkNode node2 = node1.next;//head.next
        //这个是必须的操作
        node1.next = tPos;//head.next = tPos ==>断裂结点
        MySingleLinkNode next = null;
        //翻转的条件改为 从结点相比较
        while (node2 != tPos) {
            next = node2.next;
            node2.next = node1;//指向了node1
            node1 = node2;
            node2 = next;
        }
        ///还有个翻转情况是 ： 1->2->3->4 翻转的是 1->2->3的  或者 ： 2->3->4的 就是说包含首尾结点的情况
        if(fPre != null) {
            //
            fPre.next = node1;//fPre : 保存的就是当前这个结点的值 ===》而不是前面的头指针
            return head;
        }
        return node1;
    }

//    @Test
//    public void testReverseSingleList222() {
//        MySingleLinkNode n = new ListNodeSolutions().generateMySingleLinkNode();
//        MySingleLinkNode.printMySingleLinkNode(n);//打印单链表
//    }
}
