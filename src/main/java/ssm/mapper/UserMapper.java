package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


    void save(User user);

    public void delete(int id);

    public User get(int id);

    void update(User user);

    List<User> list();

    //根据用户名和密码查询用户
    public void findUserByNameAndPwd(@Param("name") String name, @Param("password") String password);

    public User getUserByPhone(@Param("tel") String tel, @Param("password") String password);


    public User getUserByPhoneNum(@Param("tel") String tel);



}
