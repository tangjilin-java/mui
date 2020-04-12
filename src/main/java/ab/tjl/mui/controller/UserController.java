package ab.tjl.mui.controller;

import ab.tjl.mui.common.BusinessException;
import ab.tjl.mui.common.CommonRes;
import ab.tjl.mui.common.CommonUtil;
import ab.tjl.mui.common.EmBusinessError;
import ab.tjl.mui.model.User;
import ab.tjl.mui.request.LoginReq;
import ab.tjl.mui.request.RegisterReq;
import ab.tjl.mui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Author:TangJiLin
 * @Description:
 * @Date: Created in 2020/4/8 15:19
 * @Modified By:
 */
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UserService userService;

    @RequestMapping("/findUserById")
    @ResponseBody
    public User findUserById(@RequestParam(name = "id") Integer id){
        return userService.findUserById(id);
    }

    @RequestMapping("/findAllUser")
    @ResponseBody
    public List<User> findAllUser(){
        return userService.findAll();
    }


    @PostMapping("/login")
    @ResponseBody
    public CommonRes login(@Valid @RequestBody LoginReq loginReq,BindingResult bindingResult
                           ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        User user = userService.login(loginReq.getUsername(), loginReq.getPassword());
        if (user == null){
            return null;
        }
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,user);
        return CommonRes.create(user);
    }

    /**
     * 注册
     * @param registerReq
     * @param bindingResult
     * @return
     * @throws BusinessException
     */
    @PostMapping("/register")
    @ResponseBody
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()){

            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        User user = new User();
        UserService.getRegisterInfo(user, registerReq.getUsername(), registerReq.getRealname(), registerReq.getPassword(), registerReq.getSex(), registerReq.getAge(), registerReq.getHometown(), registerReq.getHobby());

        User register = userService.register(user);

        return CommonRes.create(register);
    }


    /**
     * 用户登出
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping("/logout")
    @ResponseBody
    public CommonRes logout() throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }
}
