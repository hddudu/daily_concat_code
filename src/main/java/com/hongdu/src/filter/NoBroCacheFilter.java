package com.hongdu.src.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 不进行浏览器缓存的过滤器
 * ① meta是HTML语言head区的一个辅助性标签。meta标签有两个属性：http-equiv属性和name属性。
 * （1）name属性
 *          用于描述网页，与之对应的属性值为content。content中的内容主要是用于搜索引擎机器人查找信息和分类信息。语法如下：
 * <meta name="参数" content="具体的参数值">
 * ② http-equiv属性
 * （2）http-equiv属性
 * http-equiv相当于http的文件头作用，可以向浏览器传回一些有用信息，以帮助正确和精确地显示网页内容，与之对应的属性值为content。content中的内容是各个参数的变量值。语法如下：
 * <meta http-equiv="参数" content="参数变量值">
 * <meta http-equiv="参数" content="参数变量值">
 */
public class NoBroCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 责任链模式进行过滤 : 一个请求过来 ： ==》 经过过滤器 ==》 经过拦截器 ==》 经过分发器 ==》 找到核心控制器
     *                  ==》 进行数据 与 视图 模型的封装后 ==》 返回路径(资源视图)
     *                  ==》 经过拦截器(拦截返回的结果 并添加额外的处理) ==》 经过过滤器(过滤 返回的结果) ==》 响应到UI页面
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     * https://www.cnblogs.com/zkn11199/p/5600411.html
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //HTTP消息头，控制网页的缓存
        response.setHeader("Cache-Control","no-cache");//Cache-control的作用根据浏览方法的不同可以分为以下几种情况。
        /**
         * （1）以打开新窗口的方式进行浏览
         * （2）在地址栏中单击回车进行浏览
         * （3）按后退按扭进行浏览
         * （4）按刷新按扭
         */
        response.setHeader("Pragma", "no-cache");//禁止浏览器从本地计算机的缓存中访问页面内容
        response.setDateHeader("Expires", -1);//设定网页的到期时间，一旦网页过期，必须到服务器上重新传输

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
    /**
     <filter>
     <!—servlet过滤器的名称 -->
     <filter-name>BrowserNoCacheFilter</filter-name>
     <!—servlet过滤器的包所在类名称-->
     <filter-class>com.pkh.BrowserNoCacheFilter</filter-class>
     <init-param>
     <param-name>Cache-control</param-name>
     <param-value>no-cache</param-value>
     </init-param>
     <init-param>
     <param-name>Pragma</param-name>
     <param-value>no-cache</param-value>
     </init-param>
     <init-param>
     <param-name>Expires</param-name>
     <param-value>-1</param-value>
     </init-param>
     </filter>
     <filter-mapping>
//     要映射的servlet过滤器名称
     <filter-name>BrowserNoCacheFilter </filter-name>
//     要映射的servlet过滤器映射的范围
     <url-pattern>/ *</url-pattern>
     <dispatcher>REQUEST</dispatcher>
     <dispatcher>FORWARD</dispatcher>
     </filter-mapping>

     */
}
