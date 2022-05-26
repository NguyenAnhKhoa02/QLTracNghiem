package DAL;

import java.util.ArrayList;

import BLL.ExamPaper.DetailExamPaper;

public class manageDetailExamPaperDAL extends connectSQL {
    public manageDetailExamPaperDAL() {

    }

    public void SaveDetailExamPaper(ArrayList<DetailExamPaper> arL_detailExamPaper) {
        connetToSQL();
        try {
            statement = connection.createStatement();
            for (DetailExamPaper detailExamPaper : arL_detailExamPaper)
                statement.executeUpdate("insert into DetailExamPaper(Id,IdQues,Answer) values ('"
                        + detailExamPaper.getId() + "','" + detailExamPaper.getIdQuestion() + "','"
                        + detailExamPaper.getAnswer() + "')");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        closeConnectSQL();
    }
}
