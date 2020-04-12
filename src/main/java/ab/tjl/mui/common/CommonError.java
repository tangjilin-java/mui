package ab.tjl.mui.common;

/**
 * @Author:TangJiLin
 * @Description:公共异常信息
 * @Date: Created in 2020/4/10 10:48
 * @Modified By:
 */
public class CommonError {

    //错误码
    private Integer errorCode;

    //错误描述
    private String errorMsg;

    public CommonError(Integer errorCode,String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    public CommonError(EmBusinessError emBusinessError){
        this.errorCode = emBusinessError.getErrorCode();
        this.errorMsg = emBusinessError.getErrorMsg();
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
