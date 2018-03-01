package ssm.mapper;

import ssm.model.Teacher;


import java.util.List;

public interface TeacherMapper extends BaseMapper<Teacher>{

    public void save(Teacher teacher);

    public void update(Teacher teacher);

    public void delete(int id) ;

    public Teacher get(int id);

    public List<Teacher> list();

    public Teacher findteacherbyname(String name);




}
