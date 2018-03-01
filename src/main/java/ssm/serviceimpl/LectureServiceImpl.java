package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.LectureMapper;
import ssm.model.Lecture;
import ssm.model.User;
import ssm.service.LectureService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LectureServiceImpl extends BaseServiceImpl<Lecture> implements LectureService{
    @Autowired
    LectureMapper lectureMapper;

    public void save(Lecture lecture){
        lectureMapper.save(lecture);
    }

    public void update(Lecture lecture){
        lectureMapper.update(lecture);
    }


    public void delete(int id){
        lectureMapper.delete(id);
    }


    public Lecture get(int id){
      return   lectureMapper.get(id);
    }

    public List<Lecture> list(){
       return lectureMapper.list();
    }

    public List<Lecture> getLectureLikeName(String name){
        return lectureMapper.getLectureLikeName(name);
    }

    public Lecture findLectureByName(String name){
        return lectureMapper.findLectureByName(name);
    }

    public List<Lecture> getLectureByType(String type){
        return lectureMapper.getLectureByType(type);
    }

    public List<Lecture> getLectureByTeacher(String name){
        return lectureMapper.getLectureByTeacher(name);
    }

    public String findLectureById(int id) { return lectureMapper.findLectureById(id); }

    public String findNameByName(String name){return lectureMapper.findNameByName(name);}

    @Override
    public Map<String, Object> errorRespMap(Map<String, Object> map, String message) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        map.put("error_code", "-1");
        map.put("message", message);
        map.put("data", new Lecture());
        return map;
    }

}
