package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.ExerciseMapper;
import ssm.model.Exercise;
import ssm.service.ExerciseService;

import java.util.List;




@Service
public class ExerciseServiceImpl extends BaseServiceImpl<Exercise> implements ExerciseService{

    @Autowired
    ExerciseMapper exerciseMapper;

    public void save(Exercise exercise){exerciseMapper.save(exercise);}

    public void update(Exercise exercise){exerciseMapper.update(exercise);}


    public void delete(int id) {exerciseMapper.delete(id);}


    public Exercise get(int id) {return exerciseMapper.get(id);}

    public List<Exercise> list(){return exerciseMapper.list();}

    public List<Exercise> findbylecturename( String name){return exerciseMapper.findbylecturename(name);}

    public Exercise getexercise(String lecturename,String name){return  exerciseMapper.getexercise(lecturename,name);}



}
