package ssm.service;

import org.apache.ibatis.annotations.Param;
import ssm.model.Lectureson;

import java.util.List;

public interface LecturesonService extends BaseService<Lectureson>{
    public void save(Lectureson lectureson);

    public void updata(Lectureson lectureson);

    public void delete(int id );

    public Lectureson get(int id);

    public List<Lectureson> list();

    public List<Lectureson> findsonbyname(String name);





}
