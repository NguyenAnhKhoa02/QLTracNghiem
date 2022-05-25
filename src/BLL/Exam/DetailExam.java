package BLL.Exam;

import java.util.ArrayList;

public class DetailExam {
    public DetailExam(String IDExam, String IDQuestion) {
        str_IDQuestion = IDQuestion;
        str_IDExam = IDExam;
    }

    public String getIdExam() {
        return str_IDExam;
    }

    public String getIdQuestion() {
        return str_IDQuestion;
    }

    private String str_IDExam;
    private String str_IDQuestion;
}
