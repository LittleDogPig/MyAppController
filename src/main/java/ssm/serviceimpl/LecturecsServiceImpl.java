package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.LecturecsMapper;
import ssm.model.Lecturecs;
import ssm.service.LecturecsService;

import java.util.List;


@Service
public class LecturecsServiceImpl extends BaseServiceImpl<Lecturecs> implements LecturecsService{

    @Autowired
    LecturecsMapper lecturecsMapper;

    public void save(Lecturecs lecturecs){lecturecsMapper.save(lecturecs);}

    public void update(Lecturecs lecturecs){lecturecsMapper.update(lecturecs);}

    public void delete(int id ){lecturecsMapper.delete(id);}

    public Lecturecs get(int id){return  lecturecsMapper.get(id);}

    public List<Lecturecs> list(){return lecturecsMapper.list();}

    public List<Lecturecs> finduserchose(String tel){return lecturecsMapper.finduserchose(tel);}

    public Lecturecs findrecord(String tel, String name){return lecturecsMapper.findrecord(tel,name);}

    public List<Lecturecs> finishlecture(String tel){return lecturecsMapper.finishlecture(tel);}

    public List<Lecturecs> unfinishlecture(String tel){return lecturecsMapper.unfinishlecture(tel);}

}
