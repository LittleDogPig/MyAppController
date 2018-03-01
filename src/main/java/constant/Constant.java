package constant;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Constant {


    public static String IP =null;

    static {
        try {
            IP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    //用户图片存储路径0
    public final static String IMG_USER_HOME = "F:\\imgs\\user\\";
    //课程图片存储路径1
    public final static String IMG_LECTURE_HOME = "F:\\imgs\\lecture\\";
    //老师图片存储路径2
    public final static String IMG_TEACHER_HOME = "F:\\imgs\\teacher\\";
    //课程视频存储路径3
    public final static String VDO_LECTURE_HOME = "F:\\imgs\\videolecture\\";
    //试题图片存储路径4
    public final static String IMG_EXAM_HOME = "F:\\imgs\\exam\\";
    //课件存储路径5
    public final static String PPT_HOME = "F:\\imgs\\ppt\\";


    public final static String STORE_HOME = "http://"+IP+":8080/";



}
