package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.Exam;
import ssm.model.Teacher;

import java.util.List;

    public interface ExamMapper extends BaseMapper<Exam>{

        public void save(Exam exam);

        public void update(Exam exam);

        public void delete(int id) ;

        public Exam get(int id);

        public List<Exam> list();

        public  Exam findbylname(@Param("name") String name);
}
