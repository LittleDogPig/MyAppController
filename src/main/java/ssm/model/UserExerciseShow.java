package ssm.model;

import java.io.Serializable;

public class UserExerciseShow implements Serializable {
    String name;
    String finish;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getFinish() {
        return finish;
    }
}
