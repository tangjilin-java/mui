package ab.tjl.mui.common;

/**
 * @Author:TangJiLin
 * @Description:业务异常处理
 * @Date: Created in 2020/4/10 10:47
 * @Modified By:
 */
public class BusinessException extends Exception {
    private CommonError commonError;


    public BusinessException(EmBusinessError emBusinessError) {
        super();
        this.commonError = new CommonError(emBusinessError);
    }

    public BusinessException(EmBusinessError emBusinessError,String errorMsg){
        super();
        this.commonError = new CommonError(emBusinessError);
        this.commonError.setErrorMsg(errorMsg);
    }

    public CommonError getCommonError() {
        return commonError;
    }

    public void setCommonError(CommonError commonError) {
        this.commonError = commonError;
    }
}
