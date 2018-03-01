package ssm.serviceimpl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.ExamMapper;
import ssm.model.Exam;
import ssm.service.ExamService;

import java.util.List;

@Service
public class ExamServiceImpl extends BaseServiceImpl<Exam> implements ExamService{

    @Autowired
    ExamMapper examMapper;

    public void save(Exam exam){examMapper.save(exam);}

    public void update(Exam exam){examMapper.update(exam);}

    public void delete(int id){examMapper.delete(id);}

    public Exam get(int id){return  examMapper.get(id);}

    public List<Exam> list(){return examMapper.list();}

    public  Exam findbylname(String name){return  examMapper.findbylname(name);}




}
