package ssm.controller;

import constant.Constant;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ssm.model.User;
import ssm.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController<Object> {

    @Autowired
    UserService userService;

    //指定文件下载路径
    String IMG_DOWNLOADHOME = null;

    /**
     * 查询图片
     * 文件下载功能
     *
     * @param type     0为用户 1为课程
     * @param filename
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void getImg(@Param("filename") String filename, HttpServletResponse response, @Param("type") int type) {


        if (type == 0) {
            IMG_DOWNLOADHOME = Constant.IMG_USER_HOME;
        } else if (type == 1) {
            IMG_DOWNLOADHOME = Constant.IMG_LECTURE_HOME;
        } else if (type == 2) {
            IMG_DOWNLOADHOME = Constant.IMG_TEACHER_HOME;
        }else if (type == 3) {
            IMG_DOWNLOADHOME = Constant.VDO_LECTURE_HOME;
        }else if (type == 4) {
            IMG_DOWNLOADHOME = Constant.IMG_EXAM_HOME;
        }else if (type == 5) {
            IMG_DOWNLOADHOME = Constant.PPT_HOME;
        }else {
            IMG_DOWNLOADHOME = "F:\\";
            return;
        }

        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(IMG_DOWNLOADHOME + filename);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传功能
     *
     * @param tel
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam("tel") String tel) throws IOException {

//        String path = request.getSession().getServletContext().getRealPath("upload");
        User user = userService.getUserByPhoneNum(tel);
        //判断用户是否为空
        if (user == null) {
            return userService.merrorRespMap(respMap, "user is not exist");
        }
        //用户存在，开始上传图片
        String fileName = file.getOriginalFilename();
        StringBuilder sb = new StringBuilder(fileName);
        long now = System.currentTimeMillis();
        String deal_name = now + sb.substring(sb.indexOf("."), sb.length());
        System.out.println("name:" + deal_name);
        File dir = new File(Constant.IMG_USER_HOME, deal_name);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(dir);

        //更新图片地址到数据库
//        user.setHeadimg(Constant.STORE_HOME + "file/download/?filename=" + deal_name + "&type=" + 0);
        user.setHeadimg("file/download/?filename=" + deal_name + "&type=" + 0);
        userService.update(user);
        return userService.successRespMap(respMap, "success", deal_name);
    }



}