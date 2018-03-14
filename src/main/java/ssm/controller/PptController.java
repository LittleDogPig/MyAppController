package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.model.Exam;
import ssm.model.Lecturecs;
import ssm.model.Ppt;
import ssm.model.PptShow;
import ssm.service.LectureService;
import ssm.service.LecturecsService;
import ssm.service.PptService;
import ssm.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/ppt")
public class PptController extends BaseController<Ppt>{

    @Autowired
    PptService pptService;
    @Autowired
    LecturecsService lecturecsService;
    @Autowired
    LectureService lectureService;

    @ResponseBody
    @RequestMapping(value = "findppt", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map findpptbyname(@Param("name") String name){
        Ppt ppt=pptService.findbylname(name);

        if(ppt==null){
            return pptService.errorRespMap(respMap,"error");
        }else {
            return pptService.successRespMap(respMap,"success",ppt);
        }
    }

    @ResponseBody
    @RequestMapping(value = "pptshow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map pptshow(@Param("tel") String tel){
        List<Lecturecs> lecturecs=lecturecsService.finduserchose(tel);
        List<PptShow> list=new ArrayList<PptShow>();
        for (int i=0;i<lecturecs.size();i++){
            PptShow pptShow=new PptShow();
            pptShow.setName(lectureService.get(lecturecs.get(i).getLid()).getName());
            pptShow.setUrl(pptService.findbylname(lectureService.get(lecturecs.get(i).getLid()).getName()).getUrl());
            pptShow.setFilename(pptService.findbylname(lectureService.get(lecturecs.get(i).getLid()).getName()).getName());
            list.add(pptShow);
        }
        if(list==null){
            return pptService.errorRespMap(respMap,"error");
        }else {
            return pptService.successRespMap(respMap,"success",list);
        }
    }
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map list(){
        List<Ppt> list=pptService.list();

        if(list==null){
            return pptService.errorRespMap(respMap,"error");
        }else {
            return pptService.successRespMap(respMap,"success",list);
        }
    }

    @ResponseBody
    @RequestMapping(value = "deletePPT", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map deletePPT(@Param("id") int id){
        Ppt ppt=pptService.get(id);

        if(ppt==null){
            return pptService.errorRespMap(respMap,"error");
        }else {
            pptService.delete(id);
            Ppt ppt1=pptService.get(id);
            String rt="success";
            if (ppt1==null)
            return pptService.successRespMap(respMap,"删除成功",rt);
            else  return pptService.errorRespMap(respMap,"删除失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "pptlistShow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map pptlistShow(){
        List<Ppt> pptList=pptService.list();
        List<PptShow> list=new ArrayList<PptShow>();
        for (int i=0;i<pptList.size();i++){
            PptShow pptShow=new PptShow();
            pptShow.setName(lectureService.get(pptList.get(i).getLid()).getName());
            pptShow.setId(pptList.get(i).getId());
            list.add(pptShow);
        }
        if(list==null){
            return pptService.errorRespMap(respMap,"error");
        }else {
            return pptService.successRespMap(respMap,"success",list);
        }
    }

    @ResponseBody
    @RequestMapping(value = "getPPT", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map getPPT(@Param("name") String name) {
        List<Ppt> pptList = pptService.getPPT(name);


        if (pptList == null) {
            return pptService.errorRespMap(respMap, "error");
        } else {
            List<PptShow> list = new ArrayList<PptShow>();
            for (int i = 0; i < pptList.size(); i++) {
                PptShow pptShow = new PptShow();
                pptShow.setName(lectureService.get(pptList.get(i).getLid()).getName());
                pptShow.setId(pptList.get(i).getId());
                list.add(pptShow);
                }
                return pptService.successRespMap(respMap, "success", list);
        }
    }
}
