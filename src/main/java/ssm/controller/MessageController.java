package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.mapper.UserMapper;
import ssm.model.Message;
import ssm.model.MessageShow;
import ssm.model.User;
import ssm.service.MessageService;
import ssm.service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/message")
public class MessageController extends BaseController<Message>{
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "getmessages", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map getmessages() {
        List<Message> ls=messageService.list();
        if (ls==null)
            return messageService.errorRespMap(respMap,"查询失败");
        else return messageService.successRespMap(respMap,"查询成功",ls);

    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map save(@Param("tel") String tel,@Param("description") String description) {
        User user = userService.getUserByPhoneNum(tel);
        Message message=new Message();
        message.setSid(user.getId());
        message.setCreate_time(refFormatNowDate());
        message.setDescription(description);
        messageService.save(message);
        List<Message> ls=messageService.list();
        if (ls == null)
            return messageService.errorRespMap(respMap, "失败");
        else {


            return messageService.successRespMap(respMap, "发布成功", ls);
        }


    }

    @ResponseBody
    @RequestMapping(value = "messageshow", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map messageshow() {
        List<Message> ls=messageService.list();
        List<MessageShow> list=new ArrayList<MessageShow>();
        User user=new User();
        for (int i=0;i<ls.size();i++){
            MessageShow messageShow=new MessageShow();
            user=userService.get(ls.get(i).getSid());
            messageShow.setId(ls.get(i).getId());
            messageShow.setHeadimg(user.getHeadimg());
            messageShow.setName(user.getName());
            messageShow.setDescription(user.getDescription());
            messageShow.setMessage(ls.get(i).getDescription());
            messageShow.setTime(ls.get(i).getCreate_time());
            list.add(messageShow);
        }
        if (list==null)
            return messageService.errorRespMap(respMap,"查询失败");
        else return messageService.successRespMap(respMap,"查询成功",list);

    }

    @ResponseBody
    @RequestMapping(value = "deletetmessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map deletetmessage(@Param("id") int id) {
        Message message=messageService.get(id);

        if (message == null) return messageService.errorRespMap(respMap, "无此留言");

        messageService.delete(id);
        Message message1=messageService.get(id);
        if (message1==null)
        {
            String rt="success";

            return messageService.successRespMap(respMap, "删除成功",rt);
        }
    else return messageService.errorRespMap(respMap, "删除失败");

    }


    public String refFormatNowDate() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String retStrFormatNowDate = sdFormatter.format(nowTime);

        return retStrFormatNowDate;
    }
}
