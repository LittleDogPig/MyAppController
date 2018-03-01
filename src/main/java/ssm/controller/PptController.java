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


}
