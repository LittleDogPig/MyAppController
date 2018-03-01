package ssm.mapper;


import ssm.model.Message;

import java.util.List;

public interface MessageMapper  extends BaseMapper<Message> {
    public void save(Message message);

    public void update(Message message);


    public void delete(int id);


    public Message get(int id);

    public List<Message> list();

}