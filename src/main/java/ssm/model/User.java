package ssm.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonView.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


//@Entity
//@Table(name = "user")

public class User implements Serializable {

    private int id;
    private String name;
    private String tel;
    private String password;
    private String headimg;
    private String create_time;
    private String update_time;
    private String sex;
    private String description;



    public String getHeadimg() {
        return headimg;
    }
    public void setHeadimg(String Headimg) {
        this.headimg = Headimg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }



    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getPassword() {
        return password;
    }


    public String getDescription() {
        return description;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
