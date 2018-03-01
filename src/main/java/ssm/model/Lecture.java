package ssm.model;

import java.io.Serializable;

public class Lecture implements Serializable {

    private int id;
    private String name;
    private int tid;
    private String pictureurl;
    private String time;
    private String type;
    private String description;
    private String detail;
    private String environment;

    public Lecture(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public  String getPictureurl(){
        return pictureurl;
    }

    public void setPictureurl(String pictureurl){
        this.pictureurl=pictureurl;
    }

    public  String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time=time;
    }

    public  String getType(){
        return type;
    }

    public void setType(String type){
        this.type=type;
    }

    public  String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public  String getDetail(){
        return detail;
    }

    public void setDetail(String detail){
        this.detail=detail;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
