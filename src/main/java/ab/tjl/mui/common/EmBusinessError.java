package ab.tjl.mui.common;

/**
 * @Author:TangJiLin
 * @Description:异常信息枚举类
 * @Date: Created in 2020/4/10 10:52
 * @Modified By:
 */
public enum  EmBusinessError {
    //通用的错误类型10000开头
    NO_OBJECT_FOUND(10001,"请求对象不存在"),
    UNKNOWN_ERROR(10002,"未知错误"),
    NO_HANDLER_FOUND(10003,"找不到执行的路径操作"),
    BIND_EXCEPTION_ERROR(10004,"请求参数错误"),
    PARAMETER_VALIDATION_ERROR(10005,"请求参数校验失败"),

    //用户服务相关的错误类型20000开头
    REGISTER_DUP_FAIL(20001,"用户已存在"),

    //用户名重复
    USERNAME_MORE(20003,"用户名重复"),
    LOGIN_FAIL(20002,"手机号或密码错误");
    ;
    //错误码
    private Integer errorCode;
    //错误信息
    private String errorMsg;

    EmBusinessError(Integer errorCode,String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
