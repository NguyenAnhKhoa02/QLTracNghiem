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

    public boolean isDoExam(String IdStudent, String IdSubject) {
        String str_query = "select distinct IdExam "
                + "from ExamPapers e, DetailExamPaper d, Questions q, Subjects s, TimeExam t, Exam ex "
                + "where e.IdStudent = '" + IdStudent
                + "' and e.Id = d.Id and q.IdSubject = s.Id and d.IdQues = q.Id and s.Id ='" + IdSubject.trim()
                + "' and e.IdExam = ex.Id and ex.Semester = t.TimeName and t.TimeName = 'HKII'";

        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);

            while (resultSet.next()) {
                closeConnectSQL();
                return true;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        closeConnectSQL();

        return false;
    }

    public int getAverageMart(String idStudent, String Semester) {
        int mark = 0;
        String str_query = "select AVG(Mark) "
                + "from ExamPapers ep, Exam e, TimeExam t "
                + "where IdStudent = '" + idStudent
                + "' and ep.IdExam = e.Id and e.Semester = t.TimeName and t.TimeName = '" + Semester + "'";
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery(str_query);

            resultSet.next();
            mark = resultSet.getInt(1);

        } catch (Exception e) {
            // TODO: handle exception
        }

        closeConnectSQL();
        return mark;
    }

    public String getMarkByIdSubjectAndStudent(String IdSubject, String IdStudent, String Semester) {
        String str = "";
        String str_query = "select distinct ep.Mark "
                + "from ExamPapers ep, Exam e, DetailExam de, Questions q, Subjects s, TimeExam t "
                + "where ep.IdExam = e.Id and e.Id = de.Id and de.IdQues = q.Id and s.Id = q.IdSubject and s.Id ='"
                + IdSubject.trim() + "' and ep.IdStudent = '" + IdStudent
                + "' and e.Semester = t.TimeName and t.TimeName = '" + Semester + "'";
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);

            resultSet.next();
            str = resultSet.getString(1);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return str;
    }
}
