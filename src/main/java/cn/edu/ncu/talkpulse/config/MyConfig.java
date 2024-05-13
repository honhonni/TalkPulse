package cn.edu.ncu.talkpulse.config;

import cn.edu.ncu.talkpulse.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * config包存放各种配置类
 * MVC配置类
 */
@Configuration
@Slf4j
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                //下面的是指可以对应resources文件下那些内容
                .addResourceLocations("classpath:/")
                .addResourceLocations("classpath:/templates/")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * 注册自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(// 设置拦截白名单（登录注册等不需要登录验证的接口）
                        "/register/**",
                        "/login/**"
                ).order(1);//order控制执行顺序，值越小越先执行，相同则根据添加顺序
    }

}