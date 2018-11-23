package com.hongdu.src.datastructurejava.po;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * 学生PO
 */
public class StudentPO implements Comparable{

    private int score;
    private String name;

    public StudentPO(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "StudentPO{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof StudentPO) {
            StudentPO so = (StudentPO)o;
            if(so.getScore() > this.score) {
                return 1;
            } else if(so.getScore() == this.score) {
                return 0;
            } else {
                return -1;
            }
        } else {
            throw new IllegalArgumentException("不是同类对象无法比较!");
        }
//        return -2;//-2标识不是同一类对象不能比较
    }

    /**
     * 内部比较器： 比较中文汉字大小
     * 中文字符排序
     */
    public class SortChineseName implements Comparator<StudentPO> {
        Collator cmp = Collator.getInstance(Locale.CHINA);
        @Override
        public int compare(StudentPO o1, StudentPO o2) {
            if(cmp.compare(o1.getName(), o2.getName()) > 0) {
                return 1;
            } else if(cmp.compare(o1.getName(), o2.getName()) < 0) {
                return -1;
            }
            return 0;
        }
    }
}
