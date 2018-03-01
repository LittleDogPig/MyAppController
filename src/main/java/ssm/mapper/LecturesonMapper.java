package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.Lecturecs;
import ssm.model.Lectureson;

import java.util.List;

public interface LecturesonMapper extends BaseMapper<Lectureson>{

    public void save(Lectureson lectureson);

    public void updata(Lectureson lectureson);

    public void delete(int id );

    public Lectureson get(int id);

    public List<Lectureson> list();

    public List<Lectureson> findsonbyname(@Param("name") String name);

}
