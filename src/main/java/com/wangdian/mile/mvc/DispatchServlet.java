package com.wangdian.mile.mvc;

import com.wangdian.mile.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by walter on 3/20/2017.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(DispatchServlet.class);

    private HandlerMapping handlerMapping = SingletonFactory.getHandlerMapping();
    private HandlerExecutor handlerExecutor = SingletonFactory.getHandlerExecutor();
    private HandlerExceptionResolver handlerExceptionResolver = SingletonFactory.getHandlerExceptionResolver();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化相关配置
        ServletContext servletContext = config.getServletContext();
        UploadHelper.init(servletContext);
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码方式
        request.setCharacterEncoding("UTF-8");
        // 获取当前请求相关数据
        String currentRequestMethod = request.getMethod();
        String currentRequestPath = WebUtil.getRequestPath(request);

        // 将“/”请求重定向到首页
        if (currentRequestPath.equals("/")) {
            WebUtil.redirectRequest("index.html", request, response);
            return;
        }
        // 去掉当前请求路径末尾的“/”
        if (currentRequestPath.endsWith("/")) {
            currentRequestPath = currentRequestPath.substring(0, currentRequestPath.length() - 1);
        }
        // 获取 Handler
        Handler handler = handlerMapping.getHandler(currentRequestMethod, currentRequestPath);
        // 若未找到 Action，则跳转到 404 页面
        if (handler == null) {
            WebUtil.sendError(HttpServletResponse.SC_NOT_FOUND, "", response);
            return;
        }

        // 初始化该线程的上下文数据
        ApplicationDataContext.init(request, response);
        try {
            // 调用 Handler
            handlerExecutor.invokeHandler(request, response, handler);
        } catch (Exception e) {
            handlerExceptionResolver.resolveHandlerException(request, response, e);
        } finally {
            ApplicationDataContext.destroy();
        }
    }
}