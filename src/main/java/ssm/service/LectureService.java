package ssm.service;

import ssm.model.Lecture;

import java.util.List;


public interface LectureService extends BaseService<Lecture>{

    public void save(Lecture lecture);


    public void update(Lecture lecture);


    public void delete(int id) ;


    public Lecture get(int id) ;

    public List<Lecture> list();

    public List<Lecture> getLectureLikeName(String name);

    public Lecture findLectureByName(String name);

    public List<Lecture> getLectureByType(String type);

    public List<Lecture> getLectureByTeacher(String name);

    public String findLectureById(int id);

    public String findNameByName(String name);
}
