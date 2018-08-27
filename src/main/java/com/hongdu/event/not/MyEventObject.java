package com.hongdu.event.not;

import java.io.Serializable;

/**
 * 事件对象
 * @author Administrator
 *
 */
public class MyEventObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected transient Object  source;
	
	public MyEventObject(Object source) {
        if (source == null)
            throw new IllegalArgumentException("null source");

        this.source = source;
    }

	public Object getSource() {
		return source;
	}
	
	public String toString() {
        return getClass().getName() + "[source=" + source + "]";
    }
}
