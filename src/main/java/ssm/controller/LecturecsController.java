package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.*;
import ssm.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/lecturecs")
public class LecturecsController extends BaseController<Lecturecs>{
    @Autowired
    LecturecsService lecturecsService;
    @Autowired
    UserService userService;
    @Autowired
    LectureService lectureService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    UserExerciseService userExerciseService;

    @ResponseBody
    @RequestMapping(value = "choselecture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map choselecture(@Param("name") String name, @Param("tel") String tel) {
        Lecturecs lecturecs1=lecturecsService.findrecord(tel,name);
        if(lecturecs1==null)
        {
            Lecture lecture0=lectureService.findLectureByName(name);
            List<Lecturecs> ls=lecturecsService.finduserchose(tel);
            boolean canbe=false;
            for (int i=0;i<ls.size();i++)
            {
                if (lectureService.get(ls.get(i).getLid()).getTime().equals(lecture0.getTime()))
                {
                    canbe=false;
                    break;
                }
                canbe=true;
            }
            if (canbe){
            Lecture lecture = lectureService.findLectureByName(name);
            User user = userService.getUserByPhoneNum(tel);
            List<Exercise>  exerciseList=exerciseService.findbylecturename(name);//找到本门课所有练习

            //录入选课信息
                Lecturecs lecturecs = new Lecturecs();
                lecturecs.setLid(lecture.getId());
                lecturecs.setSid(user.getId());
                lecturecs.setExercise("0");
                lecturecs.setExam("0");
                lecturecsService.save(lecturecs);
                Lecturecs lecturecs2 = lecturecsService.findrecord(tel, name);

                if(!(exerciseList==null)){
                //添加用户练习资料
                for(int i=0;i<exerciseList.size();i++){
                    UserExercise userExercise=new UserExercise();
                    userExercise.setSid(user.getId());
                    userExercise.setEid(exerciseList.get(i).getId());
                    userExercise.setFinish("未完成");
                    userExerciseService.save(userExercise);
                }
                }

                return userService.successRespMap(respMap, "选课成功", lecturecs2);


            }else return userService.errorRespMap(respMap, "选课时间冲突");
        }
        else return userService.errorRespMap(respMap, "此课已选");
        }

    @ResponseBody
    @RequestMapping(value = "lectureshow", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map getLectureShow(@Param("tel") String tel) {
        List<Lecturecs> lecturecs=lecturecsService.finduserchose(tel);

        if (lecturecs == null) {
            return lectureService.errorRespMap(respMap, "暂未选课");
        } else {
            List<LectureShow> lectureShows = new ArrayList<LectureShow>(

            );
            for(int i=0;i<lecturecs.size();i++){
                LectureShow lectureShow=new LectureShow();
                lectureShow.setTeacherName(lectureService.findNameByName(lectureService.get(lecturecs.get(i).getLid()).getName()));
                lectureShow.setLectureName(lectureService.get(lecturecs.get(i).getLid()).getName());
                lectureShow.setPictureUrl(lectureService.get(lecturecs.get(i).getLid()).getPictureurl());
                lectureShows.add(lectureShow);
            }


            return lectureService.successRespMap(respMap, "success", lectureShows);

        }
    }



    @ResponseBody
    @RequestMapping(value = "lecturedatashow", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map getLectureDataShow(@Param("tel") String tel) {
        List<Lecturecs> lecturecs=lecturecsService.finduserchose(tel);

        if (lecturecs == null) {
            return lectureService.errorRespMap(respMap, "暂未选课");
        } else {
            List<Lecturecs> lecturecs1=lecturecsService.finishlecture(tel);
            List<Lecturecs> lecturecs0=lecturecsService.unfinishlecture(tel);

            List<LectureShow> lectureShows0 = new ArrayList<LectureShow>();
            List<LectureShow> lectureShows1 = new ArrayList<LectureShow>();

            for(int i=0;i<lecturecs0.size();i++){
                LectureShow lectureShow=new LectureShow();
                lectureShow.setTeacherName(lectureService.findNameByName(lectureService.get(lecturecs0.get(i).getLid()).getName()));
                lectureShow.setLectureName(lectureService.get(lecturecs0.get(i).getLid()).getName());
                lectureShow.setPictureUrl(lectureService.get(lecturecs0.get(i).getLid()).getPictureurl());
                lectureShows0.add(lectureShow);
            }


            for(int i=0;i<lecturecs1.size();i++){
                LectureShow lectureShow=new LectureShow();
                lectureShow.setTeacherName(lectureService.findNameByName(lectureService.get(lecturecs1.get(i).getLid()).getName()));
                lectureShow.setLectureName(lectureService.get(lecturecs1.get(i).getLid()).getName());
                lectureShow.setPictureUrl(lectureService.get(lecturecs1.get(i).getLid()).getPictureurl());
                lectureShows1.add(lectureShow);
            }
            List<List<LectureShow>> lectureShows=new ArrayList<List<LectureShow>>();
            if (lecturecs0!=null)
            lectureShows.add(lectureShows0);
            if (lectureShows1!=null)
            lectureShows.add(lectureShows1);
                return lectureService.successRespMap(respMap, "success", lectureShows);

        }
    }




    @ResponseBody
    @RequestMapping(value = "getuserchose", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map getUserChose(@Param("tel") String tel) {
        List<Lecturecs> lecturecs=lecturecsService.finduserchose(tel);

        if (lecturecs == null) {
            return lectureService.errorRespMap(respMap, "暂未选课");
        } else {
            List<Course> courseList=new ArrayList<Course>();
            for (int i=0;i<lecturecs.size();i++)
            {
                Course course=new Course();
                course.setName(lectureService.get(lecturecs.get(i).getLid()).getName());
                course.setTime(lectureService.get(lecturecs.get(i).getLid()).getTime());
                course.setDetail(lectureService.get(lecturecs.get(i).getLid()).getDetail());
                courseList.add(course);
            }
                return lectureService.successRespMap(respMap, "success", courseList);
        }
    }


    @ResponseBody
    @RequestMapping(value = "getlecturecs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getLecturecs(@Param("tel") String tel,@Param("name") String name) {
       Lecturecs lecturecs=lecturecsService.findrecord(tel,name);

        if (lecturecs == null) {
            return lectureService.errorRespMap(respMap, "暂未选课");
        } else {
            return lectureService.successRespMap(respMap, "success", lecturecs);
        }
    }


    @ResponseBody
    @RequestMapping(value = "unchose", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map unchose(@Param("tel") String tel,@Param("name") String name) {
        Lecturecs lecturecs=lecturecsService.findrecord(tel,name);


        if (lecturecs == null) {
            return lecturecsService.errorRespMap(respMap, "你并没有选这门课");
        } else {
            lecturecsService.delete(lecturecs.getId());
            List<UserExercise> list=userExerciseService.findrecords(tel,name);
            if (!(list==null)){
                for (int i=0;i<list.size();i++){
                    userExerciseService.delete(list.get(i).getId());
                }

            }
            String test="success";
            return lecturecsService.successRespMap(respMap, "退课成功", test);
        }
    }


}
