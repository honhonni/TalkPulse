package cn.edu.ncu.talkpulse.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器，用作登录校验
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 请求接口前的处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 校验请求用户是否登录，若登录则放行
        if(true){
            return true;
        }

        // 否则拦截非法请求
        response.setStatus(401);
        return false;
    }


    /**
     * 后置处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}