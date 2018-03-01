package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.Ppt;

import java.util.List;

public interface PptMapper extends BaseMapper<Ppt>{

    public void save(Ppt ppt);

    public void update(Ppt ppt);

    public void delete(int id) ;

    public Ppt get(int id);

    public List<Ppt> list();

    public Ppt findbylname(@Param("name") String name);


}
