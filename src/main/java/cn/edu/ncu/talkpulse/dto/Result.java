package cn.edu.ncu.talkpulse.dto;

public class Result {
    private Integer status; // 响应状态码（200/201）
    private String message; //响应信息
    private Object data; // 返回数据(对象）

    // 请求成功（无返回数据）
    public static  Result success() {
        return new Result(200,"success",null);
    }

    // 请求成功（有返回数据）
    public static  Result success(Object object) {
        return new Result(200,"success",object);
    }

    // 返回成功信息（有返回数据）
    public static  Result success(String msg,Object object) {
        return new Result(200,msg,object);
    }

    // 请求失败，返回错误信息
    public static  Result fail() {
        return new Result(201,"fail",null);
    }

    public static  Result fail(String msg) {
        return new Result(201,msg,null);
    }

    public Result(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
