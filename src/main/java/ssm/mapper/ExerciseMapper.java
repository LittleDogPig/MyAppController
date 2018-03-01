package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.Exercise;

import java.util.List;

public interface ExerciseMapper  extends BaseMapper<Exercise>{
    public void save(Exercise exercise);

    public void update(Exercise exercise);


    public void delete(int id) ;


    public Exercise get(int id) ;

    public List<Exercise> list();

    public List<Exercise> findbylecturename(@Param("name") String name);

    public Exercise getexercise(@Param("lecturename") String lecturename,@Param("name") String name);




}
