package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Lecture;
import ssm.model.LectureShow;
import ssm.model.Lecturecs;
import ssm.model.Lectureson;
import ssm.service.LecturesonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller

@RequestMapping("/lectureson")
public class LecturesonController extends BaseController<Lectureson>{
    @Autowired
    LecturesonService lecturesonService;

    @ResponseBody
    @RequestMapping(value = "getlectureson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getLectureson(@Param("name") String name) {
        List<Lectureson> ls = lecturesonService.findsonbyname(name);

        if (ls == null) {
            return lecturesonService.errorRespMap(respMap, "error");
        } else {

                return lecturesonService.successRespMap(respMap, "success",ls);

        }
    }

    @ResponseBody
    @RequestMapping(value = "getoneson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getoneson(@Param("id") int id) {
        Lectureson lectureson=lecturesonService.get(id);

        if (lectureson == null) {
            return lecturesonService.errorRespMap(respMap, "error");
        } else {

            return lecturesonService.successRespMap(respMap, "success",lectureson);

        }
    }

}
