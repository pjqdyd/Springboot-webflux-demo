package com.pjqdyd;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**   
 * @Description:  [异步Servlet]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/asyncServlet"})
public class AsyncServletDemo extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("初始Servlet成功");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        //开启异步
        AsyncContext asyncContext = req.startAsync();

        //使用CompletableFuture来执行异步代码
        CompletableFuture.runAsync(()->{
            doSomeThing(asyncContext, asyncContext.getRequest(), asyncContext.getResponse());
        });

        long endTime = System.currentTimeMillis();
        System.out.println("耗时:" + (endTime-startTime));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    //执行的耗时业务方法
    public void doSomeThing(AsyncContext asyncContext, ServletRequest req, ServletResponse resp) {
        try {//模拟耗时操作
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {// 设置:响应内容类型
            resp.getWriter().append("Test Async Servlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //通知异步操作完成
        asyncContext.complete();
    }

    @Override
    public void destroy() {
        System.out.println("销毁Servlet成功");
    }

}
