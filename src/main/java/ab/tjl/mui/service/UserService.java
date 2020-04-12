package ab.tjl.mui.service;

import ab.tjl.mui.common.BusinessException;
import ab.tjl.mui.common.EmBusinessError;
import ab.tjl.mui.mapper.UserMapper;
import ab.tjl.mui.model.User;
import ab.tjl.mui.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * @Author:TangJiLin
 * @Description:用户业务层
 * @Date: Created in 2020/4/8 15:13
 * @Modified By:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> findAll(){
        return userMapper.selectAll();
    }

    public User findUserByUsername(String username){
        return   userMapper.selectByUsername(username);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = userMapper.selectByUsernameAndPassword(username,encodeByMd5(password));

        if (user == null){
            System.out.println("账户或者密码错误");
        }
        return user;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Transactional
    public User register(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        getRegisterInfo(user, user.getUsername(), user.getRealname(), encodeByMd5(user.getPassword()), user.getSex(), user.getAge(), user.getHometown(), user.getHobby());
        List<User> userList = findAll();
        for (User user1 : userList) {
            if ( user1.getUsername().equals(user.getUsername())){
                throw new BusinessException(EmBusinessError.USERNAME_MORE);//用户名重复
            }

        }

        try {
            userMapper.insertSelective(user);
        }catch (DuplicateKeyException e){
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);//用户存在异常
        }
        return findUserByUsername(user.getUsername());
    }

    /**
     * //确认计算方法Md5
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));
    }
    public static void getRegisterInfo(User user, String username, String realname, String password, String sex, Integer age, String hometown, String hobby) {
        user.setUsername(username);
        user.setRealname(realname);
        user.setPassword(password);
        user.setSex(sex);
        user.setAge(age);
        user.setHometown(hometown);
        user.setHobby(hobby);
    }


}
