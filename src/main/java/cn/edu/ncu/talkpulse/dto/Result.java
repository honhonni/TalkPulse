package cn.edu.ncu.talkpulse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * dto包下存放数据传输对象（用来接受前端数据的类/返回给前端的数据类）
 * Controller层返回的响应结果类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    private Integer status; // 响应状态码（200/201）
    private String message; //响应信息
    private T data; // 返回数据（泛型对象）

    /**
     * 请求成功（无返回数据）
     * @return Result对象
     * @param <T> 对象泛型
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.status = 200;
        result.message = "请求成功";
        return result;
    }

    /**
     * 请求成功（有返回数据）
     * @param object 响应返回的数据对象
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.status = 200;
        result.message = "请求成功";
        return result;
    }

    /**
     * 请求失败
     * @param msg 错误信息
     * @return
     * @param <T>
     */
    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.message = msg;
        result.status = 201;
        return result;
    }

}
