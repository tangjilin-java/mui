package ab.tjl.mui.request;

import javax.validation.constraints.NotBlank;

/**
 * @Author:TangJiLin
 * @Description:登录数据处理
 * @Date: Created in 2020/2/6 21:52
 * @Modified By:
 */
public class LoginReq {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
