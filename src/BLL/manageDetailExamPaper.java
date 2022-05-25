package BLL;

import java.util.ArrayList;
import java.util.HashMap;

import BLL.ExamPaper.DetailExamPaper;
import DAL.connectSQL;

public class manageDetailExamPaper {
    public manageDetailExamPaper() {
        connectSQL = new connectSQL();
    }

    public void createArLDetailExamPaper(String Id, HashMap<String, String> answer) {
        arL_detailExamPapers = new ArrayList<>();

        for (String str : answer.keySet()) {
            arL_detailExamPapers.add(new DetailExamPaper(Id, answer.get(str), str));
        }
    }

    public void SaveToSql() {
        connectSQL.SaveDetailExamPaper(arL_detailExamPapers);
    }

    private connectSQL connectSQL;
    private ArrayList<DetailExamPaper> arL_detailExamPapers;
}
