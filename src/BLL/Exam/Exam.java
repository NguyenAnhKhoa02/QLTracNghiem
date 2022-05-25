package BLL.Exam;

import java.sql.Time;
import java.util.ArrayList;

import BLL.TimeExam.TimeExam;

public class Exam {
    public Exam(String Id, TimeExam timeExam, ArrayList<DetailExam> deatailExam) {
        str_Id = Id;
        te_timeExam = timeExam;
        de_detailExam = deatailExam;
    }

    public String getID() {
        return str_Id;
    }

    public TimeExam getTimeExam() {
        return te_timeExam;
    }

    public ArrayList<DetailExam> getDetailExams() {
        return de_detailExam;
    }

    private String str_Id;
    private TimeExam te_timeExam;
    private ArrayList<DetailExam> de_detailExam;
}
