package ssm.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Exam;
import ssm.model.Exercise;
import ssm.model.ExerciseShow;
import ssm.model.Lecture;
import ssm.service.ExerciseService;
import ssm.service.LectureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exercise")
public class ExerciseContrioller extends BaseController<Exercise>{
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    LectureService lectureService;

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

    @ResponseBody
    @RequestMapping(value = "exerciseshow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map exerciseshow() {
        List<Lecture> lectureList=lectureService.exerciseShow();
        List<ExerciseShow> list=new ArrayList<ExerciseShow>();
        for(int i=0;i<lectureList.size();i++){
            ExerciseShow exerciseShow=new ExerciseShow();
            List<Exercise> exerciseList=exerciseService.findbylecturename(lectureList.get(i).getName());
            exerciseShow.setId(lectureList.get(i).getId());
            exerciseShow.setName(lectureList.get(i).getName());
            exerciseShow.setNumber(exerciseList.size()+"门练习");
            list.add(exerciseShow);
        }

        if (list==null)
            return exerciseService.errorRespMap(respMap,"error");
        else return exerciseService.successRespMap(respMap,"success",list);

    }

    @ResponseBody
    @RequestMapping(value = "getExercise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getExercise(@Param("name") String name){
        Lecture lecture=lectureService.exercise(name);


        if(lecture==null){
            return exerciseService.errorRespMap(respMap,"练习不存在");
        }else {
            List<ExerciseShow> list=new ArrayList<ExerciseShow>();
            ExerciseShow exerciseShow=new ExerciseShow();
            List<Exercise> exerciseList=exerciseService.findbylecturename(lecture.getName());
            exerciseShow.setName(lecture.getName());
            exerciseShow.setNumber(exerciseList.size()+"门练习");
            list.add(exerciseShow);
            return exerciseService.successRespMap(respMap,"success",list);
        }
    }

    @ResponseBody
    @RequestMapping(value = "getexson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getexson(@Param("name") String name) {
        List<Exercise> list=exerciseService.findbylecturename(name);

        if (list==null)
            return exerciseService.errorRespMap(respMap,"error");
        else return exerciseService.successRespMap(respMap,"success",list);

    }

    @ResponseBody
    @RequestMapping(value = "getoneexercise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getoneexercise(@Param("id") int id) {
       Exercise exercise =exerciseService.get(id);

        if (exercise==null)
            return exerciseService.errorRespMap(respMap,"error");
        else return exerciseService.successRespMap(respMap,"success",exercise);

    }

    @ResponseBody
    @RequestMapping(value = "deleteExercise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getexson(@Param("id") int id) {
       Exercise exercise=exerciseService.get(id);


        if (exercise==null)
            return exerciseService.errorRespMap(respMap,"练习不存在");
            exerciseService.delete(id);
            Exercise exercise1=exerciseService.get(id);
        if (exercise1==null){
            String rt="success";
            return exerciseService.successRespMap(respMap,"删除成功",rt);
        }
            else return exerciseService.errorRespMap(respMap,"删除失败");

    }

    @ResponseBody
    @RequestMapping(value = "updateExercise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map updateExercise(@Param("id") int id,@Param("description") String description,@Param("answer") String answer) {
        Exercise exercise=exerciseService.get(id);


        if (exercise==null)
            return exerciseService.errorRespMap(respMap,"练习不存在");

        else {
            exercise.setAnswer(answer);
            exercise.setDescription(description);
            exerciseService.update(exercise);
            return exerciseService.successRespMap(respMap,"保存成功",exercise);
        }


    }

    @ResponseBody
    @RequestMapping(value = "addExercise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map updateExercise(@Param("lecturename") String lecturename,@Param("description") String description,@Param("answer") String answer) {
        lectureService.findLectureByName(lecturename);
        Exercise exercise=new Exercise();
        exercise.setDescription(description);


        if (exercise==null)
            return exerciseService.errorRespMap(respMap,"练习不存在");

        else {
            exercise.setAnswer(answer);
            exercise.setDescription(description);
            exerciseService.update(exercise);
            return exerciseService.successRespMap(respMap,"保存成功",exercise);
        }


    }

}
