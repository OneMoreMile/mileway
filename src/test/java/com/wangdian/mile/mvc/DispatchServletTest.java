package com.wangdian.mile.mvc;

import com.wangdian.mile.utils.WebUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.mockito.PowerMockito;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.times;

/**
 * Created by bigv on 3/30/2017.
 */
public class DispatchServletTest {

    private HandlerMapping handlerMapping;
    private HandlerExecutor handlerExecutor;
    private HandlerExceptionResolver handlerExceptionResolver;

    @Before
    public void setUp() throws Exception {
        handlerMapping = Mockito.mock(HandlerMapping.class);
        handlerExecutor = Mockito.mock(HandlerExecutor.class);
        handlerExceptionResolver = Mockito.mock(HandlerExceptionResolver.class);
    }

    @After
    public void tearDown() throws Exception {
        handlerMapping = null;
        handlerExecutor = null;
        handlerExceptionResolver = null;
    }

//    @Test
//    public void testService_with_slash_path() throws Exception {
//        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
//
//        PowerMock.mockStatic(WebUtil.class);
//        PowerMockito.doReturn("/").when(WebUtil.class, "getRequestPath");
//
//        //接下来WebUtil.redirectRequest会被执行一次.
//
//        DispatchServlet spy = PowerMockito.spy(new DispatchServlet());
//        ServletConfig servletConfig = Mockito.mock(ServletConfig.class);
//        spy.init(servletConfig);
//
//        spy.service(request, response);
//
//        //PowerMockito.verifyStatic(spy, times(1)).invoke("redirectRequest");
//    }
//
//    @Test
//    public void testService_with_slash_ended_path() throws Exception {
//        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
//
//        PowerMock.mockStatic(WebUtil.class);
//        PowerMockito.doReturn("list/").when(WebUtil.class, "getRequestPath");
//
//        //接下来WebUtil.redirectRequest会被执行一次.
//
//        DispatchServlet spy = PowerMockito.spy(new DispatchServlet());
//        ServletConfig servletConfig = Mockito.mock(ServletConfig.class);
//        spy.init(servletConfig);
//
//        spy.service(request, response);
//    }
}