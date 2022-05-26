package BLL;

import java.util.ArrayList;
import BLL.Exam.DetailExam;
import DAL.connectSQL;

public class manageDetailExam {
    public manageDetailExam() {
        connectSQL = new connectSQL();
    }

    public ArrayList<DetailExam> getAllDetailExam(String Id[], String TypeQues[]) {
        return connectSQL.getAllDetailExam(Id, TypeQues);
    }

    public ArrayList<DetailExam> getAllDetailExam(String IdExam, String ID[]) {
        ArrayList<DetailExam> arL_detailExam = new ArrayList<>();

        for (String str : ID) {
            arL_detailExam.add(new DetailExam(IdExam, str));
        }

        return arL_detailExam;
    }

    public ArrayList<DetailExam> getAllDetailExamById(String Id) {
        return connectSQL.getAllDetailExamById(Id);
    }

    public ArrayList<String> getAllAnswerDetailExamById(String Id) {
        return connectSQL.getAnswerQuesById(Id);
    }

    private connectSQL connectSQL;
    private DetailExam de_detailExam;
}
