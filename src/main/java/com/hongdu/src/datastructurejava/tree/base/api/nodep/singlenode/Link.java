package com.hongdu.src.datastructurejava.tree.base.api.nodep.singlenode;

/**
 * 编写对外接口API
 * 方便扩展用到泛型，实际调用的时候记得重写第三方类的equals(),toString()2个方法
 * @param <E>
 */
public class Link<E> {
    private HNode headNode;//HNode 中已经使用到了 Object
    private int size = 0;

    private final static String SPLITE_STRING = "-";

    public HNode getHeadNode() {
        return headNode;
    }
    public int getSize() {
        return size;
    }
    //判空
    public boolean isEmpty() {
        return size == 0;
    }
    /** api **/
    //在末位增加结点
    public void addHNode(E ele) {
        if(headNode == null) {
            headNode = new HNode(ele);//新new堆空间 存储对象数据 ==> 二进制值
        } else {
            headNode = new HNode(ele,headNode);
        }
        size++;
    }

    //在确定位置增加结点
    public void addHNodeIndex(E ele, int index) {
        if(index == 0) {//从头部插入 如果从0位置插入，即从头部插入
            addHNode(ele);
        } else {
            if(index > size) {
                throw new IllegalArgumentException("插入位置超过链表长度!");
            } else {
                int count = 0;
                for( HNode nd = headNode; nd != null; nd = nd.gethNode()) {
                    count++;
                    if(count == index) {
                        HNode nextHNode = new HNode(ele,nd.gethNode());//新建结点 ： 指向原位置结点的下一个结点; 原位置结点
                        nd.sethNode(nextHNode);
                        size++;
                    }
                }
            }
        }

    }
    //删除确定位置索引
    public void deleteHNodeIndex (int index){
        checkIndex(index);
        if(index >= size) {
            throw new IllegalArgumentException("要删除的索引超过链表长度");
        } else {
            if(index == 0) {
                headNode = headNode.gethNode();//移动头指针
                size--;
            } else {
                int count = 0;
                HNode nd = headNode;
                while(++count < index) {
                    nd = nd.gethNode();
                }
                if(index == size - 1) {
                    nd.sethNode(null);
                } else {
                    nd.sethNode(nd.gethNode().gethNode());
                }
            }
        }
    }

    //删除确定位置《《《《====== leetcode && lintcode

    /**
     * 删除匹配元素的结点
     * toend控制只删除第一匹配项 还是全部匹配项
     * @param ele
     * @param toEnd
     */
    public void deleteNodeElement(E ele, boolean toEnd) {
        int count = 0;
        for(HNode nd = headNode; nd != null; nd = nd.gethNode(),count++) {
            if(ele.equals(nd.getObj())) {
                deleteHNodeIndex(count);
                if(toEnd)continue;//继续删除
                else break;//只删除第一个匹配的位置
            }
        }
    }

    //删除所有结点
    public void deleteAll() {
        headNode = null;
        size = 0;
    }
    /**--查找接口api ①边界 ②合法性检查 ③执行逻辑 --**/
    public HNode searchHNodeIndex(int index) {
        checkIndex(index);
        int count = 0;
        if(index == 0) {
            return headNode;
        }
        HNode nd = headNode;
        while (++count < index) {
            nd = nd.gethNode();
        }
        return nd;
    }

    public void checkIndex(int index) {
        if(index >= size) {
            throw new IllegalArgumentException("查询索引超过链表长度");
        }
    }

    //查找相等元素的第一个节点
    public HNode searchHNodeEle (E ele) {
        int count = 0;
        for(HNode nd = headNode; nd != null; nd = nd.gethNode(),count++) {
            if(ele.equals(nd.getObj())) {
                return searchHNodeIndex(count);
            }
        }
        return null;
    }
    //得到包含该元素的结点集合
    public HNode[] searchHNodeElement(E ele, HNode[] hNodes) {
        try{
            int count = 0, index = 0;
            for(HNode nd = headNode; nd != null; nd = nd.gethNode(), count++) {
                if(ele.equals(nd.getObj())){
                    hNodes[index++] = searchHNodeIndex(count);
                }
            }
        } catch (Exception e) {
            hNodes = null;
            e.printStackTrace();
        } finally {
            return hNodes;
        }
    }

    //修改索引 index 的值 为 newEle
    public boolean modifyHNodeIndex(int index, E ele) {
        checkIndex(index);
        int count = 0;
        HNode nd = headNode;
        while (count ++ < index) {
            nd = nd.gethNode();
        }
        nd.setObj(ele);
        return true;
    }

    /*
     * 将匹配的旧元素oldEle改成新的newEle
     * Boolean toEnd : 控制修改的范围是第一个匹配项还是全部匹配项
     */
    public boolean modifyHNodeElement(E oldEle, E newEle, boolean toEnd) {
        int count = 0;
        for(HNode nd = headNode; nd != null; nd = nd.gethNode(), count++) {
            if(oldEle.equals(nd.getObj())) {
                modifyHNodeIndex(count, newEle);
                if(!toEnd) continue;
                else break;
            }
        }
        return true;
    }

    //输出全部元素
    public String printNode() {
        HNode nd = headNode;
        StringBuilder dataString = new StringBuilder();
        while (nd != null) {
            dataString.append(nd.getObj().toString()).append(SPLITE_STRING);
            nd = nd.gethNode();
        }
        return dataString.toString();
    }

}
