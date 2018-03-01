package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Exam;
import ssm.model.Lecturecs;
import ssm.model.Teacher;
import ssm.service.ExamService;
import ssm.service.LecturecsService;

import java.util.Map;


@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController<Exam>{

    @Autowired
    ExamService examService;
    @Autowired
    LecturecsService lecturecsService;

    @ResponseBody
    @RequestMapping(value = "findexam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map findteacherbyname(@Param("name") String name){
        Exam exam=examService.findbylname(name);

        if(exam==null){
            return examService.errorRespMap(respMap,"error");
        }else {
            return examService.successRespMap(respMap,"success",exam);
        }
    }

    @ResponseBody
    @RequestMapping(value = "answer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map answer(@Param("tel") String tel,@Param("name") String name,@Param("answer") String answer){
        Exam exam=examService.findbylname(name);
        Lecturecs lecturecs=lecturecsService.findrecord(tel,name);
        lecturecs.setScore(score(answer,exam.getAnswer()));//评分
        lecturecs.setExam("1");//完成考试
        lecturecs.setTime(System.currentTimeMillis()+"");//完成时间
        lecturecsService.update(lecturecs);
        Lecturecs lecturecs1=lecturecsService.findrecord(tel,name);
        if(lecturecs1==null){
            return examService.errorRespMap(respMap,"error");
        }else {
            return examService.successRespMap(respMap,"success",lecturecs1);
        }
    }

    private  static int score(String post,String answer){
        post=post.toUpperCase();
        char a[]=post.toCharArray();
        char b[]=answer.toCharArray();
        int score=0;
        for(int i=0;i<a.length;i++){
            if(a[i]==(b[i]))
                score+=20;
        }


        return score;

    }


}
