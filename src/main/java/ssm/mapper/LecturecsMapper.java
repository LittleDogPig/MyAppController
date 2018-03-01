package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.Lecturecs;

import java.util.List;

public interface LecturecsMapper extends BaseMapper<Lecturecs>{

    public void save(Lecturecs lecturecs);

    public void update(Lecturecs lecturecs);

    public void delete(int id );

    public Lecturecs get(int id);

    public List<Lecturecs> list();

    public List<Lecturecs> finduserchose(@Param("tel") String tel);

    public Lecturecs findrecord(@Param("tel") String tel,@Param("name") String name);

    public List<Lecturecs> finishlecture(@Param("tel") String tel);

    public List<Lecturecs> unfinishlecture(@Param("tel") String tel);
}
