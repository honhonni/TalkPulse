package cn.edu.ncu.talkpulse.interceptor;

import cn.edu.ncu.talkpulse.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 */
@Slf4j
@RestControllerAdvice// @RestControllerAdvice用于标识当前类为REST风格对应的异常处理器
public class WebExceptionHandler {

    /**
     * 处理运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        log.error(e.toString(), e);
        return Result.error("服务器异常，请联系管理员");
    }
}
