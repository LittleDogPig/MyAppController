package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.MessageMapper;
import ssm.model.Message;
import ssm.service.MessageService;

import java.util.List;



@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService{
    @Autowired
    MessageMapper messageMapper;

    public void save(Message message){messageMapper.save(message);}

    public void update(Message message){messageMapper.update(message);}


    public void delete(int id){messageMapper.delete(id);}


    public Message get(int id){return messageMapper.get(id);}

    public List<Message> list(){return  messageMapper.list();}




}
