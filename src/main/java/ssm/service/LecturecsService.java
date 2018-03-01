package ssm.service;

import org.apache.ibatis.annotations.Param;
import ssm.model.Lecturecs;

import java.util.List;

public interface LecturecsService extends BaseService<Lecturecs>{

    public void save(Lecturecs lecturecs);

    public void update(Lecturecs lecturecs);

    public void delete(int id );

    public Lecturecs get(int id);

    public List<Lecturecs> list();

    public List<Lecturecs> finduserchose(String tel);

    public Lecturecs findrecord(String tel, String name);

    public List<Lecturecs> finishlecture(String tel);

    public List<Lecturecs> unfinishlecture(String tel);


}
