package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Teacher;
import ssm.service.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController<Teacher>{
    @Autowired
    TeacherService teacherService;


    @ResponseBody
    @RequestMapping(value = "findTeacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map findteacherbyname(@Param("id") int id){
        Teacher teacher=teacherService.get(id);

        if(teacher==null){
            return teacherService.errorRespMap(respMap,"error");
        }else {
            return teacherService.successRespMap(respMap,"success",teacher);
        }
    }

    @ResponseBody
    @RequestMapping(value = "findTeacherByName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map findteacherbyname(@Param("name") String name){
        Teacher teacher=teacherService.findteacherbyname(name);

        if(teacher==null){
            return teacherService.errorRespMap(respMap,"error");
        }else {
            return teacherService.successRespMap(respMap,"success",teacher);
        }
    }

    @ResponseBody
    @RequestMapping(value = "teacherlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map teacherlist(){
        List<Teacher> list=teacherService.list();

        if(list==null){
            return teacherService.errorRespMap(respMap,"error");
        }else {
            return teacherService.successRespMap(respMap,"success",list);
        }
    }

    @ResponseBody
    @RequestMapping(value = "getTeacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getTeacher(@Param("name") String name){
        Teacher teacher=teacherService.findteacherbyname(name);


        if(teacher==null){
            return teacherService.errorRespMap(respMap,"老师不存在");
        }else {
            List<Teacher> list=new ArrayList<Teacher>();
            list.add(teacher);
            return teacherService.successRespMap(respMap,"success",list);
        }
    }

    @ResponseBody
    @RequestMapping(value = "deleteteacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map deleteteacher(@Param("id") int id){
        Teacher teacher=teacherService.get(id);

        if(teacher==null){
            return teacherService.errorRespMap(respMap,"error");
        }
        teacherService.delete(id);
        Teacher teacher1=teacherService.get(id);
        if (teacher1==null)
        {
            String rt="成功了";
            return teacherService.successRespMap(respMap,"删除成功",rt);
        }
        else  return teacherService.errorRespMap(respMap,"删除失败");
    }

    @ResponseBody
    @RequestMapping(value = "udteacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map udteacher(@Param("id") int id,@Param("name") String name,@Param("email") String email,@Param("school") String school,@Param("sex") String sex,@Param("description") String description){
        Teacher teacher=teacherService.get(id);

        if(teacher==null){
            return teacherService.errorRespMap(respMap,"error");
        }
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setSchool(school);
        teacher.setSex(sex);
        teacher.setDescription(description);
        teacherService.update(teacher);
            return teacherService.successRespMap(respMap,"修改成功",teacher);

    }

    @ResponseBody
    @RequestMapping(value = "newteacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map udteacher(@Param("name") String name,@Param("email") String email,@Param("school") String school,@Param("sex") String sex,@Param("description") String description){
        Teacher teacher=new Teacher();
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setSchool(school);
        teacher.setSex(sex);
        teacher.setDescription(description);
        int i=(int)(Math.random()*30);
        String imgsrc=" file/download?filename="+i+".jpg&type=2";
        teacher.setPictureurl(imgsrc);
        teacherService.save(teacher);
        if(teacher==null){
            return teacherService.errorRespMap(respMap,"error");
        }


        return teacherService.successRespMap(respMap,"添加成功",teacher);

    }




}

