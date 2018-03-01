package ssm.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Exercise;
import ssm.service.ExerciseService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exercise")
public class ExerciseContrioller extends BaseController<Exercise>{
    @Autowired
    ExerciseService exerciseService;

    @ResponseBody
    @RequestMapping(value = "getexercises", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getexercises(@Param("name") String name) {
        List<Exercise> exerciseList=exerciseService.findbylecturename(name);
        if (exerciseList==null)
            return exerciseService.errorRespMap(respMap,"查询失败");
        else return exerciseService.successRespMap(respMap,"查询成功",exerciseList);

    }

    @ResponseBody
    @RequestMapping(value = "getexercise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getexercise(@Param("lecturename") String lecturename,@Param("name") String name) {
      Exercise exercise=exerciseService.getexercise(lecturename,name);

      if (exercise==null)
          return exerciseService.errorRespMap(respMap,"error");
      else return exerciseService.successRespMap(respMap,"success",exercise);

    }





}
