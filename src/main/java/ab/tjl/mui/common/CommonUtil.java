package ab.tjl.mui.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @Author:TangJiLin
 * @Description:公共工具类  处理参数问题
 * @Date: Created in 2020/4/10 10:59
 * @Modified By:
 */
public class CommonUtil {
    public static String processErrorString(BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(FieldError fieldError:bindingResult.getFieldErrors()){
            stringBuilder.append(fieldError.getDefaultMessage()+",");
        }
        return stringBuilder.substring(0,stringBuilder.length()-1);
    }
}
