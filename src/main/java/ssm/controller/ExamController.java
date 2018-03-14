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

import java.util.ArrayList;
import java.util.List;
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


    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map list(){
        List<Exam> list=examService.list();

        if(list==null){
            return examService.errorRespMap(respMap,"error");
        }else {
            return examService.successRespMap(respMap,"success",list);
        }
    }

    @ResponseBody
    @RequestMapping(value = "deleteExam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map deleteExam(@Param("id") int id){
        Exam exam=examService.get(id);

        if(exam==null) return examService.errorRespMap(respMap,"error");
       examService.delete(id);
       Exam exam1=examService.get(id);
       if(exam1==null)
            {
                String rt="success";
            return examService.successRespMap(respMap,"删除成功",rt);
        }
        else return examService.errorRespMap(respMap,"error");

    }

    @ResponseBody
    @RequestMapping(value = "getExam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getExam(@Param("name") String name){
        Exam exam=examService.findbylname(name);


        if(exam==null){
            return examService.errorRespMap(respMap,"试题不存在");
        }else {
            List<Exam> list=new ArrayList<Exam>();
            list.add(exam);
            return examService.successRespMap(respMap,"success",list);
        }
    }


}
