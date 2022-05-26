package DAL;

import java.util.ArrayList;

import BLL.ExamPaper.ExamPaper;

public class manageExamPaperDAL extends connectSQL {
    public manageExamPaperDAL() {

    }

    public ArrayList<String> getAllIdExamPaper() {
        ArrayList<String> arL_tempt = new ArrayList<>();
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Id from ExamPapers");

            while (resultSet.next())
                arL_tempt.add(resultSet.getString(1));
        } catch (Exception e) {
            // TODO: handle exception
        }
        closeConnectSQL();

        return arL_tempt;
    }

    public void SaveExamPaper(ExamPaper examPaper) {
        connetToSQL();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(
                    "insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('"
                            + examPaper.getIdStudent() + "','" + examPaper.getIdExam() + "','" + examPaper.getId()
                            + "'," + examPaper.getNumberRight() + "," + examPaper.getNumberWrong() + ","
                            + examPaper.getMark() + ")");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        closeConnectSQL();
    }

    public ExamPaper getExamPaperById(String Id) {
        connetToSQL();
        ExamPaper examPaper = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from ExamPapers where Id = '" + Id + "'");

            resultSet.next();
            examPaper = new ExamPaper(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getInt(4), resultSet.getInt(5), resultSet.getFloat(6));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return examPaper;
    }
}
