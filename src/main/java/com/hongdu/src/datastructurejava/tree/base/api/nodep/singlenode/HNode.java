package com.hongdu.src.datastructurejava.tree.base.api.nodep.singlenode;

import java.util.Objects;

/**
 * 完整 Node action api
 * 对象设计
 * 结点组成域 = [地址 + 值 + 下一个结点地址]
 * private ===========================================>私有 不提供外面访问 进行了封装
 */
public class HNode {
    private HNode hNode;//下一个结点
    private Object obj;

    /**
     * 所谓的门面模式
     */
    public HNode() {
        this(null);
    }

    public HNode(Object obj) {
        this(obj,null);//再走一个门面模式
//        this.obj = obj;
//        this.hNode = null;
    }

    public HNode(Object obj, HNode hNode) {
        this.obj = obj;
        this.hNode = hNode;
    }

    /**-- getter setter 方法 --**/

    public HNode gethNode() {
        return hNode;
    }

    public Object getObj() {
        return obj;
    }
    /** -- 设置下一个结点 ==》 也就是新增 -- **/
    public void sethNode(HNode hNode) {
        this.hNode = hNode;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HNode hNode1 = (HNode) o;
        return Objects.equals(hNode, hNode1.hNode) &&
                Objects.equals(obj, hNode1.obj);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hNode, obj);
    }
}
