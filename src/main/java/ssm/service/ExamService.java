package ssm.service;

import org.apache.ibatis.annotations.Param;
import ssm.model.Exam;

import java.util.List;

public interface ExamService extends BaseService<Exam>{

    public void save(Exam exam);

    public void update(Exam exam);

    public void delete(int id) ;

    public Exam get(int id);

    public List<Exam> list();

    public  Exam findbylname(String name);



}
