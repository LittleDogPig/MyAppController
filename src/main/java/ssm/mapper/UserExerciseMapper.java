package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.UserExercise;

import java.util.List;

public interface UserExerciseMapper extends BaseMapper<UserExercise>{
    public void save(UserExercise userexercise);

    public void update(UserExercise userexercise);


    public void delete(int id) ;


    public UserExercise get(int id) ;

    public List<UserExercise> list();

    public List<UserExercise> findbytel(@Param("tel") String tel);

    public List<UserExercise> findrecords(@Param("tel") String tel,@Param("name") String name);//根据用户和课程名找到系列练习

    public UserExercise getrecord(@Param("tel") String tel,@Param("name") String name,@Param("lecturename") String lecturename);//根据用户和练习名和课程名找到对应练习记录


}
