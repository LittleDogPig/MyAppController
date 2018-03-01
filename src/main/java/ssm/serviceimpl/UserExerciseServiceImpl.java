package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.UserExerciseMapper;
import ssm.model.UserExercise;
import ssm.service.UserExerciseService;

import java.util.List;


@Service
public class UserExerciseServiceImpl extends BaseServiceImpl<UserExercise> implements UserExerciseService{
    @Autowired
    UserExerciseMapper userExerciseMapper;
    public void save(UserExercise userexercise){userExerciseMapper.save(userexercise);}

    public void update(UserExercise userexercise){userExerciseMapper.update(userexercise);}


    public void delete(int id) {userExerciseMapper.delete(id);}


    public UserExercise get(int id) {return userExerciseMapper.get(id);}

    public List<UserExercise> list(){return userExerciseMapper.list();}

    public List<UserExercise> findbytel(String tel){return userExerciseMapper.findbytel(tel);}

    public List<UserExercise> findrecords(String tel,String name){return userExerciseMapper.findrecords(tel,name);}

    public UserExercise getrecord(String tel, String name, String lecturename){return  userExerciseMapper.getrecord(tel,name,lecturename);}

}
