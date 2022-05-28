package BLL;

import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.util.ElementScanner14;

import BLL.DTO.ExamPaperDTO;
import BLL.Exam.Exam;
import BLL.ExamPaper.ExamPaper;
import DAL.connectSQL;
import DAL.manageExamPaperDAL;

public class manageExamPaper {
    public manageExamPaper() {
        mnq_manageQuestion = new manageQuestion();
        mepd_manageExamPaperDAL = new manageExamPaperDAL();
    }

    public ArrayList<String> getAllIdExamPaper() {
        return mepd_manageExamPaperDAL.getAllIdExamPaper();
    }

    private void checkMark(Exam exam, HashMap<String, String> answer) {
        float float_markQuestion = 10 / answer.size();

        int_rightQuestion = 0;
        int_wrongQuestion = 0;

        for (String str : answer.keySet()) {
            if (answer.get(str).equalsIgnoreCase(mnq_manageQuestion.getAnswer(str))) {
                int_rightQuestion++;
            } else
                int_wrongQuestion++;
        }

        float_mark = int_rightQuestion * float_markQuestion;
    }

    public void makingExamPaper(Exam exam, HashMap<String, String> answer, String IdStudent) {
        checkMark(exam, answer);
        riep_randomIdExamPaper = new randomIdExamPaper();
        str_idExamPaper = riep_randomIdExamPaper.getRandomIdExamPaper();
        mepd_manageExamPaperDAL.SaveExamPaper(
                new ExamPaper(IdStudent, exam.getID(), str_idExamPaper, int_rightQuestion,
                        int_wrongQuestion, float_mark));

        mdep_manageDetailPaper = new manageDetailExamPaper();
        mdep_manageDetailPaper.createArLDetailExamPaper(str_idExamPaper, answer);
        mdep_manageDetailPaper.SaveToSql();
    }

    public String getCurrentStringIdExamPaper() {
        return str_idExamPaper;
    }

    public ExamPaper getExamPaperById(String Id) {
        return mepd_manageExamPaperDAL.getExamPaperById(Id);
    }

    public boolean isDoExam(String IdStudent, String subject) {
        return mepd_manageExamPaperDAL.isDoExam(IdStudent, subject);
    }

    public int getAverageMark(String idStudent, String Semester) {
        return mepd_manageExamPaperDAL.getAverageMart(idStudent, Semester);
    }

    public String[][] getArrStastic(String Semester) {

        return new ExamPaperDTO().getStringArrStatic(Semester);
    }

    private int int_rightQuestion, int_wrongQuestion;
    private float float_mark;
    private manageQuestion mnq_manageQuestion;
    private manageDetailExamPaper mdep_manageDetailPaper;
    private randomIdExamPaper riep_randomIdExamPaper;
    private String str_idExamPaper;

    private manageExamPaperDAL mepd_manageExamPaperDAL;
}
