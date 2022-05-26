package BLL;

import java.util.ArrayList;
import BLL.Exam.DetailExam;
import DAL.connectSQL;
import DAL.manageDetailExamDAL;

public class manageDetailExam {
    public manageDetailExam() {
        mded_manageDetailExamDAL = new manageDetailExamDAL();
    }

    public ArrayList<DetailExam> getAllDetailExam(String Id[], String TypeQues[]) {
        return mded_manageDetailExamDAL.getAllDetailExam(Id, TypeQues);
    }

    public ArrayList<DetailExam> getAllDetailExam(String IdExam, String ID[]) {
        ArrayList<DetailExam> arL_detailExam = new ArrayList<>();

        for (String str : ID) {
            arL_detailExam.add(new DetailExam(IdExam, str));
        }

        return arL_detailExam;
    }

    public ArrayList<DetailExam> getAllDetailExamById(String Id) {
        return mded_manageDetailExamDAL.getAllDetailExamById(Id);
    }

    public ArrayList<String> getAllAnswerDetailExamById(String Id) {
        return mded_manageDetailExamDAL.getAnswerQuesById(Id);
    }

    private manageDetailExamDAL mded_manageDetailExamDAL;
    private DetailExam de_detailExam;
    private ArrayList<DetailExam> arL_detalExam;
}
