package ssm.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.TeacherMapper;
import ssm.model.Teacher;
import ssm.service.BaseService;
import ssm.service.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService{

    @Autowired
    TeacherMapper teacherMapper;

    public void save(Teacher teacher){teacherMapper.save(teacher);}

    public void update(Teacher teacher){teacherMapper.update(teacher);}

    public void delete(int id){teacherMapper.delete(id);}

    public Teacher get(int id){return teacherMapper.get(id);}


    public List<Teacher> list(){return teacherMapper.list();}

    public Teacher findteacherbyname(String name){return  teacherMapper.findteacherbyname(name);}

}
