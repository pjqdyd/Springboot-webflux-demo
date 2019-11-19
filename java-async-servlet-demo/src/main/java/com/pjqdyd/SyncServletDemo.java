package com.pjqdyd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**   
 * @Description:  [同步Servlet]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@WebServlet("/syncServlet")
public class SyncServletDemo extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("初始Servlet成功");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long firstTime = System.currentTimeMillis();
        doSomeThing(req, resp);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:" + (endTime-firstTime));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    //执行的耗时业务方法
    public void doSomeThing(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {//模拟耗时操作
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 设置:响应内容类型
       resp.getWriter().append("Test Sync Servlet");
    }

    @Override
    public void destroy() {
        System.out.println("销毁Servlet成功");
    }
}
