package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.PptMapper;
import ssm.model.Ppt;
import ssm.service.PptService;

import java.util.List;

@Service
public class PptServiceImpl extends BaseServiceImpl<Ppt> implements PptService{

    @Autowired
    PptMapper pptMapper;

    public void save(Ppt ppt){pptMapper.save(ppt);}

    public void update(Ppt ppt){pptMapper.update(ppt);}

    public void delete(int id) {pptMapper.delete(id);}

    public Ppt get(int id){return pptMapper.get(id);}

    public List<Ppt> list(){return pptMapper.list();}

    public Ppt findbylname(String name){return pptMapper.findbylname(name);}



}
