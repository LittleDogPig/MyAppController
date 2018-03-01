package ssm.model;

import java.io.Serializable;

public class Lecturecs implements Serializable {
    private int id;
    private int lid;
    private int sid;
    private String time;
    private String exercise;
    private String exam;
    private int score;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getExam() {
        return exam;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
