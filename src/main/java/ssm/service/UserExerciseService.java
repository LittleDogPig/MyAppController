package ssm.service;

import ssm.model.UserExercise;

import java.util.List;

public interface UserExerciseService extends BaseService<UserExercise>{
    public void save(UserExercise userexercise);

    public void update(UserExercise userexercise);


    public void delete(int id) ;


    public UserExercise get(int id) ;

    public List<UserExercise> list();

    public List<UserExercise> findbytel(String tel);

    public List<UserExercise> findrecords(String tel,String name);

    public UserExercise getrecord(String tel, String name, String lecturename);

}
