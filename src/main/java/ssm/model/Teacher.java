package ssm.model;

import java.io.Serializable;

public class Teacher implements Serializable {

    private int id;
    private String name;
    private String sex;
    private String email;
    private String school;
    private String pictureurl;
    private String description;
    private String detail;

    public Teacher(){}


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public  String getPictureurl(){
        return pictureurl;
    }

    public void setPictureurl(String pictureurl){
        this.pictureurl=pictureurl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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



}
