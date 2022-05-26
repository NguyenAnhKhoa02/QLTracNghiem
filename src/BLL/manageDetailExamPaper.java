package BLL;

import java.util.ArrayList;
import java.util.HashMap;

import BLL.ExamPaper.DetailExamPaper;
import DAL.connectSQL;
import DAL.manageDetailExamPaperDAL;

public class manageDetailExamPaper {
    public manageDetailExamPaper() {
        mdep_manageDetailExamDAL = new manageDetailExamPaperDAL();
    }

    public void createArLDetailExamPaper(String Id, HashMap<String, String> answer) {
        arL_detailExamPapers = new ArrayList<>();

        for (String str : answer.keySet()) {
            arL_detailExamPapers.add(new DetailExamPaper(Id, answer.get(str), str));
        }
    }

    public void SaveToSql() {
        mdep_manageDetailExamDAL.SaveDetailExamPaper(arL_detailExamPapers);
    }

    private manageDetailExamPaperDAL mdep_manageDetailExamDAL;
    private ArrayList<DetailExamPaper> arL_detailExamPapers;
}
