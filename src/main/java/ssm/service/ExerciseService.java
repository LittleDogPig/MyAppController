package ssm.service;

import ssm.model.Exercise;

import java.util.List;

public interface ExerciseService extends BaseService<Exercise>{
    public void save(Exercise exercise);

    public void update(Exercise exercise);


    public void delete(int id) ;


    public Exercise get(int id) ;

    public List<Exercise> list();

    public List<Exercise> findbylecturename( String name);

    public Exercise getexercise( String lecturename,String name);

    public Exercise findexercise(String name);






}
