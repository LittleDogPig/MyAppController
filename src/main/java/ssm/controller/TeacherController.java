package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Teacher;
import ssm.service.TeacherService;

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

}

