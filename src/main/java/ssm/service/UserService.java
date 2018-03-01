package ssm.service;

import ssm.model.User;

import java.util.List;
import java.util.Map;

public interface UserService extends BaseService<User> {

    List<User> list();

    User get(int id);

    //登录
    void login(String tel, String password);

    void save(User user);

    void delete(int id);

    //更新用户信息
    void update(User user);

    //根据用户手机和密码查找用户信息
    User getUserByPhone(String tel, String password);


    User getUserByPhoneNum(String phone);

    public Map<String, Object> merrorRespMap(Map<String, Object> map, String message);




}
