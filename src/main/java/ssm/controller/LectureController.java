package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Lecture;
import ssm.model.LectureShow;
import ssm.service.LectureService;
import ssm.service.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lecture")
public class LectureController extends BaseController<Lecture>{
    @Autowired
    LectureService lectureService;
    @Autowired
    TeacherService teacherService;

    @ResponseBody
    @RequestMapping(value = "lecturelist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map getAllLecture(){
        List<Lecture> ls=lectureService.list();
        if(ls==null){
            return lectureService.errorRespMap(respMap,"error");
        }else {
            return lectureService.successRespMap(respMap,"success",ls);
        }
    }

    @ResponseBody
    @RequestMapping(value = "findlecturebyname", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map findlecturebyname(@Param("name") String name){
        Lecture lt=lectureService.findLectureByName(name);
        if(lt==null){
            return lectureService.errorRespMap(respMap,"error");
        }else {
            return lectureService.successRespMap(respMap,"success",lt);
        }
    }


    @ResponseBody
    @RequestMapping(value = "lectureshow", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map getLectureShow() {
        List<Lecture> ls = lectureService.list();

        if (ls == null) {
            return lectureService.errorRespMap(respMap, "error");
        } else {
            List<LectureShow> lectureShows = new ArrayList<LectureShow>(

            );
            for(int i=0;i<ls.size();i++){
                 LectureShow lectureShow=new LectureShow();
                lectureShow.setTeacherName(lectureService.findNameByName(ls.get(i).getName()));
                lectureShow.setLectureName(ls.get(i).getName());
                lectureShow.setPictureUrl(ls.get(i).getPictureurl());
                lectureShow.setType(ls.get(i).getType());
                lectureShows.add(lectureShow);
            }

                if (lectureShows == null) {
                    return lectureService.errorRespMap(respMap, "error");
                } else
                    return lectureService.successRespMap(respMap, "success", lectureShows);

        }
    }

    @ResponseBody
    @RequestMapping(value = "findnamebyname", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map findNameByName(@Param("name") String name){
        String tname=lectureService.findNameByName(name);
        if(tname==null){
            return lectureService.errorRespMap(respMap,"error");
        }else {
            return lectureService.successRespMap(respMap,"success",tname);
        }
    }


    @ResponseBody
    @RequestMapping(value = "findlecturebyid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map findlecturebyid(@Param("id") int id){
        String name=lectureService.findLectureById(id);
        if(name==null){
            return lectureService.errorRespMap(respMap,"error");
        }else {
            return lectureService.successRespMap(respMap,"success",name);
        }
    }

    @ResponseBody
    @RequestMapping(value = "deletelecture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map deletelecture(@Param("name") String name){
        Lecture lecture=lectureService.findLectureByName(name);
        int id=lecture.getId();
        if(lecture==null) return lectureService.errorRespMap(respMap,"error");
      lectureService.delete(id);
      Lecture lecture1=lectureService.get(id);
      if(lecture1==null)
        {
            String rt="success";
            return lectureService.successRespMap(respMap,"删除成功",rt);
        }
        else return lectureService.errorRespMap(respMap,"删除失败");
    }


    @ResponseBody
    @RequestMapping(value = "findlecturelikename", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map findlecturelikename(@Param("String") String name){
        List<Lecture> ls=lectureService.getLectureLikeName(name);
        if (ls == null) {
            return lectureService.errorRespMap(respMap, "error");
        } else {
            List<LectureShow> lectureShows = new ArrayList<LectureShow>(

            );
            for(int i=0;i<ls.size();i++){
                LectureShow lectureShow=new LectureShow();
                lectureShow.setTeacherName(lectureService.findNameByName(ls.get(i).getName()));
                lectureShow.setLectureName(ls.get(i).getName());
                lectureShow.setPictureUrl(ls.get(i).getPictureurl());
                lectureShows.add(lectureShow);
            }

            if (lectureShows == null) {
                return lectureService.errorRespMap(respMap, "error");
            } else
                return lectureService.successRespMap(respMap, "success", lectureShows);

        }
    }

    @ResponseBody
    @RequestMapping(value = "getlecturedata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getLectureData(@Param("name") String name){
        Lecture lt=lectureService.findLectureByName(name);
        List<String> list=new ArrayList<String>();
        list.add(lt.getName());
        list.add(lectureService.findNameByName(name));
        list.add(date(lt.getTime()));
        list.add(lt.getDetail());
        list.add(lt.getEnvironment());
        list.add(lt.getDescription());
        list.add(lt.getPictureurl());
        if(list==null){
            return lectureService.errorRespMap(respMap,"error");
        }else {
            return lectureService.successRespMap(respMap,"success",list);
        }
    }

    @ResponseBody
    @RequestMapping(value = "getLecture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getLecture(@Param("name") String name){
        Lecture lecture=lectureService.findLectureByName(name);


        if(lecture==null){
            return teacherService.errorRespMap(respMap,"课程不存在");
        }else {
            LectureShow lectureShow=new LectureShow();
            lectureShow.setTeacherName(lectureService.findNameByName(lecture.getName()));
            lectureShow.setLectureName(lecture.getName());
            lectureShow.setPictureUrl(lecture.getPictureurl());
            List<LectureShow> list=new ArrayList<LectureShow>();
            list.add(lectureShow);
            return teacherService.successRespMap(respMap,"success",list);
        }
    }

        protected  String date(String date){
            String week=week(date);
            String time=time(date);
            return  week+"  "+time;
        }


    protected  String week(String time){
        int a=0;
        int i=0;
        if (time.length()<3){

            try {
                a = Integer.parseInt(time);
                i=a/10;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return "第"+i+"周";}
        else {try {
            a = Integer.parseInt(time);
            i=a/100;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
            return "第"+i+"周";}

    }

    protected String time(String time){
        int a=0;
        int i=0;
        if(time.length()<3){
            try {
                a = Integer.parseInt(time);
                i=a%10;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return i+"-"+(i+1)+"节";
        }
        else{
            try {
                a = Integer.parseInt(time);
                i=a%100;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return i+"-"+(i+1)+"节";
        }
    }

}
