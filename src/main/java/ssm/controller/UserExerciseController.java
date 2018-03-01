package ssm.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Exercise;
import ssm.model.Lecturecs;
import ssm.model.UserExercise;
import ssm.model.UserExerciseShow;
import ssm.service.ExerciseService;
import ssm.service.LecturecsService;
import ssm.service.UserExerciseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userexercise")
public class UserExerciseController extends BaseController<UserExercise>{
    @Autowired
    UserExerciseService userExerciseService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    LecturecsService lecturecsService;

    @ResponseBody
    @RequestMapping(value = "getrecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getrecord(@Param("tel") String tel) {
        List<UserExercise> ls=userExerciseService.findbytel(tel);
        if (ls==null)
            return userExerciseService.errorRespMap(respMap,"查询失败");
        else return userExerciseService.successRespMap(respMap,"查询成功",ls);


    }

    @ResponseBody
    @RequestMapping(value = "getrecordshow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getrecordshow(@Param("tel") String tel,@Param("name") String name) {
        List<UserExercise> ls=userExerciseService.findrecords(tel,name);
        List<Exercise> ls1=exerciseService.findbylecturename(name);
        List<UserExerciseShow> list=new ArrayList<UserExerciseShow>();

        for(int i=0;i<ls1.size();i++){
            UserExerciseShow userExerciseShow=new UserExerciseShow();
            userExerciseShow.setName(ls1.get(i).getName());
            userExerciseShow.setFinish(ls.get(i).getFinish());
            list.add(userExerciseShow);
        }

        if (list==null)
            return userExerciseService.errorRespMap(respMap,"查询失败");
        else return userExerciseService.successRespMap(respMap,"查询成功",list);

    }

    @ResponseBody
    @RequestMapping(value = "answer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map updatarecord(@Param("tel") String tel,@Param("name") String name,@Param("lecturename") String lecturename,@Param("answer") String answer) {
        UserExercise userExercise=userExerciseService.getrecord(tel,name,lecturename);
        Exercise exercise=exerciseService.getexercise(lecturename,name);//name是练习名
        if (userExercise==null)
            return exerciseService.errorRespMap(respMap,"error");

        else {
                String score;
                if(answer.equals(exercise.getAnswer()))
                    score="1";
                else  score="0";
                String finish="已完成";
                userExercise.setFinish(finish);
                userExercise.setScore(score);
                userExerciseService.update(userExercise);
            UserExercise userExercise1=userExerciseService.getrecord(tel,name,lecturename);

            //完成练习以后更新选课信息，若完成所有练习则1
            Lecturecs lecturecs=lecturecsService.findrecord(tel,lecturename);
            List<UserExercise> list=userExerciseService.findrecords(tel,lecturename);
            boolean finexer=false;//练习是否完成
            for(int i=0;i<list.size();i++){
                if (list.get(i).getFinish().equals("未完成"))
                {
                    finexer=false;
                    break;
                }
                else finexer=true;
            }
            if (finexer){
                lecturecs.setExercise("1");
                lecturecsService.update(lecturecs);
            }

            Lecturecs lecturecs1=lecturecsService.findrecord(tel,lecturename);

            return exerciseService.successRespMap(respMap,"success",userExercise1);
        }


    }

    @ResponseBody
    @RequestMapping(value = "getuserrecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getuserrecord(@Param("tel") String tel,@Param("name") String name,@Param("lecturename") String lecturename) {
        UserExercise userExercise=userExerciseService.getrecord(tel,name,lecturename);
        if (userExercise==null)
            return exerciseService.errorRespMap(respMap,"error");
        else {
            return exerciseService.successRespMap(respMap,"success",userExercise);
        }


    }


}
