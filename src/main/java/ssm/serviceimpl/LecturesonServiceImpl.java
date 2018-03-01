package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.LecturecsMapper;
import ssm.mapper.LecturesonMapper;
import ssm.model.Lecturecs;
import ssm.model.Lectureson;
import ssm.service.LecturecsService;
import ssm.service.LecturesonService;

import java.util.List;



    @Service
    public class LecturesonServiceImpl extends BaseServiceImpl<Lectureson> implements LecturesonService {

        @Autowired
        LecturesonMapper lecturesonMapper;

        public void save(Lectureson lectureson){lecturesonMapper.save(lectureson);}

        public void updata(Lectureson lectureson){lecturesonMapper.updata(lectureson);}

        public void delete(int id ){lecturesonMapper.delete(id);}

        public Lectureson get(int id){return lecturesonMapper.get(id);}

        public List<Lectureson> list(){return lecturesonMapper.list();}

        public List<Lectureson> findsonbyname(String name){return lecturesonMapper.findsonbyname(name);}


    }



