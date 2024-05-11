package cn.edu.ncu.talkpulse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                //下面的是指可以对应resources文件下那些内容
                .addResourceLocations("classpath:/")
                .addResourceLocations("classpath:/templates/")
                .addResourceLocations("classpath:/static/");
    }

}